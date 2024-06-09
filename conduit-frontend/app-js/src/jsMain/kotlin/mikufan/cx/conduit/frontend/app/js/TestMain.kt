package mikufan.cx.conduit.frontend.app.js

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import mikufan.cx.conduit.frontend.ui.TestMainUI
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main(args: Array<String>) {
  onWasmReady {
    CanvasBasedWindow("Conduit") {
      TestMainUI()
    }
  }
}