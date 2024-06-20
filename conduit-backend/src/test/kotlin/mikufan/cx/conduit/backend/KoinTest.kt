package mikufan.cx.conduit.backend

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.ShouldSpec
import org.koin.dsl.koinApplication


class KoinTest : ShouldSpec({
  context("koin modules") {
    should("load successfully") {
      shouldNotThrow<Exception> {
        koinApplication {
          modules(allModules)
        }.koin
      }
    }
  }
})