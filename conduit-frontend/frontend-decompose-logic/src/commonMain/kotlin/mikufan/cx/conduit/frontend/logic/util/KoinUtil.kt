package mikufan.cx.conduit.frontend.logic.util

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

/**
 * Idea copied from https://gist.github.com/aartikov/30e182fd58ed9697af498bb22ef4edfa
 */
class LocalKoinComponent(
  private val localKoin: Koin
) : KoinComponent {
  override fun getKoin() = localKoin
}

fun Koin.toLocalKoinComponent() = LocalKoinComponent(this)