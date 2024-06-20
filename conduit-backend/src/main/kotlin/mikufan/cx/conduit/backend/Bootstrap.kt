package mikufan.cx.conduit.backend

import org.flywaydb.core.Flyway

class Bootstrap(
  private val flyway: Flyway,
) : Runnable {
  override fun run() {
    dbMigration()

  }

  private fun dbMigration() {
    flyway.migrate()
  }
}