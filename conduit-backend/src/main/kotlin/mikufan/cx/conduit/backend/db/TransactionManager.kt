package mikufan.cx.conduit.backend.db

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.transaction

interface TransactionManager {
  val db: Database

  fun <T> tx(block: Transaction.() -> T): T {
    return transaction(db) {
      block()
    }
  }
}

class TransactionManagerImpl(
  override val db: Database
) : TransactionManager