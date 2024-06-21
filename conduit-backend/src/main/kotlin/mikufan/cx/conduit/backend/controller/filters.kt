package mikufan.cx.conduit.backend.controller

import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import mikufan.cx.conduit.backend.config.CorsConfig
import org.http4k.core.Filter
import org.http4k.core.Method
import org.http4k.filter.*


fun metricsFilter(): Filter {
  return ServerFilters.MicrometerMetrics.RequestTimer(SimpleMeterRegistry())
}

fun corsFilter(config: CorsConfig): Filter {
  return ServerFilters.Cors(
    CorsPolicy(
    originPolicy = OriginPolicy.AnyOf(config.allowedOrigins),
    headers = config.allowedHeaders,
    methods = Method.entries,
    credentials = config.allowCredentials,
  )
  )
}

fun errorResponseFilter(): Filter {
  return ServerFilters.CatchLensFailure
}