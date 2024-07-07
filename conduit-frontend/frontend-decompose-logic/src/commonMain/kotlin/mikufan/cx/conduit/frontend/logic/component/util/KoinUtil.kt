package mikufan.cx.conduit.frontend.logic.component.util

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

/**
 * A [KoinComponent] that stores a reference to the [Koin] instance in the [LocalKoinComponent].
 * Mainly used in Decompose component to get access to the [Koin] instance in a more test-friendly way.
 *
 * Idea copied from https://gist.github.com/aartikov/30e182fd58ed9697af498bb22ef4edfa
 */
class LocalKoinComponent(
  private val localKoin: Koin
) : KoinComponent {
  override fun getKoin() = localKoin
}

fun Koin.toLocalKoinComponent() = LocalKoinComponent(this)