package mikufan.cx.conduit.common

import kotlinx.serialization.Serializable

@Serializable
data class TestPojo(
  val id: Int,
  val name: String
)
