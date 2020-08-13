name := "anonymize"
version := "1.0"

scalaVersion := "2.11.8"
// scalaVersion := "2.12.0"

// === Spark === //
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.1" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql"  % "2.1.1" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-hive" % "2.1.1" % "provided"

// === Library  === //
libraryDependencies += "org.apache.kafka" %% "kafka-streams-scala" % "2.3.0"

// === Config  === //
libraryDependencies += "com.typesafe" % "config" % "1.3.3"
libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.22.0"
libraryDependencies += "com.holdenkarau" %% "spark-testing-base" % "1.6.0_0.3.1" % "test"
libraryDependencies += "org.json4s" %% "json4s-native" % "3.4.0" % "provided"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.0"