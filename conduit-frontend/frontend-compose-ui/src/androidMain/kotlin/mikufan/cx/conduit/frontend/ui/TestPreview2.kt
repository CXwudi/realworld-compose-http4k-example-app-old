package mikufan.cx.conduit.frontend.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import mikufan.cx.conduit.frontend.logic.RootComponent
import mikufan.cx.conduit.frontend.logic.RootComponentChild
import mikufan.cx.conduit.frontend.logic.ScreenAComponent
import mikufan.cx.conduit.frontend.logic.ScreenAIntent
import mikufan.cx.conduit.frontend.logic.ScreenAState

@Composable
@Preview
fun TestPreview() {
  TestMainUI {
    ScreenA(object : ScreenAComponent {
      override val state: Value<ScreenAState> = MutableValue(ScreenAState("Test"))

      override fun send(intent: ScreenAIntent) {
      }

    })
  }
}