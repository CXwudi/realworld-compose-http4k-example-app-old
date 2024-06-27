package mikufan.cx.conduit.frontend.app.android

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import mikufan.cx.conduit.frontend.logic.DefaultRootComponent
import mikufan.cx.conduit.frontend.logic.essentyModule
import mikufan.cx.conduit.frontend.ui.RootScreen
import mikufan.cx.conduit.frontend.ui.TestMainUI
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.dsl.module

fun initKoin(componentContext: DefaultComponentContext, ctx: Context) = koinApplication {
  androidContext(ctx)
  modules(module {
    single { componentContext }
  })
  modules(essentyModule)
}

class TestMainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val defaultComponentContext = defaultComponentContext()
    val koin = initKoin(defaultComponentContext, this@TestMainActivity).koin

    val rootComponent = DefaultRootComponent(defaultComponentContext, koin.get())
    setContent {
      TestMainUI {
        RootScreen(rootComponent)
      }
    }
  }
}