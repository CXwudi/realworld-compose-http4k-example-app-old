package mikufan.cx.conduit.frontend.ui.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import mikufan.cx.conduit.frontend.logic.landing.LandingPageComponent
import mikufan.cx.conduit.frontend.logic.landing.LandingPageIntent

@Composable
fun LandingPage(component: LandingPageComponent, modifier: Modifier = Modifier) {

  val state by component.state.subscribeAsState()
  val urlText by remember { derivedStateOf { state.url } }

  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
  ) {
    OutlinedTextField(
      value = urlText,
      label = { Text("URL") },
      onValueChange = { component.send(LandingPageIntent.TextChanged(it)) })
  }
}