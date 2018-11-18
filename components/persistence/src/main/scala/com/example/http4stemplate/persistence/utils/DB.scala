package com.example.http4stemplate.persistence.utils

import com.typesafe.config.Config
import slick.jdbc.JdbcProfile

class DB(dbName: String, val driver: JdbcProfile, config: => Config) {

  lazy val db: driver.api.Database = driver.api.Database.forConfig(dbName, config)

}
