package mikufan.cx.conduit.backend.service

import mikufan.cx.conduit.backend.db.TransactionManager
import mikufan.cx.conduit.backend.db.repo.UserRepo
import mikufan.cx.conduit.backend.db.tx
import mikufan.cx.conduit.backend.util.ConduitException
import mikufan.cx.conduit.common.NewUserDto
import mikufan.cx.conduit.common.UserDto

class UserService(
  private val txManager: TransactionManager,
  private val userRepo: UserRepo,
) {

  fun registerUser(user: NewUserDto): UserDto =
    txManager.tx {
      val userDto = userRepo.getByUsername(user.username) ?: userRepo.getByEmail(user.email)
      if (userDto != null) {
        throw ConduitException("User already exists, username or email must be unique")
      } else {
        userRepo.insert(user) ?: throw ConduitException("Failed to create new user")
      }
    }
}