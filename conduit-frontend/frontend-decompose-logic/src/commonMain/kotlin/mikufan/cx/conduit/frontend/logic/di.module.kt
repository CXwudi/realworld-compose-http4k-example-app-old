package mikufan.cx.conduit.frontend.logic

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import org.koin.dsl.module

val essentyModule = module {
  single { get<DefaultComponentContext>().lifecycle }
  single { get<DefaultComponentContext>().stateKeeper }
  single { get<DefaultComponentContext>().instanceKeeper }
  single<StoreFactory> { DefaultStoreFactory() }
}