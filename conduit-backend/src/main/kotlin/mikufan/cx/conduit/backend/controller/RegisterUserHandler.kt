package mikufan.cx.conduit.backend.controller

import mikufan.cx.conduit.backend.service.UserService
import mikufan.cx.conduit.common.NewUserReq
import mikufan.cx.conduit.common.UserRsp
import org.http4k.core.*
import org.http4k.format.KotlinxSerialization.auto

class RegisterUserHandler(
  private val userService: UserService,
) : HttpHandler {

  val registrationLens = Body.auto<NewUserReq>().toLens()
  val newUserCreatedLens = Body.auto<UserRsp>().toLens()

  override fun invoke(request: Request): Response {
    val userDto = registrationLens(request).user
    val newUserDto = userService.registerUser(userDto)
    return newUserCreatedLens(UserRsp(newUserDto), Response(Status.CREATED))
  }
}