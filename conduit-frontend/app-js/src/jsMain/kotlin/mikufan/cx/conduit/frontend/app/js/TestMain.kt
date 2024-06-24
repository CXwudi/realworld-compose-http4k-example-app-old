package mikufan.cx.conduit.frontend.app.js

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import mikufan.cx.conduit.frontend.logic.DefaultRootComponent
import mikufan.cx.conduit.frontend.ui.RootScreen
import mikufan.cx.conduit.frontend.ui.TestMainUI
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main(args: Array<String>) {

  val lifecycle = LifecycleRegistry()
  val defaultComponentContext = DefaultComponentContext(
    lifecycle = lifecycle
  )
  val rootComponent = DefaultRootComponent(defaultComponentContext)

  onWasmReady {
    CanvasBasedWindow("Conduit") {
      TestMainUI {
        RootScreen(rootComponent)
      }
    }
  }
}