package mikufan.cx.conduit.frontend.logic.service

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.statekeeper.StateKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.serializer


class SampleCounterService(
  stateKeeper: StateKeeper,
) {
  private val _counter = MutableValue(stateKeeper.consume("_counter", Int.serializer()) ?: 0)
  val counter: Value<Int>
    get() = _counter

  init {
    stateKeeper.register("_counter", Int.serializer()) {
      _counter.value
    }
    CoroutineScope(Dispatchers.Default).launch {
      flow {
        while (true) {
          delay(1000)
          emit(Unit)
        }
      }.collect {
        _counter.value++
      }
    }
  }
}

class SampleCounterNoServive {

  private val _counter = MutableValue(0)
  val counter: Value<Int>
    get() = _counter

  init {
    CoroutineScope(Dispatchers.Default).launch {
      flow {
        while (true) {
          delay(1000)
          emit(Unit)
        }
      }.collect {
        _counter.value++
      }
    }
  }
}