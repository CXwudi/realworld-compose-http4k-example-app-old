package mikufan.cx.conduit.backend.config

import org.koin.dsl.module

val configModule = module {
  // in the future, replace it with config library reading from various sources
  single { LOCAL_CONFIG }
}