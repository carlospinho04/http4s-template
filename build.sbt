import Dependencies._
import sbt.{Def, _}

val scalaBinaryVersionNumber = "2.12"
val scalaVersionNumber = s"$scalaBinaryVersionNumber.7"

// HACK: H2 in memory seems to have problems with concurrent tests
val testSeqOptions: Seq[Def.Setting[_]] =
  Seq(parallelExecution in Test := false, testOptions in Test += Tests.Argument("sequential"))

lazy val root = (project in file("."))
  .settings(organization := "com.example",
            name := "http4s-template",
            version := "0.0.1-SNAPSHOT",
            scalaVersion := scalaVersionNumber,
            // App Dependencies
            libraryDependencies ++= http4s ++ database ++ circe ++ jodaTime ++ Seq(logbackClassic, typesafeConfig),
            // Test Dependencies
            libraryDependencies ++= Seq(specs2))
  .settings(testSeqOptions)
  .dependsOn(rules)
  .dependsOn(domain)

lazy val rules = project
  .in(file("components/rules"))
  .settings(organization := "com.example",
            name := "http4s-template-rules",
            // App Dependencies
            libraryDependencies ++= http4s ++ Seq(logbackClassic, typesafeConfig),
            // Test Dependencies
            libraryDependencies ++= Seq(specs2))
  .settings(testSeqOptions: _*)
  .dependsOn(persistence)
  .dependsOn(domain)

lazy val persistence = project
  .in(file("components/persistence"))
  .settings(organization := "com.example",
            name := "http4s-template-persistence",
            libraryDependencies ++= http4s ++ database ++ jodaTime ++ Seq(logbackClassic))
  .settings(testSeqOptions: _*)

lazy val domain = project
  .in(file("components/domain"))
  .settings(organization := "com.example", name := "http4s-template-domain")

//Compiler Plugins
addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.6")
addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.2.4")

// Scapegoat
scalaVersion in ThisBuild := scalaVersionNumber
scalaBinaryVersion in ThisBuild := scalaBinaryVersionNumber
