package com.example.http4stemplate.persistence.utils

import java.sql.Timestamp

import org.joda.time.DateTime
import slick.ast.BaseTypedType
import slick.jdbc.{JdbcProfile, JdbcType}

trait DBMappers {

  protected val driver: JdbcProfile

  import driver.api._

  implicit def timestampToDateTime: JdbcType[DateTime] with BaseTypedType[DateTime] =
    MappedColumnType
      .base[DateTime, Timestamp](dateTime => new Timestamp(dateTime.getMillis), date => new DateTime(date))

}
