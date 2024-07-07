package mikufan.cx.conduit.frontend.ui

import androidx.compose.runtime.Composable
import mikufan.cx.conduit.frontend.logic.component.DefaultRootNavComponent
import mikufan.cx.conduit.frontend.ui.screen.RootNavigation
import mikufan.cx.conduit.frontend.ui.util.SetupUI
import org.koin.compose.KoinContext
import org.koin.core.Koin

@Composable
fun MainUI(
  koin: Koin,
  rootComponent: DefaultRootNavComponent,
) {
  KoinContext(context = koin) { // currently unused, but added in case if we need it
    SetupUI {
      RootNavigation(rootComponent)
    }
  }
}

