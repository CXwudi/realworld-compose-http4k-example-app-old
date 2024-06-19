package mikufan.cx.conduit.backend

import mikufan.cx.conduit.backend.config.configModule
import mikufan.cx.conduit.backend.db.dbModule
import mikufan.cx.inlinelogging.KInlineLogging
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication


val allModules = listOf(configModule, dbModule)

fun initKoin(): KoinApplication {
  val koin = koinApplication {
    modules(allModules)
  }
  return koin
}

fun main(args: Array<String>) {
  val koin = initKoin()
}

private val log = KInlineLogging.logger()