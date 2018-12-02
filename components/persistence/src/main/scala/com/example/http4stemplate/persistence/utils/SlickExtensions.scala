package com.example.http4stemplate.persistence.utils

import cats.effect.Effect
import slick.dbio.{DBIOAction, NoStream}
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext

trait SlickExtensions {

  protected val driver: JdbcProfile

  implicit class DBIOExtension[R](db: driver.api.Database) {

    def delay[F[_]: Effect](a: DBIOAction[R, NoStream, Nothing])(implicit ec: ExecutionContext): F[R] =
      new Converters[F].fromFuture(db.run(a))
  }

}
