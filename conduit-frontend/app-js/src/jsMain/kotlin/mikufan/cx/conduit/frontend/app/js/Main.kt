package mikufan.cx.conduit.frontend.app.js

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import mikufan.cx.conduit.frontend.logic.allModules
import mikufan.cx.conduit.frontend.logic.component.DefaultRootNavComponent
import mikufan.cx.conduit.frontend.logic.util.toLocalKoinComponent
import org.jetbrains.skiko.wasm.onWasmReady
import org.koin.dsl.koinApplication
import web.dom.DocumentVisibilityState
import web.dom.document
import web.events.EventType
import web.events.addEventListener

fun initKoin() = koinApplication {
  modules(allModules)
}


@OptIn(ExperimentalComposeUiApi::class)
fun main(args: Array<String>) {

  val lifecycle = LifecycleRegistry()
  val defaultComponentContext = DefaultComponentContext(
    lifecycle = lifecycle
  )

  val koin = initKoin().koin
  val rootComponent = DefaultRootNavComponent(defaultComponentContext, koin.toLocalKoinComponent())

  lifecycle.attachToDocument()

  onWasmReady {
    CanvasBasedWindow("Conduit") {

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