package mikufan.cx.conduit.frontend.logic

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.serialization.Serializable


class ScreenAStoreFactory(
  private val storeFactory: StoreFactory,
// other services here
) {

  internal sealed interface ScreenAMsg {
    data class TextChange(val text: String) : ScreenAMsg
    data class CountChange(val count: Int) : ScreenAMsg
  }

  private val executor =
    coroutineExecutorFactory<ScreenAIntent, Nothing, ScreenAState, ScreenAMsg, ScreenAToScreenB> {
      onIntent<ScreenAIntent.TextChange> {
        dispatch(ScreenAMsg.TextChange(it.text))
      }
      onIntent<ScreenAIntent.CountChange> {
        dispatch(ScreenAMsg.CountChange(it.count))
      }
      onIntent<ScreenAIntent.ToScreenB> {
        publish(ScreenAToScreenB(state().text))
      }
    }

  private val reducer = Reducer<ScreenAState, ScreenAMsg> { msg ->
    when (msg) {
      is ScreenAMsg.TextChange -> this.copy(text = msg.text)
      is ScreenAMsg.CountChange -> this.copy(count = msg.count)
    }
  }

  fun createStore(): Store<ScreenAIntent, ScreenAState, ScreenAToScreenB> {
    val store =
      object : Store<ScreenAIntent, ScreenAState, ScreenAToScreenB> by storeFactory.create(
        name = "ScreenAStore",
        initialState = ScreenAState("", 0),
        executorFactory = executor,
        reducer = reducer,
      ) {}
    return store
  }

  // other functions here

}

sealed interface ScreenAIntent {
  data object ToScreenB : ScreenAIntent
  data class TextChange(val text: String) : ScreenAIntent
  data class CountChange(val count: Int) : ScreenAIntent
}


data class ScreenAToScreenB(
  val id: String
)

@Serializable
data class ScreenAState(
  val text: String,
  val count: Int,
)