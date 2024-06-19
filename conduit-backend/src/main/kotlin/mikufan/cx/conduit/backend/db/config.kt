package mikufan.cx.conduit.backend.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import mikufan.cx.conduit.backend.config.DbConfig
import org.flywaydb.core.Flyway
import javax.sql.DataSource

fun creatDataSource(dbConfig: DbConfig): DataSource {
  val config = HikariConfig().apply {
    jdbcUrl = dbConfig.url
    username = dbConfig.user
    password = dbConfig.password
    driverClassName = dbConfig.driver
  }
  return HikariDataSource(config)
}

fun createFlyway(dataSource: DataSource) = Flyway
  .configure()
  .dataSource(dataSource)
  .load()