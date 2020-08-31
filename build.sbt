
import Dependencies._

name := "tokenization"
version := "1.0"

//scalaVersion := "2.11.8"
scalaVersion in ThisBuild := "2.12.4"

scalacOptions in ThisBuild := Seq("-Xexperimental", "-Xlint:_", "-unchecked", "-deprecation", "-feature", "-target:jvm-1.8")

lazy val protobufs = (project in file("./protobufs"))
    .settings(
    PB.targets in Compile := Seq(
          PB.gens.java -> (sourceManaged in Compile).value,
          scalapb.gen(javaConversions=true) -> (sourceManaged in Compile).value
        )
    )

lazy val client = (project in file("./client"))
      .settings(libraryDependencies ++=  Seq(Dependencies.kafka, Dependencies.curator))
      .dependsOn(protobufs, configuration)


//lazy val model = (project in file("./model"))
//  .settings(libraryDependencies ++= Dependencies.modelsDependencies)
//  .dependsOn(protobufs)

//lazy val kafkaStreamsModelServer = (project in file("./kafkaStreamsModelServer"))
//  .settings(dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.9.1",
//    dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.1"
//  )
//  .settings(libraryDependencies ++= Seq(Dependencies.kafkastreams, Dependencies.kafkastreamsScala) ++ Dependencies.webDependencies ++ Dependencies.akkHTTPPSupport)
//  .dependsOn(model, configuration)
//
//lazy val akkaStreamsModelServer = (project in file("./akkaStreamsModelServer"))
//  .settings(dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.9.1",
//    dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.1"
//  )
//  .settings(libraryDependencies ++= Dependencies.kafkabaseDependencies ++ Dependencies.akkaServerDependencies
//    ++ Dependencies.modelsDependencies)
//  .dependsOn(model, configuration)


lazy val configuration = (project in file("./configuration"))

lazy val akkaKafkaTutorial = (project in file(".")).
    aggregate(protobufs, client, configuration)
//  model,
//  kafkaStreamsModelServer,
//  akkaStreamsModelServer)

addCommandAlias("run_client", "client/runMain br.xpi.kafka.client.DataProvider")
addCommandAlias("run_reader", "client/runMain br.xpi.kafka.client.DataReader")
//addCommandAlias("run_scala_akka_server",  "akkaStreamsModelServer/runMain br.xpi.akkastream.modelserver.AkkaModelServer")
//addCommandAlias("run_scala_kafka_server", "kafkaStreamsModelServer/runMain br.xpi.kafkastreams.modelserver.KafkaModelServer")


// === Spark === //
//libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.1" % "provided"
//libraryDependencies += "org.apache.spark" %% "spark-sql"  % "2.1.1" % "provided"
//libraryDependencies += "org.apache.spark" %% "spark-hive" % "2.1.1" % "provided"
//
// libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.2"
// libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.2"
// libraryDependencies += "org.apache.spark" %% "spark-hive" % "2.4.2" % "provided"
//
//// === Library  === //
//libraryDependencies += "org.apache.kafka" %% "kafka-streams-scala" % "2.3.0"
//
//// === Config  === //
//libraryDependencies += "com.typesafe" % "config" % "1.3.3"
//libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.22.0"
////libraryDependencies += "com.holdenkarau" %% "spark-testing-base" % "1.6.0_0.3.1" % "test"
////libraryDependencies += "org.json4s" %% "json4s-native" % "3.4.0" % "provided"
//
//libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.0"