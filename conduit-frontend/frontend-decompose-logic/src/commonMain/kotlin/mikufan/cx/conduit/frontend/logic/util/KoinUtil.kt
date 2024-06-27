package mikufan.cx.conduit.frontend.logic.util

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

interface LocalKoinComponent : KoinComponent {
  val localKoin: Koin
  override fun getKoin() = localKoin
}