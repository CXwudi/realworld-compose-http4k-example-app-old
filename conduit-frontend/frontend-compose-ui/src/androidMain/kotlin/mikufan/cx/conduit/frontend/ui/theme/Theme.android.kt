package mikufan.cx.conduit.frontend.ui.theme

import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * Determine the color scheme in Android.
 * If [dynamicColor] is `true` and at least Android 12, we enable the dynamic color.
 * Otherwise, delegate to [determineSchema_common]
 */
@Composable
actual fun determineScheme(
  dynamicColor: Boolean,
  darkTheme: Boolean
): ColorScheme {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }
    else -> determineSchema_common(darkTheme)
  }
  return colorScheme
}