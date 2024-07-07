package mikufan.cx.conduit.frontend.logic.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import mikufan.cx.conduit.frontend.logic.component.landing.DefaultLandingPageComponent
import mikufan.cx.conduit.frontend.logic.component.landing.LandingPageComponent
import mikufan.cx.conduit.frontend.logic.component.util.LocalKoinComponent
import org.koin.core.component.get

interface RootNavComponent {
  val childSlot: Value<ChildSlot<*, RootComponentChild>>
}

sealed interface RootComponentChild {

  data object Loading : RootComponentChild
  data class LandingPage(
    val component: LandingPageComponent
  ) : RootComponentChild

}

class DefaultRootNavComponent(
  componentContext: ComponentContext,
  private val koin: LocalKoinComponent
) : RootNavComponent, ComponentContext by componentContext {

  private val slotNavigation = SlotNavigation<Config>()

  override val childSlot: Value<ChildSlot<*, RootComponentChild>> =
    childSlot(
      source = slotNavigation,
      initialConfiguration = { Config.Loading },
      serializer = Config.serializer(),
      childFactory = ::childFactory
    )

  private fun childFactory(
    config: Config,
    componentContext: ComponentContext
  ): RootComponentChild {
    val child = when (config) {
      Config.Loading -> RootComponentChild.Loading
      Config.LandingPage -> RootComponentChild.LandingPage(
        component = koin.createLandingPageComponent(componentContext)
      )
    }
    return child
  }

  private fun LocalKoinComponent.createLandingPageComponent(
    componentContext: ComponentContext
  ) = DefaultLandingPageComponent(
    componentContext = componentContext,
    koinComponent = this,
    storeFactory = get()
  )


  @Serializable
  sealed interface Config {
    @Serializable
    data object Loading : Config

    @Serializable
    data object LandingPage : Config
  }
}
