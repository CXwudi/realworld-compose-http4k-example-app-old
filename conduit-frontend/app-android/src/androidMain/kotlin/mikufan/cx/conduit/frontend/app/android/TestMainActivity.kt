package mikufan.cx.conduit.frontend.app.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import mikufan.cx.conduit.frontend.logic.DefaultRootComponent
import mikufan.cx.conduit.frontend.ui.RootScreen
import mikufan.cx.conduit.frontend.ui.TestMainUI

class TestMainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val storeFactory = DefaultStoreFactory()

    val defaultComponentContext = defaultComponentContext()
    val rootComponent = DefaultRootComponent(defaultComponentContext, storeFactory)
    setContent {
      TestMainUI {
        RootScreen(rootComponent)
      }
    }
  }
}