package mikufan.cx.conduit.frontend.logic

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import mikufan.cx.conduit.frontend.logic.service.SampleCounterNoServive
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * This provides all the essential modules binded to the [DefaultComponentContext],
 * which usually have a lifecycle equivalent to the whole application
 */
val essentyModule = module {
  single { get<DefaultComponentContext>().lifecycle }
  single { get<DefaultComponentContext>().stateKeeper }
  single { get<DefaultComponentContext>().instanceKeeper }
}

val serviceModule = module {
  single { SampleCounterNoServive() }
}

val storeModule = module {
  single<StoreFactory> { LoggingStoreFactory(DefaultStoreFactory()) }
  singleOf(::ScreenAStoreFactory)
  singleOf(::ScreenBStoreFactory)
}

val allModules = listOf(essentyModule, serviceModule, storeModule)