package mikufan.cx.conduit.frontend.app.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import mikufan.cx.conduit.frontend.ui.TestMainUI

fun main(args: Array<String>) = application {
  Window(
    onCloseRequest = ::exitApplication,
    title = "Conduit Desktop"
  ) {
    TestMainUI()
  }
}