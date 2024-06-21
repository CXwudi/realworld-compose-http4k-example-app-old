package mikufan.cx.conduit.backend

import mikufan.cx.conduit.backend.controller.ConduitServer
import org.flywaydb.core.Flyway

class Bootstrap(
  private val flyway: Flyway,
  private val server: ConduitServer
) : Runnable {
  override fun run() {
    dbMigration()
    server.start()
  }

  private fun dbMigration() {
    flyway.migrate()
  }
}