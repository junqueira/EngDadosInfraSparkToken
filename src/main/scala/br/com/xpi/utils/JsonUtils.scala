package br.com.anonymize.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import scala.reflect.ClassTag
import scala.reflect._
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.sql.{DataFrame, SaveMode}
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.sql.SparkSession


object JsonUtil {

    val jacksonMapper = new ObjectMapper()
    jacksonMapper.registerModule(DefaultScalaModule)
    //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    def toJson(value: Map[Symbol, Any]): String = {
        toJson(value map { case (k,v) => k.name -> v})
    }

    def toJson(value: Any): String = {
        jacksonMapper.writeValueAsString(value)
    }

    // sample => val _json = JsonUtil.fromJson[Map[String, List[Map[String, String]]]](json)
    // _json.get("idade")
    def fromJson[T: ClassTag](json: String): T = {
        jacksonMapper.readValue[T](json, classTag[T].runtimeClass.asInstanceOf[Class[T]])
    }

    def dfToJson(spark: SparkSession, pathConfig: String, base: String = ""): DataFrame = {
        val fileContext = spark.sparkContext.wholeTextFiles(pathConfig.toString).values
        val configDF = spark.read.json(fileContext)
        if (base != "") {
            val configFilterDF = configDF.filter("name='" + base.toString + "'")
            configFilterDF
        } else {
            configDF
        }
    }

}


