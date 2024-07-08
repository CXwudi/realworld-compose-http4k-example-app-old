package mikufan.cx.conduit.frontend.logic.repo.db

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Worker


fun provideDbDriver(
  schema: SqlSchema<QueryResult.AsyncValue<Unit>>,
  callback: (SqlDriver) -> Unit
) {
  val driver = WebWorkerDriver(
    Worker(
      js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)""") as String
    )
  )
  CoroutineScope(Dispatchers.Default).launch {
    schema.create(driver).await()
    callback(driver)
  }
}