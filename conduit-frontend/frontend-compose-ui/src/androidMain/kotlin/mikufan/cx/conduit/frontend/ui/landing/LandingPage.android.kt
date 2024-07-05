package mikufan.cx.conduit.frontend.ui.landing

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import mikufan.cx.conduit.frontend.logic.landing.LandingPageComponent
import mikufan.cx.conduit.frontend.logic.landing.LandingPageIntent
import mikufan.cx.conduit.frontend.logic.landing.LandingPageState
import mikufan.cx.conduit.frontend.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun PreviewLandingPage() {
  AppTheme {
    val mockComponent = object : LandingPageComponent {
      override val state: Value<LandingPageState> = MutableValue(LandingPageState("bla bla URL"))

      override fun send(intent: LandingPageIntent) {
      }
    }
    LandingPage(mockComponent)
  }
}