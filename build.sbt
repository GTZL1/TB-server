import play.core.PlayVersion.{akkaHttpVersion, akkaVersion}

name := """tb-server"""
organization := "heig.david.tb"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.4"

libraryDependencies += guice
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-http-jackson" % akkaHttpVersion
libraryDependencies += "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
libraryDependencies ++= Seq(javaJdbc, javaWs, javaJpa, evolutions, javaForms, guice,
  "org.hibernate" % "hibernate-core" % "5.4.27.Final", "at.favre.lib" % "bcrypt" % "0.9.0",
  "org.mockito" % "mockito-core" % "3.11.2")
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.19"

PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml"