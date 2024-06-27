package mikufan.cx.conduit.frontend.logic

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.rx.observer
import mikufan.cx.conduit.frontend.logic.util.MviComponent
import mikufan.cx.conduit.frontend.logic.util.stateValue

interface ScreenBComponent : MviComponent<ScreenBIntent, ScreenBState> {
  val id: String
}

class DefaultScreenBComponent(
  override val id: String,
  private val componentContext: ComponentContext,
  storeFactory: ScreenBStoreFactory,
  val onBackPressed: () -> Unit,
) : ScreenBComponent, ComponentContext by componentContext {

  private val store = instanceKeeper.getStore {
    storeFactory.createStore()
  }

  init {
    this.store.labels(observer {
      onBackPressed()
    })
  }

  override val state: Value<ScreenBState> = store.stateValue

  override fun send(intent: ScreenBIntent) {
    store.accept(intent)
  }

}

