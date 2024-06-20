package mikufan.cx.conduit.backend.db

import org.jetbrains.exposed.dao.id.IntIdTable

object Users : IntIdTable() {
  val email = varchar("email", 255).uniqueIndex()
  val username = varchar("username", 255).uniqueIndex()
  val password = varchar("password", 255)
  val bio = varchar("bio", 255).nullable()
  val image = varchar("image", 255).nullable()
}
