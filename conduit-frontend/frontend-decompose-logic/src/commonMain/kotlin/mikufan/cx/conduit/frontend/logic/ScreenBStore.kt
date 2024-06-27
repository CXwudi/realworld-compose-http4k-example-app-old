package mikufan.cx.conduit.frontend.logic

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory

interface ScreenBStore : Store<ScreenBIntent, ScreenBState, ScreenBLabel>

class ScreenBStoreFactory(
  private val storeFactory: StoreFactory,
) {

  private val executorFactory =
    coroutineExecutorFactory<ScreenBIntent, Nothing, ScreenBState, Nothing, ScreenBLabel> {
      onIntent<ScreenBIntent.BackToScreenA> {
        publish(ScreenBLabel.BackToScreenA)
      }
    }

  fun createStore(): ScreenBStore = object : ScreenBStore,
    Store<ScreenBIntent, ScreenBState, ScreenBLabel> by storeFactory.create(
      name = "ScreenBStore",
      initialState = ScreenBState,
      executorFactory = executorFactory
    ) {}
}


sealed interface ScreenBIntent {
  data object BackToScreenA : ScreenBIntent
}

data object ScreenBState

sealed interface ScreenBLabel {
  data object BackToScreenA : ScreenBLabel
}