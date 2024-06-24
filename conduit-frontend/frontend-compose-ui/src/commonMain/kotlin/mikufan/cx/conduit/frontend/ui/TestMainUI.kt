package mikufan.cx.conduit.frontend.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import mikufan.cx.conduit.frontend.logic.RootComponent
import mikufan.cx.conduit.frontend.logic.ScreenAComponent
import mikufan.cx.conduit.frontend.logic.ScreenBComponent

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
      is RootComponent.RootComponentChild.ScreenA -> ScreenA(child.component)
      is RootComponent.RootComponentChild.ScreenB -> ScreenB(child.component)
    }
  }

}

@Composable
fun ScreenA(
  component: ScreenAComponent
) = Column {
  Text("Screen A")
  var text by remember { mutableStateOf("") }
  OutlinedTextField(
    value = text,
    onValueChange = { text = it }
  )
  Button({ component.toScreenB(text) }) {
    Text("Navigate to Screen B")
  }
}

@Composable
fun ScreenB(component: ScreenBComponent) = Column {
  Text("Screen B: ")
  Text(component.id)
  Button({ component.onBack() }) {
    Text("Back")
  }
}