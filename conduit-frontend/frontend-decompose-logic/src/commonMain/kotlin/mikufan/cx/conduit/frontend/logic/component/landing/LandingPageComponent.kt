package mikufan.cx.conduit.frontend.logic.component.landing

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import mikufan.cx.conduit.frontend.logic.component.util.LocalKoinComponent
import mikufan.cx.conduit.frontend.logic.component.util.MviComponent
import mikufan.cx.conduit.frontend.logic.component.util.stateValue

interface LandingPageComponent : MviComponent<LandingPageIntent, LandingPageState> {
}

class DefaultLandingPageComponent(
  componentContext: ComponentContext,
  private val koinComponent: LocalKoinComponent,
  storeFactory: LandingPageStoreFactory,
) : LandingPageComponent, ComponentContext by componentContext {

  private val store = instanceKeeper.getStore {
    storeFactory.createStore()
  }

  init {
    store.labels.onEach {
      // TODO: wire button click to navigation to main screen
    }.launchIn(CoroutineScope(Dispatchers.Main))
  }

  override val state: Value<LandingPageState> = store.stateValue

  override fun send(intent: LandingPageIntent) = store.accept(intent)
}
