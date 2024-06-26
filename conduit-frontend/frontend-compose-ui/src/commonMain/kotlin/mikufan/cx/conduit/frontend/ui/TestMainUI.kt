package mikufan.cx.conduit.frontend.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import mikufan.cx.conduit.frontend.logic.RootComponent
import mikufan.cx.conduit.frontend.logic.RootComponentChild
import mikufan.cx.conduit.frontend.logic.ScreenAComponent
import mikufan.cx.conduit.frontend.logic.ScreenAIntent
import mikufan.cx.conduit.frontend.logic.ScreenBComponent
import mikufan.cx.conduit.frontend.logic.ScreenBIntent

@Composable
fun TestMainUI(
  content: @Composable () -> Unit
) {
  MaterialTheme {
    content()
  }
}

@Composable
fun RootScreen(component: RootComponent, modifier: Modifier = Modifier) {
  Children(
    stack = component.child,
    modifier = modifier,
    animation = stackAnimation(fade())
  ) {

    when (val child = it.instance) {
      is RootComponentChild.ScreenA -> ScreenA(child.component)
      is RootComponentChild.ScreenB -> ScreenB(child.component)
    }
  }

}

@Composable
fun ScreenA(
  component: ScreenAComponent
) = Column {
  Text("Screen A")

  val state by component.state.subscribeAsState()
  val text by remember {
    derivedStateOf { state.text }
  }

  OutlinedTextField(
    value = text,
    onValueChange = { component.send(ScreenAIntent.TextChange(it)) }
  )
  Button({ component.send(ScreenAIntent.ToScreenB) }) {
    Text("Navigate to Screen B")
  }
}

@Composable
fun ScreenB(component: ScreenBComponent) = Column {
  Text("Screen B: ")
  Text(component.id)
  Button({ component.send(ScreenBIntent.BackToScreenA) }) {
    Text("Back")
  }
}