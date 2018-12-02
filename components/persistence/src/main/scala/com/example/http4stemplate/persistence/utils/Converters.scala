package com.example.http4stemplate.persistence.utils

import cats.effect.{Effect, IO}

import scala.concurrent.Future

class Converters[F[_]: Effect] {

  def fromFuture[A](f: => Future[A]): F[A] =  {
    Effect[F].liftIO(IO.fromFuture(IO(f)))
  }

}
