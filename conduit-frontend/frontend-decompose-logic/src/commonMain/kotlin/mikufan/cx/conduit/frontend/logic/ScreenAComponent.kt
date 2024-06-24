package mikufan.cx.conduit.frontend.logic

import com.arkivanov.decompose.ComponentContext

interface ScreenAComponent {
  fun toScreenB(id: String)
}

class DefaultScreenAComponent(
  val componentContext: ComponentContext,
  val onScreenBNavigate: (String) -> Unit
) : ScreenAComponent, ComponentContext by componentContext {
  override fun toScreenB(id: String) {
    onScreenBNavigate(id)
  }
}