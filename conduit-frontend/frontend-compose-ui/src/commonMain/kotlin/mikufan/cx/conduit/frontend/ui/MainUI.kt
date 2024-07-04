package mikufan.cx.conduit.frontend.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mikufan.cx.conduit.frontend.logic.component.DefaultRootNavComponent
import mikufan.cx.conduit.frontend.ui.theme.AppTheme

@Composable
fun MainUI(
  rootComponent: DefaultRootNavComponent,
) {
  AppTheme {
    RootScreen(rootComponent)
  }
}

@Composable
fun RootScreen(rootComponent: DefaultRootNavComponent, modifier: Modifier = Modifier) {

}
