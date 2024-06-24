package mikufan.cx.conduit.frontend.logic

import com.arkivanov.decompose.ComponentContext

interface ScreenBComponent {
  val id: String
  fun onBack(): Unit
}

class DefaultScreenBComponent(
  override val id: String,
  val componentContext: ComponentContext,
  val onBackPressed: () -> Unit,
) : ScreenBComponent, ComponentContext by componentContext {

  override fun onBack() = onBackPressed()
}