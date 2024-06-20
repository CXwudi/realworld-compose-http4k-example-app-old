package mikufan.cx.conduit.backend.db

import mikufan.cx.conduit.backend.config.Config
import mikufan.cx.conduit.backend.config.configModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * require [configModule]
 */
val dbModule = module {
  single { creatDataSource(get<Config>().db) }
  singleOf(::createFlyway)
  singleOf(::createExposedDb)
  singleOf(::createTransactionManager)
}