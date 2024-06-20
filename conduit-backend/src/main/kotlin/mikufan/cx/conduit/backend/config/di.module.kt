package mikufan.cx.conduit.backend.config

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val configModule = module {
  // in the future, replace it with config library reading from various sources
  singleOf(::LOCAL_CONFIG)
}