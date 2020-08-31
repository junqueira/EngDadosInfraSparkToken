//package br.xpi.utils
//
//import org.apache.hadoop.fs.{FileSystem, Path}
//import org.apache.spark.sql.{DataFrame, SaveMode}
//import org.apache.spark.broadcast.Broadcast
//import org.apache.spark.sql.SparkSession
//import java.io.File
//
//
//object FileUtils {
//
//    def readFile(spark: SparkSession, path_file: String, separator: String = "|"): DataFrame = {
//        val withOptions: Map[String, String] = Map(
//            ("delimiter", separator.toString),
//            ("header", "true"),
//            ("compression", "None"))
//        spark.read.options(withOptions).csv(path_file)
//    }
//
//}