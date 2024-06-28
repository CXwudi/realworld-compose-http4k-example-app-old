package mikufan.cx.conduit.backend

import mikufan.cx.conduit.backend.controller.ConduitServer
import mikufan.cx.inlinelogging.KInlineLogging
import org.flywaydb.core.Flyway

class Bootstrap(
  private val flyway: Flyway,
  private val server: ConduitServer
) : Runnable {
  override fun run() {
    dbMigration()
    startServer()
  }

  private fun dbMigration() {
    log.info { "Performing DB migrations if any" }
    flyway.migrate()
  }

  private fun startServer() {
    log.info { "Starting server" }
    server.start()
  }
}

private val log = KInlineLogging.logger()