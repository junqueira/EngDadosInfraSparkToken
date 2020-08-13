package br.com.anonymize.pii

import org.apache.log4j.Logger
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.sql.functions.{col, lit, udf}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.types._


object AnonymizeFunc {
    
    val log: Logger = Logger.getLogger(AnonymizeFunc.getClass)

    def withCrypto(bc_lookupMap: Broadcast[Map[(String,String), String]], field: String)(fileDF: DataFrame): DataFrame = {
        def getLookUp(column: String, field: String): String = {
            try {
                bc_lookupMap.value((column.toLowerCase(), field))
            }
            catch {
                case _: Throwable => "null"
            }
        }
        val lookUpUdf = udf[String, String, String](getLookUp)
        fileDF.withColumn(field, lookUpUdf(lit(field), col(field)))
    }

    def withHashCopy(field: String)(df: DataFrame): DataFrame = {
        var colHashCopy = field.concat("_hash")
        if (field.toUpperCase() == field) {
            colHashCopy = colHashCopy.toUpperCase()
        } else {
            colHashCopy = colHashCopy.toLowerCase()
        }
        df.withColumn(colHashCopy, col(field))
    }

}