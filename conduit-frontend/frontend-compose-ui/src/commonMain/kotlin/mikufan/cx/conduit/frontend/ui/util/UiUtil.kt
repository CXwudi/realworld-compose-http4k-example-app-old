package mikufan.cx.conduit.frontend.ui.util

import androidx.compose.runtime.Composable
import mikufan.cx.conduit.frontend.ui.theme.AppTheme
import mikufan.cx.conduit.frontend.ui.theme.WithProperSize


@Composable
fun SetupUI(
  content: @Composable () -> Unit
) {
  WithProperSize {
    AppTheme {
      content()
    }
  }
}