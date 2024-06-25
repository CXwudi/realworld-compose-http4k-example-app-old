package mikufan.cx.conduit.frontend.logic

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.rx.observer
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import mikufan.cx.conduit.frontend.logic.util.asValue

interface ScreenAComponent {
  val state: Value<ScreenAState>
  fun sendIntent(intent: ScreenAIntent)
}

class DefaultScreenAComponent(
  private val componentContext: ComponentContext,
  private val storeFactory: StoreFactory,
  val onScreenBNavigate: (String) -> Unit
) : ScreenAComponent, ComponentContext by componentContext {

  private val store = instanceKeeper.getStore {
    storeFactory
      .create<ScreenAIntent, Nothing, ScreenAMsg, ScreenAState, ScreenAToScreenB>(
        name = "ScreenAStore",
        initialState = ScreenAState(""),
        executorFactory = coroutineExecutorFactory {
          onIntent<ScreenAIntent.TextChange> {
            dispatch(ScreenAMsg.TextChange(it.text))
          }
          onIntent<ScreenAIntent.ToScreenB> {
            publish(ScreenAToScreenB(state().text))
          }
        },
        reducer = { msg ->
          when (msg) {
            is ScreenAMsg.TextChange -> ScreenAState(msg.text)
          }
        }
      )
  }

  init {
    store.labels(observer {
      onScreenBNavigate(it.id)
    })
  }

  override val state: Value<ScreenAState> = store.asValue()

  override fun sendIntent(intent: ScreenAIntent) {
    store.accept(intent)
  }
}

sealed interface ScreenAIntent {
  data object ToScreenB : ScreenAIntent
  data class TextChange(val text: String) : ScreenAIntent
}

sealed interface ScreenAMsg {
  data class TextChange(val text: String) : ScreenAMsg
}

data class ScreenAToScreenB(
  val id: String
)

data class ScreenAState(
  val text: String
)