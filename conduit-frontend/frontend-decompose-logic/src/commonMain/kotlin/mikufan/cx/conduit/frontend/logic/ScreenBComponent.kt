package mikufan.cx.conduit.frontend.logic

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.rx.observer
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.core.store.create
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory

interface ScreenBComponent {
  val id: String
  fun sendIntent(intent: ScreenBIntent)
}

sealed interface ScreenBIntent {
  data object BackToScreenA : ScreenBIntent
}

class DefaultScreenBComponent(
  override val id: String,
  val componentContext: ComponentContext,
  val storeFactory: StoreFactory,
  val onBackPressed: () -> Unit,
) : ScreenBComponent, ComponentContext by componentContext {

  private val store = instanceKeeper.getStore {
    storeFactory.create<ScreenBIntent, Nothing, Nothing, ScreenBState, ScreenBLabel>(
      name = "ScreenBStore",
      initialState = ScreenBState,
      executorFactory = coroutineExecutorFactory {
        onIntent<ScreenBIntent.BackToScreenA> {
          publish(ScreenBLabel.BackToScreenA)
        }
      }
    )
  }

  init {
    store.labels(observer {
      onBackPressed()
    })
  }

  override fun sendIntent(intent: ScreenBIntent) {
    store.accept(intent)
  }

}

data object ScreenBState

sealed interface ScreenBLabel {
  object BackToScreenA : ScreenBLabel
}