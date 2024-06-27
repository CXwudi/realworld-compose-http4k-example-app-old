package mikufan.cx.conduit.frontend.app.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import mikufan.cx.conduit.frontend.app.desktop.util.runOnUiThread
import mikufan.cx.conduit.frontend.logic.DefaultRootComponent
import mikufan.cx.conduit.frontend.logic.essentyModule
import mikufan.cx.conduit.frontend.ui.RootScreen
import mikufan.cx.conduit.frontend.ui.TestMainUI
import org.koin.dsl.koinApplication
import org.koin.dsl.module

fun initKoin(componentContext: DefaultComponentContext) = koinApplication {
  modules(module {
    single { componentContext }
  })
  modules(essentyModule)
}

fun main(args: Array<String>) {
  val lifecycle = LifecycleRegistry()
  val defaultComponentContext = runOnUiThread {
    DefaultComponentContext(lifecycle = lifecycle)
  }
  val koin = initKoin(defaultComponentContext).koin
  val storeFactory = koin.get<StoreFactory>()
  val root = runOnUiThread {
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