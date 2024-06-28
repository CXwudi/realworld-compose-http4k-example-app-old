package mikufan.cx.conduit.frontend.logic

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.rx.observer
import mikufan.cx.conduit.frontend.logic.util.MviComponent
import mikufan.cx.conduit.frontend.logic.util.stateValue

interface ScreenAComponent : MviComponent<ScreenAIntent, ScreenAState>

class DefaultScreenAComponent(
  private val componentContext: ComponentContext,
  storeFactory: ScreenAStoreFactory,
  val onScreenBNavigate: (String) -> Unit
) : ScreenAComponent, ComponentContext by componentContext {

  private val store = instanceKeeper.getStore {
    // the creation of the store must be called inside here
    storeFactory.createStore()
  }

  init {
    store.labels(observer {
      onScreenBNavigate(it.id)
    })
  }

  override val state: Value<ScreenAState> = store.stateValue

  override fun send(intent: ScreenAIntent) {
    store.accept(intent)
  }
}

