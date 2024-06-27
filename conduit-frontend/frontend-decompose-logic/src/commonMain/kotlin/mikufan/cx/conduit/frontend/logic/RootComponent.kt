package mikufan.cx.conduit.frontend.logic

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import mikufan.cx.conduit.frontend.logic.util.LocalKoinComponent
import org.koin.core.component.get

interface RootComponent {
  val child: Value<ChildStack<*, RootComponentChild>>
}

sealed class RootComponentChild {
  data class ScreenA(val component: ScreenAComponent) : RootComponentChild()
  data class ScreenB(val component: ScreenBComponent) : RootComponentChild()
}

class DefaultRootComponent(
  val componentContext: ComponentContext,
  val koinComponent: LocalKoinComponent,
) : RootComponent, ComponentContext by componentContext {

  private val stackNavigation = StackNavigation<NavigationConfig>()

  override val child: Value<ChildStack<*, RootComponentChild>> =
    childStack(
      source = stackNavigation,
      serializer = NavigationConfig.serializer(),
      initialConfiguration = NavigationConfig.ScreenAConfig,
      handleBackButton = true,
      childFactory = ::createChild
    )

  private fun createChild(
    navigationConfig: NavigationConfig,
    componentContext: ComponentContext
  ): RootComponentChild {
    return when (navigationConfig) {
      is NavigationConfig.ScreenAConfig -> RootComponentChild.ScreenA(
        koinComponent.createScreenAComponent(componentContext) { stackNavigation.push(NavigationConfig.ScreenBConfig(it)) }
      )

      is NavigationConfig.ScreenBConfig -> RootComponentChild.ScreenB(
        koinComponent.createScreenBComponent(navigationConfig.id, componentContext) { stackNavigation.pop() }
      )
    }
  }

  private fun LocalKoinComponent.createScreenAComponent(
    componentContext: ComponentContext,
    onNavigate: (String) -> Unit
  ) = DefaultScreenAComponent(componentContext, get()) { onNavigate(it) }

  private fun LocalKoinComponent.createScreenBComponent(
    id: String,
    componentContext: ComponentContext,
    onNavigate: () -> Unit
  ) = DefaultScreenBComponent(id, componentContext, get()) { onNavigate() }

  @Serializable
  private sealed class NavigationConfig {
    @Serializable
    data object ScreenAConfig : NavigationConfig()

    @Serializable
    data class ScreenBConfig(val id: String) : NavigationConfig()
  }
}
