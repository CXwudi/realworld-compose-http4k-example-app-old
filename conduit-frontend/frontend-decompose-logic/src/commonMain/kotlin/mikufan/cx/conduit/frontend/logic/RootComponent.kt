package mikufan.cx.conduit.frontend.logic

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

interface RootComponent {
  val child: Value<ChildStack<*, RootComponentChild>>

  sealed class RootComponentChild {
    data class ScreenA(val component: ScreenAComponent) : RootComponentChild()
    data class ScreenB(val component: ScreenBComponent) : RootComponentChild()
  }
}

class DefaultRootComponent(
  val componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

  private val stackNavigation = StackNavigation<NavigationConfig>()

  override val child: Value<ChildStack<*, RootComponent.RootComponentChild>> =
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
  ): RootComponent.RootComponentChild {
    return when (navigationConfig) {
      is NavigationConfig.ScreenAConfig -> RootComponent.RootComponentChild.ScreenA(
        DefaultScreenAComponent(componentContext) { stackNavigation.push(NavigationConfig.ScreenBConfig(it)) }
      )
      is NavigationConfig.ScreenBConfig -> RootComponent.RootComponentChild.ScreenB(
        DefaultScreenBComponent(navigationConfig.id, componentContext) { stackNavigation.pop() }
      )
    }
  }

  @Serializable
  private sealed class NavigationConfig {
    @Serializable
    data object ScreenAConfig : NavigationConfig()
    @Serializable
    data class ScreenBConfig(val id: String) : NavigationConfig()
  }
}

