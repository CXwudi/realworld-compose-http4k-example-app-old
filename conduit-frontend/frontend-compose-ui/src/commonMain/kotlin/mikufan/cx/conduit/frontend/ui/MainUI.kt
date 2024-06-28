package mikufan.cx.conduit.frontend.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mikufan.cx.conduit.frontend.logic.component.RootNavComponent

@Composable
fun MainUI(
  content: @Composable () -> Unit
) {
  MaterialTheme {
    content()
  }
}

@Composable
fun RootScreen(component: RootNavComponent, modifier: Modifier = Modifier) {

}
