package mikufan.cx.conduit.backend.config

data class Config(
  val port: Int,
  val db: DbConfig
)

data class DbConfig(
  val url: String,
  val user: String? = null,
  val password: String? = null,
  val driver: String,
)

/**
 * So far trade this as the only true source of config,
 * we will be able to replace it with dedicated config library
 */
val LOCAL_CONFIG = Config(
  port = 8080,
  db = DbConfig(url = "jdbc:h2:mem:test", driver = "org.h2.Driver")
)