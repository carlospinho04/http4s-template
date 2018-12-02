package com.example.http4stemplaterules

import cats.effect.{Effect, IO}
import com.example.http4stemplate.di.RulesComponents
import com.example.http4stemplate.domain.Item
import com.example.http4stemplate.persistence.di.EffectComponents
import org.specs2.matcher.FutureMatchers
import org.specs2.specification.core.{Env, OwnExecutionEnv}

class ItemRulesSpec(val env: Env)
    extends org.specs2.mutable.Specification
    with FutureMatchers
    with OwnExecutionEnv
    with RulesComponents[IO]
    with EffectComponents[IO] {
  override val eff: Effect[IO] = Effect[IO]

  private val name = "Potato"
  private val name1 = "Milk"
  private val price = 20
  private val price1 = 10

  "ItemRules" should {
    "create an entry for an item" >> {
      val items = for {
        _ <- itemRules.create(Item(name = name, price = price))
        _ <- itemRules.create(Item(name = name1, price = price1))
        items <- itemRules.listItems()
      } yield {
        items
      }
      items.unsafeRunSync().map(_.name) must containTheSameElementsAs(Seq(name, name1))
    }
  }

}
