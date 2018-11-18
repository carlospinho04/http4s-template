package com.example.http4stemplate.di

import cats.effect.Effect
import com.example.http4stemplate.persistence.di.EffectComponents
import com.example.http4stemplate.services.ItemService
import io.circe.generic.AutoDerivation

class ServiceComponents[F[_]: Effect] extends AutoDerivation with RulesComponents[F] with EffectComponents[F] {
  override val eff: Effect[F] = Effect[F]

  lazy val itemService: ItemService[F] = new ItemService[F](itemRules)

}
