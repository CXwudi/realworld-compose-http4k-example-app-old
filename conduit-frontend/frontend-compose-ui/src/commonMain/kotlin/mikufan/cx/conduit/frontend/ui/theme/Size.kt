package mikufan.cx.conduit.frontend.ui.theme

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import mikufan.cx.conduit.frontend.ui.util.LocalWindowSize

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun WithProperSize(
  content: @Composable () -> Unit
) {
  val windowSizeClass = calculateWindowSizeClass()
  CompositionLocalProvider(
    LocalWindowSize provides windowSizeClass,
  ) {
    content()
  }
}