package com.example.http4stemplate.persistence.di

import cats.effect.Effect
import com.example.http4stemplate.persistence.utils.{DB, Reflection}
import com.typesafe.config.ConfigFactory
import slick.jdbc.JdbcProfile

trait EffectComponents[F[_]] {
  implicit def eff: Effect[F]
}

trait DatabaseComponents[F[_]] {
  self: EffectComponents[F] =>

  val dbName = "database"
  private lazy val config = ConfigFactory.load(s"databases/$dbName.conf")

  val profile = Reflection.Object.forString[JdbcProfile](config.getString("database.profile"))

  private lazy val dbComponent: DB = new DB("database", profile, config)

}
