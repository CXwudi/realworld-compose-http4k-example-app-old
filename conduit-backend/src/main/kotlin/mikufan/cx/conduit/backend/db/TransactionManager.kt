package mikufan.cx.conduit.backend.db

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction

interface TransactionManager {
  val db: Database
}

inline fun <reified T> TransactionManager.transaction(crossinline block: Transaction.() -> T): T {
  return org.jetbrains.exposed.sql.transactions.transaction(db) {
    block()
  }
}

class TransactionManagerImpl(
  override val db: Database
) : TransactionManager