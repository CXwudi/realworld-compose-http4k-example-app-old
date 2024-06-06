package mikufan.cx.conduit.backend

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mikufan.cx.conduit.common.TestPojo

fun main(args: Array<String>) {
  println("Hello, Conduit Backend!")
  println(Json.encodeToString(TestPojo(1, "MikuFan")))
}