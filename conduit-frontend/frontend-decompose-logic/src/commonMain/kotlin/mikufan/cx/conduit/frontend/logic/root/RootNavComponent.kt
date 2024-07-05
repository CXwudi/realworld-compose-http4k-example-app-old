package mikufan.cx.conduit.frontend.logic.root

import com.arkivanov.decompose.ComponentContext
import mikufan.cx.conduit.frontend.logic.util.LocalKoinComponent

interface RootNavComponent {
//  val child: Value<ChildStack<*, RootComponentChild>>
}

class DefaultRootNavComponent(
  componentContext: ComponentContext,
  private val koin: LocalKoinComponent
) : RootNavComponent, ComponentContext by componentContext {

}
