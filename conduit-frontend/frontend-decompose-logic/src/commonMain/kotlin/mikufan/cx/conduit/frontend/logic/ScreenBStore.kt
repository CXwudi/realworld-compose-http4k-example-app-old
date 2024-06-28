package mikufan.cx.conduit.frontend.logic

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mikufan.cx.conduit.frontend.logic.service.SampleCounterNoServive


class ScreenBStoreFactory(
  private val storeFactory: StoreFactory,
  private val counterNoServive: SampleCounterNoServive,
) {

  private val executorFactory =
    coroutineExecutorFactory<ScreenBIntent, Nothing, ScreenBState, Msg, ScreenBLabel> {
      onIntent<ScreenBIntent.BackToScreenA> {
        publish(ScreenBLabel.BackToScreenA)
      }
      onIntent<ScreenBIntent.CountChange> {
        dispatch(Msg.CountChange(it.count))
      }
    }

  private sealed interface Msg {
    data class CountChange(val count: Int) : Msg
  }

  private val reducer: Reducer<ScreenBState, Msg> = Reducer { msg ->
    when (msg) {
      is Msg.CountChange -> this.copy(count = msg.count)
    }
  }

  fun createStore(): Store<ScreenBIntent, ScreenBState, ScreenBLabel> {

    val store = object : Store<ScreenBIntent, ScreenBState, ScreenBLabel> by storeFactory.create(
      name = "ScreenBStore",
      initialState = ScreenBState(0),
      executorFactory = executorFactory,
      reducer = reducer,
    ) {}
    counterNoServive.counter.subscribe {
      CoroutineScope(Dispatchers.Main).launch {
        store.accept(ScreenBIntent.CountChange(it))
      }
    }
    return store
  }
}


sealed interface ScreenBIntent {
  data object BackToScreenA : ScreenBIntent
  data class CountChange(val count: Int) : ScreenBIntent
}

data class ScreenBState(
  val count: Int,
)

sealed interface ScreenBLabel {
  data object BackToScreenA : ScreenBLabel
}