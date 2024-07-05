package mikufan.cx.conduit.frontend.logic.landing

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import mikufan.cx.conduit.frontend.logic.util.LocalKoinComponent
import mikufan.cx.conduit.frontend.logic.util.MviComponent
import mikufan.cx.conduit.frontend.logic.util.stateValue

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

  override val state: Value<LandingPageState> = store.stateValue

  override fun send(intent: LandingPageIntent) = store.accept(intent)
}
