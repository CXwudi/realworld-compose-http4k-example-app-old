package mikufan.cx.conduit.frontend.logic.util

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

/**
 * A single MVI component that accept an [Intent] and update the exposed single [State] value.
 *
 * Suitable for [ComponentContext] that is used for a single UI.
 *
 * Probably not suitable for [ComponentContext] that is used for navigation.
 * Although you can and it will break the MVI pattern as you will have another exposed value for navigation.
 *
 * @param Intent
 * @param State
 */
interface MviComponent<Intent : Any, State : Any> {
  val state: Value<State>
  fun send(intent: Intent)
}