
/** We use scala test as our testing framework */
val scalaTest= "org.scalatest" %% "scalatest" % "3.0.1" % "test"

/** We use scala logging as our high-level logging adapter */
val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"

/** We use logback as our concrete logging plugin (adapted by slf4j and scala logging on top) */
val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"

/** We use rxScala to receive and process events on domain objects */
val rxScala = "io.reactivex" %% "rxscala" % "0.26.5"

/**
  * Common settings used by all sbt modules in this project.
  */
lazy val commonSettings = Seq(
  organization := "io.themepark",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.11.11",
  libraryDependencies ++= Seq(
    scalaTest,
    logback,
    scalaLogging
  )
)

/**
  * Queue Time Predictor Module: Responsible for predicting queue times on attractions.
  */
lazy val queueTimePredictor = Project(id = "queue-time-predictor", base = file("queue-time-predictor")).
  settings(commonSettings: _*).
  settings(
    name := "themepark-queue-time-predictor",
    libraryDependencies ++= Seq(
      "io.reactivex" %% "rxscala" % "0.26.5"
    )
  )

/**
  * Queue Time Predictor App bootstrapping the Queue Time Predictor Module, injecting all of its dependencies.
  */
lazy val queueTimePredictorApp = Project(id = "queue-time-predictor-app", base = file("queue-time-predictor-app")).
  settings(commonSettings: _*).
  settings(
    name := "themepark-queue-time-predictor-app",
    libraryDependencies ++= Seq(
      rxScala
    )
  ).dependsOn(queueTimePredictor)


/**
  * Root module, aggregates all other modules.
  * This is used so that all modules will run the respective sbt commands when issued at top level (compile/test etc.).
  */
lazy val root = project.in(file(".")).aggregate(queueTimePredictor, queueTimePredictorApp)

