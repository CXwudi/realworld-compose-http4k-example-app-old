package mikufan.cx.conduit.backend

import mikufan.cx.conduit.backend.config.configModule
import mikufan.cx.conduit.backend.controller.allHttpSetupModule
import mikufan.cx.conduit.backend.db.dbModule
import mikufan.cx.conduit.backend.service.serviceModule
import org.koin.core.KoinApplication
import org.koin.core.logger.Level
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.logger.slf4jLogger

val bootstrapModule = module {
  singleOf(::Bootstrap)
}

val allModules = listOf(
  configModule, // configuration layer, can be used by anyone in any later
  dbModule, // repo layer, but mostly just db related
  serviceModule, // service layer, handling business logic
  allHttpSetupModule, // controller layer
  bootstrapModule // bootstrap layer
)

fun initKoin(): KoinApplication {
  val koin = koinApplication {
    modules(allModules)
    slf4jLogger(Level.DEBUG)
    createEagerInstances()
  }
  return koin
}

fun main(args: Array<String>) {
  val koinApp = initKoin()
  koinApp.koin.get<Bootstrap>().run()
  koinApp.close()
}
