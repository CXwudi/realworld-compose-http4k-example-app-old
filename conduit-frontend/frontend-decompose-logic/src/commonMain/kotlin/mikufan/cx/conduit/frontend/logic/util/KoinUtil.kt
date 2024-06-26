package mikufan.cx.conduit.frontend.logic.util

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

interface LocalKoinComponent : KoinComponent {
  val koin: Koin
  override fun getKoin() = koin
}