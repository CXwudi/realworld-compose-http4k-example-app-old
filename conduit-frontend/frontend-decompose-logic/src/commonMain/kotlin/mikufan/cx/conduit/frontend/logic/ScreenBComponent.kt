package mikufan.cx.conduit.frontend.logic

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.rx.observer
import com.arkivanov.mvikotlin.core.store.StoreFactory
import mikufan.cx.conduit.frontend.logic.util.MviComponent
import mikufan.cx.conduit.frontend.logic.util.stateValue

interface ScreenBComponent : MviComponent<ScreenBIntent, ScreenBState> {
  val id: String
}

class DefaultScreenBComponent(
  override val id: String,
  val componentContext: ComponentContext,
  storeFactory: StoreFactory,
  val onBackPressed: () -> Unit,
) : ScreenBComponent, ComponentContext by componentContext {

  private val store = instanceKeeper.getStore {
    ScreenBStore(storeFactory)
  }

  init {
    this.store.labels(observer {
      onBackPressed()
    })
  }

  override val state: Value<ScreenBState>
    get() = store.stateValue

  override fun send(intent: ScreenBIntent) {
    store.accept(intent)
  }

}

