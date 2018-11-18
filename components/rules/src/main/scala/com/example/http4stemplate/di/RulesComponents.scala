package com.example.http4stemplate.di

import com.example.http4stemplate.ItemRules
import com.example.http4stemplate.persistence.di.{DatabaseComponents, EffectComponents}

trait RulesComponents[F[_]] extends DatabaseComponents[F] {
  self: EffectComponents[F] =>

  protected lazy val itemRules = new ItemRules[F](itemQueries)

}
