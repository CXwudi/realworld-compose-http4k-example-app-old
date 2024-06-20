package mikufan.cx.conduit.backend.controller

import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.then
import org.http4k.filter.MicrometerMetrics
import org.http4k.filter.ServerFilters
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes

fun filters(conduitRoute: RoutingHttpHandler): RoutingHttpHandler {
  return ServerFilters.MicrometerMetrics.RequestTimer(SimpleMeterRegistry())
    .then(conduitRoute)
}

fun conduitRoute(apiRoute: RoutingHttpHandler): RoutingHttpHandler = routes(
  "/healthcheck" bind GET to { Response(Status.OK) },
  "/api" bind apiRoute
)

fun apiRoute(
  registerUser: RegisterUserHandler,
) = routes(
  "/users" bind routes(
    "/" bind routes(
      POST to registerUser
    )
  )
)

