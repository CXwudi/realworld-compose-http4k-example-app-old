package mikufan.cx.conduit.frontend.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
actual fun determineScheme(
  dynamicColor: Boolean,
  darkTheme: Boolean
): ColorScheme = determineSchema_common(darkTheme)