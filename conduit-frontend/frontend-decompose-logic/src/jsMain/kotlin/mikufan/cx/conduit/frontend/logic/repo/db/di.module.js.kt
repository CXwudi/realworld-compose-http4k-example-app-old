package mikufan.cx.conduit.frontend.logic.repo.db

import org.koin.dsl.module

internal actual val dbDriverProvider = module {
  // https://stackoverflow.com/a/78651086/8529009
  provideDbDriver(Database.Schema) { driver ->
    single { driver }
  }
}