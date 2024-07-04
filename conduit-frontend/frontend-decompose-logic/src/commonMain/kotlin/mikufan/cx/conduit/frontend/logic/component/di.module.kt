package mikufan.cx.conduit.frontend.logic.component

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import org.koin.dsl.module

val storeModule = module {
  single<StoreFactory> { LoggingStoreFactory(DefaultStoreFactory()) }
}
