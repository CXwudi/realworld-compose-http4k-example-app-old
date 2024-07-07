package mikufan.cx.conduit.frontend.logic.component.landing

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.serialization.Serializable


sealed interface LandingPageIntent {
  data class TextChanged(val text: String) : LandingPageIntent
  data object ToNextPage : LandingPageIntent
}

data object ToNextPageLabel

@Serializable
data class LandingPageState(
  val url: String
)

class LandingPageStoreFactory(
  private val storeFactory: StoreFactory,
) {

  internal sealed interface Msg {
    data class TextChanged(val text: String) : Msg
  }

  private val executor =
    coroutineExecutorFactory<LandingPageIntent, Nothing, LandingPageState, Msg, ToNextPageLabel> {
      onIntent<LandingPageIntent.TextChanged> {
        dispatch(Msg.TextChanged(it.text))
      }
      onIntent<LandingPageIntent.ToNextPage> {
        // TODO: verify it is a valid URL format and save it into disk
        publish(ToNextPageLabel)
      }
    }

  private val reducer = Reducer<LandingPageState, Msg> { msg ->
    when (msg) {
      is Msg.TextChanged -> LandingPageState(msg.text)
    }
  }

  fun createStore() = storeFactory.create(
      name = "LandingPageStore",
      initialState = LandingPageState(""),
      executorFactory = executor,
      reducer = reducer
    )
}

