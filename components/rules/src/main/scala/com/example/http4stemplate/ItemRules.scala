package com.example.http4stemplate

import cats.effect.Effect
import com.example.http4stemplate.persistence.{ItemIdentifier, ItemQueries}
import cats.implicits._

class ItemRules[F[_]: Effect](itemQueries: => ItemQueries[F]) {

  def create(item: domain.Item): F[ItemIdentifier] = {
    itemQueries.create(persistence.Item(name = item.name, price = item.price))
  }

  def listItems(): F[Seq[domain.Item]] = {
    itemQueries.list.map(_.map(item => domain.Item(item.name, item.price)))
  }
}
