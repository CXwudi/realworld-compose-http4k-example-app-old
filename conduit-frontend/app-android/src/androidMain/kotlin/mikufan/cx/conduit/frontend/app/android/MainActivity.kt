package mikufan.cx.conduit.frontend.app.android

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import mikufan.cx.conduit.frontend.logic.allModules
import mikufan.cx.conduit.frontend.logic.component.DefaultRootNavComponent
import mikufan.cx.conduit.frontend.logic.util.toLocalKoinComponent
import mikufan.cx.conduit.frontend.ui.MainUI
import mikufan.cx.conduit.frontend.ui.RootScreen
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.dsl.module

fun initKoin(componentContext: DefaultComponentContext, ctx: Context) = koinApplication {
  androidContext(ctx)
  modules(module {
    single { componentContext }
  })
  modules(allModules)
}

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.i(this@MainActivity::class.simpleName, "onCreate")

    val defaultComponentContext = defaultComponentContext()

    val koin = initKoin(defaultComponentContext, this@MainActivity).koin

    val rootComponent = DefaultRootNavComponent(defaultComponentContext, koin.toLocalKoinComponent())
    setContent {
      MainUI {
        RootScreen(rootComponent)
      }
    }
  }
}