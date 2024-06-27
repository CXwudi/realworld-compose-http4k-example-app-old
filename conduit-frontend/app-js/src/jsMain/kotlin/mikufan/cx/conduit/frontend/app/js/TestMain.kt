package mikufan.cx.conduit.frontend.app.js

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import mikufan.cx.conduit.frontend.logic.DefaultRootComponent
import mikufan.cx.conduit.frontend.ui.RootScreen
import mikufan.cx.conduit.frontend.ui.TestMainUI
import org.jetbrains.skiko.wasm.onWasmReady
import web.dom.DocumentVisibilityState
import web.dom.document
import web.events.EventType
import web.events.addEventListener

@OptIn(ExperimentalComposeUiApi::class)
fun main(args: Array<String>) {

  val storeFactory = DefaultStoreFactory()

  val lifecycle = LifecycleRegistry()
  val defaultComponentContext = DefaultComponentContext(
    lifecycle = lifecycle
  )
  val rootComponent = DefaultRootComponent(defaultComponentContext, storeFactory)

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