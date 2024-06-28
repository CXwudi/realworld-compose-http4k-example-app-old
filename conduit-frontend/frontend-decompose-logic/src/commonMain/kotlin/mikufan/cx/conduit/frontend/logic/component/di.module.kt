package mikufan.cx.conduit.frontend.logic.component

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
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

val storeModule = module {
  single<StoreFactory> { LoggingStoreFactory(DefaultStoreFactory()) }
}
