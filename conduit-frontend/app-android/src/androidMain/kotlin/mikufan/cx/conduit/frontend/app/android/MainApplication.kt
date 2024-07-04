package mikufan.cx.conduit.frontend.app.android

import android.util.Log
import mikufan.cx.conduit.frontend.logic.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.dsl.koinApplication

class MainApplication : android.app.Application() {

  val koin: Koin by lazy {
    koinApplication {
      androidContext(this@MainApplication)
      modules(allModules)
    }.koin
  }

  override fun onCreate() {
    super.onCreate()
    Log.i(this@MainApplication::class.simpleName, "onCreate")
  }

}