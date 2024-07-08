package mikufan.cx.conduit.frontend.logic.repo.db

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

fun provideDbDriver(
  schema: SqlSchema<QueryResult.AsyncValue<Unit>>,
  context: Context
) : SqlDriver = AndroidSqliteDriver(schema.synchronous(), context, "db.db")