package mikufan.cx.conduit.backend.controller

import mikufan.cx.conduit.backend.config.Config
import org.http4k.core.*
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Http4kServer
import org.http4k.server.JettyLoom
import org.http4k.server.asServer


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

//// server setup ////

fun conduitHttp4kHandler(
  metricFilter: Filter,
  corsFilter: Filter,
  errorRspFilter: Filter,
  route: RoutingHttpHandler
): HttpHandler {
  return metricFilter
    .then(corsFilter)
    .then(errorRspFilter)
    .then(route)
}


fun conduitServer(
  config: Config,
  handler: HttpHandler,
): Http4kServer = handler.asServer(JettyLoom(config.port))



