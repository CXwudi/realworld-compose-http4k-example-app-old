package mikufan.cx.conduit.backend.service

import org.koin.dsl.module

/**
 * require [dbModule]
 */
val serviceModule = module {
  single<UserService> { UserService(get(), get()) }
}