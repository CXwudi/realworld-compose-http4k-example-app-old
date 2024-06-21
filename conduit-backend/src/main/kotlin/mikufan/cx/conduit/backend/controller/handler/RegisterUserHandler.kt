package mikufan.cx.conduit.backend.controller.handler

import mikufan.cx.conduit.backend.controller.newUserReqLens
import mikufan.cx.conduit.backend.controller.userRspLens
import mikufan.cx.conduit.backend.service.UserService
import mikufan.cx.conduit.common.UserRsp
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status

class RegisterUserHandler(
  private val userService: UserService,
) : HttpHandler {

  override fun invoke(request: Request): Response {
    val userDto = newUserReqLens(request).user
    val newUserDto = userService.registerUser(userDto)
    return userRspLens(UserRsp(newUserDto), Response(Status.CREATED))
  }
}