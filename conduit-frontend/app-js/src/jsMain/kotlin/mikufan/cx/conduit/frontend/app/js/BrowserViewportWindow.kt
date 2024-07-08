package mikufan.cx.conduit.frontend.app.js

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLStyleElement

/**
 * A Skiko/Canvas-based top-level window using the browser's entire viewport. Supports resizing.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Suppress("FunctionName")
fun BrowserViewportWindow(
  title: String,
  canvasElementId: String = "ComposeTarget",
  content: @Composable () -> Unit,
) {
  val htmlHeadElement = document.head!!
  htmlHeadElement.appendChild(
    (document.createElement("style") as HTMLStyleElement).apply {
      type = "text/css"
      appendChild(
        document.createTextNode(
          """
                    html, body {
                        overflow: hidden;
                        margin: 0 !important;
                        padding: 0 !important;
                    }

                    #$canvasElementId {
                        outline: none;
                    }
                    """.trimIndent(),
        ),
      )
    },
  )

  fun HTMLCanvasElement.fillViewportSize() {
    setAttribute("width", "${window.innerWidth}")
    setAttribute("height", "${window.innerHeight}")
  }

  (document.getElementById(canvasElementId) as HTMLCanvasElement).apply {
    fillViewportSize()
  }

  // WORKAROUND: ComposeWindow does not implement `setTitle(title)`
  val titleElement = htmlHeadElement.getElementsByTagName("title").item(0)
    ?: document.createElement("title").also { htmlHeadElement.appendChild(it) }
  titleElement.textContent = title

  CanvasBasedWindow(title = title, canvasElementId = canvasElementId) {
    content()
  }
}