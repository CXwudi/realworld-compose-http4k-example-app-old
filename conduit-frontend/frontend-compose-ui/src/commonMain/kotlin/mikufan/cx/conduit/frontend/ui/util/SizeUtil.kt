package mikufan.cx.conduit.frontend.ui.util

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.compositionLocalOf

val LocalWindowSize = compositionLocalOf<WindowSizeClass> { error("No LocalWindowSize provided") }