//package br.xpi.hubs
//
//import br.xpi.pii.AnonymizeFunc
//import org.apache.log4j.Logger
//import org.apache.spark.broadcast.Broadcast
//import org.apache.spark.sql.functions.{col, lit, udf}
//import org.apache.spark.sql.{DataFrame, SparkSession}
//import org.apache.spark.sql.types._
//
//
//object sparkProducer {
//    val log: Logger = Logger.getLogger(AnonymizeFunc.getClass)
//
//    def execute(spark: SparkSession): Unit = {
//        val TOPIC = "anonymize"
//        val BOOTSTRAP_SERVERS = "broker-test.servicebus.windows.net:9093"
//
//        val EH_SASL = "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"$ConnectionString\" password=\"Endpoint=sb://broker-test.servicebus.windows.net/;SharedAccessKeyName=saved;SharedAccessKey=h9enxIJp1ABiDk/qz5AALAc2F2AaI1PM7hSbK0Y0b28=;EntityPath=anonymize\";"
//        val CHECKPOINT_PATH = "./checkpoint"
//
//        val artistschema = StructType(List(
//            StructField("artist", StringType, true),
//            StructField("firstName", StringType, true),
//            StructField("lastName", StringType, true),
//            StructField("location", StringType, true),
//            StructField("song", StringType, true),
//            StructField("userId", StringType, true)))
//
//        val rdd = spark.read.schema(artistschema).json("/tmp/broker-test.json")
//        val rddWrite = rdd.selectExpr("CAST(userId as STRING) as key", "to_json(struct(*)) AS value")
//        rddWrite.show()
//
//        rddWrite.write.format("kafka").option("topic", TOPIC).
//            option("kafka.bootstrap.servers", BOOTSTRAP_SERVERS).
//            option("kafka.sasl.mechanism", "PLAIN").                 //
//            option("kafka.security.protocol", "SASL_PLAINTEXT").           //("SASL_PLAINTEXT", "SASL_SSL")
//            option("kafka.sasl.jaas.config", EH_SASL).
//            option("checkpointLocation", CHECKPOINT_PATH).
//            save()
//    }
//}