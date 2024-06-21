package mikufan.cx.conduit.common

import kotlinx.serialization.Serializable

@Serializable
data class GenericErrorRsp(
  val message: ErrorBody
)

@Serializable
data class ErrorBody(
  val body: List<String>
)

fun ErrorRsp(list: List<String>): GenericErrorRsp =
  GenericErrorRsp(ErrorBody(list))

fun ErrorRsp(vararg list: String): GenericErrorRsp =
  GenericErrorRsp(ErrorBody(list.toList()))
