import sbt._

object Dependencies {
  lazy val specs2: ModuleID = "org.specs2" %% "specs2-core" % "4.2.0" % "test"

  lazy val logbackClassic: ModuleID = "ch.qos.logback" % "logback-classic" % "1.2.3"

  lazy val typesafeConfig: ModuleID = "com.typesafe" % "config" % "1.3.2"

  private val Http4sVersion = "0.18.19"
  lazy val http4s: List[ModuleID] =
    List("http4s-blaze-server", "http4s-circe", "http4s-dsl")
      .map("org.http4s" %% _ % Http4sVersion)

  private val slickVersion = "3.2.3"
  lazy val database: List[ModuleID] = List("com.typesafe.slick" %% "slick" % slickVersion,
                                           "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
                                           "com.h2database" % "h2" % "1.4.197")

  private val circeVersion = "0.10.0"
  lazy val circe: List[ModuleID] =
    List("circe-core", "circe-generic", "circe-parser")
      .map("io.circe" %% _ % circeVersion)

  lazy val jodaTime: List[ModuleID] = List("joda-time" % "joda-time" % "2.9.9", "org.joda" % "joda-convert" % "1.8.3")

}
