//package br.xpi
//
//import br.xpi.utils.{DataUtils}
////import br.xpi.pii.{Decrypto, Encrypto}
//import br.xpi.pii.{Encryption}
////import com.typesafe.config.ConfigFactory
//import org.apache.log4j.{Level, Logger}
//import org.apache.spark.sql.SparkSession
//
//
//object Execute {
//
//    val log: Logger = Logger.getLogger(Execute.getClass)
//
//    def main(args: Array[String]): Unit = {
////        val configManager = ConfigFactory.load()
////        val loglevel = configManager.getString(s"log.level")
//        Logger.getLogger("org").setLevel(Level.OFF)
//        Logger.getLogger("akka").setLevel(Level.OFF)
//        Logger.getLogger("hadoop").setLevel(Level.OFF)
//        Logger.getLogger("hadoop.ParquetFileReader").setLevel(Level.OFF)
//        Logger.getLogger("hadoop.InternalParquetRecordReader").setLevel(Level.OFF)
//
////        loglevel match {
////            case "INFO" => log.setLevel(Level.INFO)
////            case "DEBUG" => log.setLevel(Level.DEBUG)
////            case "WARN" => log.setLevel(Level.WARN)
////            case "ERROR" => log.setLevel(Level.ERROR)
////            case _ => log.setLevel(Level.INFO)
////        }
//        log.info(s"#######  Initializing process")
////        log.info(s"[*] Log Level: $loglevel")
//        val fonte = args(0)
//        val fila = "develop" //params.getOrElse(args(1), "develop")
//        if(fonte.isEmpty || args.contains("-h"))
//            showHelp()
////        val spark = SparkUtils.getSparkSession(fonte, fila)
//        val spark = SparkSession
//        .builder()
//        .appName(fonte + "-test" )
//        .getOrCreate()
//
//        log.info(s"**********************************************************************************")
//        log.info(s"*** Fonte: $fonte")
//        log.info(s"*** Application ID: " + spark.sparkContext.applicationId)
//        log.info(s"**********************************************************************************")
//
//        try {
//            val paramData = DataUtils.getDtMovto(args)
//            log.info("[*] Process Type " + fonte)
//            log.info("[*] datamovto: "   + paramData)
//
//            fonte match {
//                case "encrypto" =>
//                    log.info(s"Encrypto data generation")
////                    Encrypto.execute(spark)
//                    Encryption.execute(spark)
////                case "decrypto" =>
////                    log.info(s"Decryption data generation ")
////                    Decrypto.execute(spark)
//                case _ =>
//                    log.error(s"Fonte não encontrada!")
//                    sys.exit(134)
//            }
//
//        } catch {
//            case e : Exception =>
//                log.error(s"Erro no processo: "+e.getMessage)
//                e.printStackTrace()
//                sys.exit(134)
//        }
//        log.info(s"Final proccess")
//    }
//
//    def showHelp(): Unit = {
//        log.info(s" ==> HELP")
//        log.info(s"======================================================================================== ")
//        log.info("usage    : spark-submit decrypto.jar --fonte $fonte")
//        log.info("$fonte   : font name, like (decrypto, etc...)")
//        log.info("generics : usar decrypto-fonte para executar somente uma das fontes")
//        log.info(s"========================================================================================")
//        sys.exit(134)
//    }
//}
