package com.example.http4stemplate

import org.slf4j

trait Logger {
  lazy val logger: slf4j.Logger = slf4j.LoggerFactory.getLogger(getClass.getName)
}
