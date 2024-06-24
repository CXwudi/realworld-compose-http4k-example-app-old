package mikufan.cx.conduit.frontend.app.desktop

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import mikufan.cx.conduit.frontend.app.desktop.util.runOnUiThread
import mikufan.cx.conduit.frontend.logic.DefaultRootComponent
import mikufan.cx.conduit.frontend.ui.RootScreen
import mikufan.cx.conduit.frontend.ui.TestMainUI
import javax.swing.SwingUtilities

fun main(args: Array<String>) {
  val lifecycle = LifecycleRegistry()

  val root = runOnUiThread {
    val defaultComponentContext = DefaultComponentContext(lifecycle = lifecycle)
    DefaultRootComponent(defaultComponentContext)
  }

  application {

    val windowState = rememberWindowState()

    LifecycleController(lifecycle, windowState)
    Window(
      onCloseRequest = ::exitApplication,
      title = "Conduit Desktop"
    ) {
      TestMainUI {
        RootScreen(root)
      }
    }
  }
}