package com.example.http4stemplate

import cats.effect.Effect
import com.example.http4stemplate.persistence.ItemQueries

class ItemRules[F[_]: Effect](itemQueries: => ItemQueries[F]) {
  //TODO: Add methods
}
