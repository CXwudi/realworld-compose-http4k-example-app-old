package mikufan.cx.conduit.frontend.logic.repo.db

import org.koin.core.module.Module
import org.koin.dsl.module

internal expect val dbDriverProvider: Module

// without this it will complain that dbDriverProvider is not initialized
fun getDbDriverProvider() = dbDriverProvider

val dbModule = module {
  includes(getDbDriverProvider())
}