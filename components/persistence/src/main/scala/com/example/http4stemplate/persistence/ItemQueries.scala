package com.example.http4stemplate.persistence

import com.example.http4stemplate.persistence.utils.{DB, DBMappers}
import cats.effect.Effect
import slick.jdbc.JdbcProfile

case class ItemIdentifier(id: Long)

case class Item(id: ItemIdentifier, name: String, value: Double)

class ItemQueries[F[_]: Effect](dbComponent: DB) extends ItemTable(dbComponent.driver) {
  //TODO: Add item queries
}

class ItemTable(protected val driver: JdbcProfile) extends DBMappers {

  import driver.api._

  class ItemTableDef(tag: Tag) extends Table[Item](tag, "item") {
    val itemId = column[ItemIdentifier]("problem_id")
    val name = column[String]("name")
    val value = column[Double]("value")

    def * =
      (itemId, name, value) <> (Item.tupled, Item.unapply)

  }

  protected val tableQuery = TableQuery[ItemTableDef]

}
