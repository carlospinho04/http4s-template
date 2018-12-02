package com.example.http4stemplate.services

import cats.effect.Effect
import com.example.http4stemplate.{ItemRules, Logger}
import org.http4s.HttpService
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import cats.implicits._
import com.example.http4stemplate.domain.Item
import io.circe.generic.AutoDerivation

class ItemService[F[_]: Effect](itemRules: => ItemRules[F])
    extends Http4sDsl[F]
    with AutoDerivation
    with CirceEntityDecoder
    with CirceEntityEncoder
    with Logger {

  val service: HttpService[F] = {
    HttpService[F] {
      case req @ POST -> Root / "item" / "create" =>
        req.as[Item].flatMap(itemRules.create).flatMap(Created(_))

      case GET -> Root / "items" =>
        logger.info("Getting all items")
        itemRules.listItems().flatMap(Ok(_))
    }
  }
}
