package mikufan.cx.conduit.backend

import mikufan.cx.conduit.backend.config.configModule
import mikufan.cx.conduit.backend.db.dbModule
import mikufan.cx.inlinelogging.KInlineLogging
import org.koin.core.KoinApplication
import org.koin.core.logger.Level
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.logger.slf4jLogger


val bootstrapModule = module {
  singleOf(::Bootstrap)
}

val allModules = listOf(configModule, dbModule, bootstrapModule)

fun initKoin(): KoinApplication {
  val koin = koinApplication {
    modules(allModules)
    slf4jLogger(level = Level.INFO)
  }
  return koin
}

fun main(args: Array<String>) {
  val koinApp = initKoin()
  koinApp.koin.get<Bootstrap>().run()
  koinApp.close()
}

private val log = KInlineLogging.logger()