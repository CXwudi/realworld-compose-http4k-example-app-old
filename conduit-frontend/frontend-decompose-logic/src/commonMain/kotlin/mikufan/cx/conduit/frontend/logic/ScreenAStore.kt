package mikufan.cx.conduit.frontend.logic

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.serialization.Serializable


interface ScreenAStore : Store<ScreenAIntent, ScreenAState, ScreenAToScreenB>

internal class ScreenAStoreFactory(
  private val storeFactory: StoreFactory,
// other services here
) {

  internal sealed interface ScreenAMsg {
    data class TextChange(val text: String) : ScreenAMsg
  }

  private val executor =
    coroutineExecutorFactory<ScreenAIntent, Nothing, ScreenAState, ScreenAMsg, ScreenAToScreenB> {
      onIntent<ScreenAIntent.TextChange> {
        dispatch(ScreenAMsg.TextChange(it.text))
      }
      onIntent<ScreenAIntent.ToScreenB> {
        publish(ScreenAToScreenB(state().text))
      }
    }

  private val reducer = Reducer<ScreenAState, ScreenAMsg> { msg ->
    when (msg) {
      is ScreenAMsg.TextChange -> ScreenAState(msg.text)
    }
  }

  fun createStore(): ScreenAStore = object : ScreenAStore,
    Store<ScreenAIntent, ScreenAState, ScreenAToScreenB> by storeFactory.create(
      name = "ScreenAStore",
      initialState = ScreenAState(""),
      executorFactory = executor,
      reducer = reducer,
    ) {}

  // other functions here
}

sealed interface ScreenAIntent {
  data object ToScreenB : ScreenAIntent
  data class TextChange(val text: String) : ScreenAIntent
}


data class ScreenAToScreenB(
  val id: String
)

@Serializable
data class ScreenAState(
  val text: String
)