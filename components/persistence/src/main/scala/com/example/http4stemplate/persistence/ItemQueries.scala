package com.example.http4stemplate.persistence

import com.example.http4stemplate.persistence.utils.{DB, DBMappers, SlickExtensions}
import cats.effect.Effect
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global

final case class ItemIdentifier(id: Long)

final case class Item(id: ItemIdentifier = ItemIdentifier(-1), name: String, price: Long)

class ItemQueries[F[_]: Effect](dbComponent: DB) extends ItemTable(dbComponent.driver) with SlickExtensions {
  import dbComponent._
  import driver.api._

  def create(item: Item): F[ItemIdentifier] =
    db.delay {
      (tableAutoInc += item).transactionally
    }

  def list: F[Seq[Item]] = db.delay {
    tableQuery.result
  }
}

class ItemTable(protected val driver: JdbcProfile) extends DBMappers {

  import driver.api._

  class ItemTableDef(tag: Tag) extends Table[Item](tag, "item") {
    val id = column[ItemIdentifier]("id", O.PrimaryKey, O.AutoInc)
    val name = column[String]("name")
    val value = column[Long]("price")

    def * =
      (id, name, value) <> (Item.tupled, Item.unapply)

  }

  protected val tableQuery = TableQuery[ItemTableDef]

  private[persistence] def tableAutoInc = tableQuery.returning(tableQuery.map(_.id))

}
