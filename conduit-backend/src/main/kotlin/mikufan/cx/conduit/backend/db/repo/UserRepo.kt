package mikufan.cx.conduit.backend.db.repo

import mikufan.cx.conduit.backend.db.Users
import mikufan.cx.conduit.common.NewUserDto
import mikufan.cx.conduit.common.UserDto
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insertReturning
import org.jetbrains.exposed.sql.selectAll

class UserRepo {

  fun getById(id: Int): UserDto? = Users
    .selectAll().where { Users.id eq id }
    .firstOrNull()
    ?.toUserDto()

  fun getByEmail(email: String): UserDto? = Users
    .selectAll().where { Users.email eq email }
    .firstOrNull()
    ?.toUserDto()

  fun getByUsername(username: String): UserDto? = Users
    .selectAll().where { Users.username eq username }
    .firstOrNull()
    ?.toUserDto()

  fun insert(newUserDto: NewUserDto): UserDto? = Users.insertReturning {
    it[email] = newUserDto.email
    it[username] = newUserDto.username
    it[password] = newUserDto.password
    it[bio] = ""
    it[image] = null
  }
    .firstOrNull()
    ?.toUserDto()

  private fun ResultRow.toUserDto() = UserDto(
    id = this[Users.id].value,
    email = this[Users.email],
    username = this[Users.username],
    bio = this[Users.bio],
    image = this[Users.image],
    token = null
  )
}