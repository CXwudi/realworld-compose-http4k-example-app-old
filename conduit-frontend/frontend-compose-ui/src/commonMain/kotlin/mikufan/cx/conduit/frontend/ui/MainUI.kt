package mikufan.cx.conduit.frontend.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mikufan.cx.conduit.frontend.logic.component.DefaultRootNavComponent
import mikufan.cx.conduit.frontend.ui.theme.AppTheme
import org.koin.compose.KoinContext
import org.koin.core.Koin

@Composable
fun MainUI(
  koin: Koin,
  rootComponent: DefaultRootNavComponent,
) {
  KoinContext(context = koin) { // currently unused, but added in case if we need it
    AppTheme {
      RootScreen(rootComponent)
    }
  }
}

@Composable
fun RootScreen(rootComponent: DefaultRootNavComponent, modifier: Modifier = Modifier) {

}
