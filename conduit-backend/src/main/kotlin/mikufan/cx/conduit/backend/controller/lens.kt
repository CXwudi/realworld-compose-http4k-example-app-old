package mikufan.cx.conduit.backend.controller

import mikufan.cx.conduit.common.NewUserReq
import mikufan.cx.conduit.common.UserRsp
import org.http4k.core.Body
import org.http4k.format.KotlinxSerialization.auto


val newUserReqLens = Body.auto<NewUserReq>().toLens()
val userRspLens = Body.auto<UserRsp>().toLens()
