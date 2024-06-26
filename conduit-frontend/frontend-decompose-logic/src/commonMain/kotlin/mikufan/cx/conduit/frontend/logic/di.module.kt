package mikufan.cx.conduit.frontend.logic

import com.arkivanov.essenty.instancekeeper.InstanceKeeperDispatcher
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.statekeeper.StateKeeperDispatcher
import org.koin.dsl.module

val essentyModule = module {
  single { LifecycleRegistry() }
  single { StateKeeperDispatcher() }
  single { InstanceKeeperDispatcher() }
}