package mikufan.cx.conduit.frontend.ui.theme

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Space(
  val horizontal: HorizontalSpace,
  val vertical: VerticalSpace,
)

data class HorizontalSpace(
  val spacing: Dp,
  val spacingSmall: Dp,
  val spacingLarge: Dp,
  val padding: Dp,
  val paddingSmall: Dp,
  val paddingLarge: Dp,
)

data class VerticalSpace(
  val spacing: Dp,
  val spacingSmall: Dp,
  val spacingLarge: Dp,
  val padding: Dp,
  val paddingSmall: Dp,
  val paddingLarge: Dp,
)

val LocalSpace = compositionLocalOf<Space> { error("No LocalSpace provided") }

val compactHorizontalSpace = HorizontalSpace(
  spacing = 3.dp,
  spacingSmall = 2.dp,
  spacingLarge = 4.dp,
  padding = 3.dp,
  paddingSmall = 2.dp,
  paddingLarge = 4.dp
)

val mediumHorizontalSpace = HorizontalSpace(
  spacing = 4.dp,
  spacingSmall = 3.dp,
  spacingLarge = 6.dp,
  padding = 4.dp,
  paddingSmall = 3.dp,
  paddingLarge = 6.dp
)

val expandedHorizontalSpace = HorizontalSpace(
  spacing = 6.dp,
  spacingSmall = 4.dp,
  spacingLarge = 8.dp,
  padding = 6.dp,
  paddingSmall = 4.dp,
  paddingLarge = 8.dp
)

val compactVerticalSpace = VerticalSpace(
  spacing = 3.dp,
  spacingSmall = 2.dp,
  spacingLarge = 4.dp,
  padding = 3.dp,
  paddingSmall = 2.dp,
  paddingLarge = 4.dp
)

val medianVerticalSpace = VerticalSpace(
  spacing = 4.dp,
  spacingSmall = 3.dp,
  spacingLarge = 6.dp,
  padding = 4.dp,
  paddingSmall = 3.dp,
  paddingLarge = 6.dp
)

val mediumVerticalSpace = VerticalSpace(
  spacing = 6.dp,
  spacingSmall = 4.dp,
  spacingLarge = 8.dp,
  padding = 6.dp,
  paddingSmall = 4.dp,
  paddingLarge = 8.dp
)

fun WindowSizeClass.calculateSpace(): Space {
  val horizontalSpace = when (widthSizeClass) {
    WindowWidthSizeClass.Compact -> compactHorizontalSpace
    WindowWidthSizeClass.Expanded -> expandedHorizontalSpace
    else -> mediumHorizontalSpace
  }

  // so far we just let the vertical space be the same with the horizontal space
  val verticalSpace = when (widthSizeClass) {
    WindowWidthSizeClass.Compact -> compactVerticalSpace
    WindowWidthSizeClass.Expanded -> mediumVerticalSpace
    else -> medianVerticalSpace
  }

  return Space(horizontal = horizontalSpace, vertical = verticalSpace)
}