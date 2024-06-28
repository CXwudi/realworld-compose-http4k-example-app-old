package mikufan.cx.conduit.frontend.app.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import mikufan.cx.conduit.frontend.app.desktop.util.runOnUiThread
import mikufan.cx.conduit.frontend.logic.allModules
import mikufan.cx.conduit.frontend.logic.component.DefaultRootNavComponent
import mikufan.cx.conduit.frontend.logic.util.toLocalKoinComponent
import mikufan.cx.conduit.frontend.ui.MainUI
import mikufan.cx.conduit.frontend.ui.RootScreen
import org.koin.dsl.koinApplication
import org.koin.dsl.module

fun initKoin(componentContext: DefaultComponentContext) = koinApplication {
  modules(module {
    single { componentContext }
  })
  modules(allModules)
}

fun main(args: Array<String>) {
  val lifecycle = LifecycleRegistry()
  val defaultComponentContext = runOnUiThread {
    DefaultComponentContext(lifecycle = lifecycle)
  }
  val koin = initKoin(defaultComponentContext).koin
  val root = runOnUiThread {
    DefaultRootNavComponent(defaultComponentContext, koin.toLocalKoinComponent())
  }

  application {

    val windowState = rememberWindowState()

    LifecycleController(lifecycle, windowState)
    Window(
      onCloseRequest = ::exitApplication,
      title = "Conduit Desktop"
    ) {
      MainUI {
        RootScreen(root)
      }
    }
  }
}