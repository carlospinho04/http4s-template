package com.example.http4stemplate.services

import cats.effect.Effect
import com.example.http4stemplate.ItemRules
import io.circe.Json
import org.http4s.HttpService
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

class ItemService[F[_]: Effect](itemRules: => ItemRules[F]) extends Http4sDsl[F] {

  val service: HttpService[F] = {
    HttpService[F] {
      case GET -> Root / "hello" / name =>
        Ok(Json.obj("message" -> Json.fromString(s"Hello, ${name}")))
    }
  }
}
