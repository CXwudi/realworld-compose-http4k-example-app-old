package mikufan.cx.conduit.frontend.logic

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import mikufan.cx.conduit.frontend.logic.landing.LandingPageStoreFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val storeModule = module {
  single<StoreFactory> { LoggingStoreFactory(DefaultStoreFactory()) }
  singleOf(::LandingPageStoreFactory)
}

val allModules = listOf(storeModule)