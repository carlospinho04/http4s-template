package com.example.http4stemplate

import cats.effect.{Effect, IO}
import com.example.http4stemplate.di.ServiceComponents
import fs2.StreamApp
import org.http4s.server.blaze.BlazeBuilder

import scala.concurrent.ExecutionContext

object HelloWorldServer extends StreamApp[IO] {
  import scala.concurrent.ExecutionContext.Implicits.global

  def stream(args: List[String], requestShutdown: IO[Unit]): fs2.Stream[IO, StreamApp.ExitCode] =
    ServerStream.stream[IO]
}

object ServerStream {

  def stream[F[_]: Effect](implicit ec: ExecutionContext): fs2.Stream[F, StreamApp.ExitCode] = {
    val sc = new ServiceComponents[F]

    BlazeBuilder[F]
      .bindHttp(8080, "0.0.0.0")
      .mountService(sc.itemService.service, "/")
      .serve
  }
}
