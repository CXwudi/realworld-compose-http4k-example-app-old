package mikufan.cx.conduit.frontend.app.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import mikufan.cx.conduit.frontend.app.desktop.util.runOnUiThread
import mikufan.cx.conduit.frontend.logic.DefaultRootComponent
import mikufan.cx.conduit.frontend.ui.RootScreen
import mikufan.cx.conduit.frontend.ui.TestMainUI

fun main(args: Array<String>) {
  val storeFactory = DefaultStoreFactory()

  val lifecycle = LifecycleRegistry()
  val root = runOnUiThread {
    val defaultComponentContext = DefaultComponentContext(lifecycle = lifecycle)
    DefaultRootComponent(defaultComponentContext, storeFactory)
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