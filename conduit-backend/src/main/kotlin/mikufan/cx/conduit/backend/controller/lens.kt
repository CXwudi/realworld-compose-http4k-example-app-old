package mikufan.cx.conduit.backend.controller

import mikufan.cx.conduit.common.ErrorRsp
import mikufan.cx.conduit.common.GenericErrorRsp
import mikufan.cx.conduit.common.NewUserReq
import mikufan.cx.conduit.common.UserRsp
import org.http4k.core.Body
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.format.KotlinxSerialization.auto

val errorRspLens = Body.auto<GenericErrorRsp>().toLens()
fun createErrorRsp(vararg errors: String, status: Status) = errorRspLens(ErrorRsp(*errors), Response(status))


val newUserReqLens = Body.auto<NewUserReq>().toLens()
val userRspLens = Body.auto<UserRsp>().toLens()
