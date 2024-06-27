package mikufan.cx.conduit.frontend.app.js

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import mikufan.cx.conduit.frontend.logic.DefaultRootComponent
import mikufan.cx.conduit.frontend.logic.allModules
import mikufan.cx.conduit.frontend.logic.util.toComponent
import mikufan.cx.conduit.frontend.ui.RootScreen
import mikufan.cx.conduit.frontend.ui.TestMainUI
import org.jetbrains.skiko.wasm.onWasmReady
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import web.dom.DocumentVisibilityState
import web.dom.document
import web.events.EventType
import web.events.addEventListener

fun initKoin(componentContext: DefaultComponentContext) = koinApplication {
  modules(module {
    single { componentContext }
  })
  modules(allModules)
}


@OptIn(ExperimentalComposeUiApi::class)
fun main(args: Array<String>) {

  val lifecycle = LifecycleRegistry()
  val defaultComponentContext = DefaultComponentContext(
    lifecycle = lifecycle
  )

  val koin = initKoin(defaultComponentContext).koin
  val rootComponent = DefaultRootComponent(defaultComponentContext, koin.toComponent())

  lifecycle.attachToDocument()

  onWasmReady {
    CanvasBasedWindow("Conduit") {
      TestMainUI {
        RootScreen(rootComponent)
      }
    }
  }
}

private fun LifecycleRegistry.attachToDocument() {
  fun onVisibilityChanged() {
    if (document.visibilityState == DocumentVisibilityState.visible) {
      resume()
    } else {
      stop()
    }
  }

  onVisibilityChanged()

  document.addEventListener(type = EventType("visibilitychange"), handler = { onVisibilityChanged() })
}