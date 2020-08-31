//package br.xpi.pii
//
//import org.apache.log4j.Logger
//import org.apache.spark.sql.{SparkSession, Row, DataFrame}
////import br.com.anonymize.utils.Helpers
//import br.xpi.utils.FileUtils
//import com.typesafe.config.ConfigFactory
//import java.security.MessageDigest
//import java.util
//import javax.crypto.Cipher
//import javax.crypto.spec.SecretKeySpec
//import org.apache.commons.codec.binary.Base64
//
//
//object Encryption {
//
//    val log: Logger = Logger.getLogger(Encryption.getClass)
//    val configManager = ConfigFactory.load()
//
//    def execute(spark: SparkSession): Unit = {
//        try {
//            val builk_hash = getBulkPrivacy(spark)
//            builk_hash.show(15, false)
//
//        } catch {
//            case e : Exception =>
//                log.error(s"Erro no processo: "+e.getMessage)
//                e.printStackTrace()
//                sys.exit(134)
//        }
//        log.info(s"Decrypto finaly proccess")
//    }
//
//    def getBulkPrivacy(spark: SparkSession): DataFrame = {
//        log.info(s"Decrypto get _bulk PRIVATY...!")
////        val data_privacy = configManager.getString(s"file.data_privacy")
////        val sep_priv = configManager.getString(s"file.sep_priv")
//        val data_privacy = "file_source/jars/data_privacy.txt"
//        val sep_priv = "/\\073"
//        val builk_privacy = FileUtils.readFile(spark, data_privacy, sep_priv)
//        builk_privacy
//    }
//
//    def encrypt(key: String, value: String): String = {
//        val cipher: Cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
//        cipher.init(Cipher.ENCRYPT_MODE, keyToSpec(key))
//        Base64.encodeBase64String(cipher.doFinal(value.getBytes("UTF-8")))
//    }
//
//    def decrypt(key: String, encryptedValue: String): String = {
//        val cipher: Cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
//        cipher.init(Cipher.DECRYPT_MODE, keyToSpec(key))
//        new String(cipher.doFinal(Base64.decodeBase64(encryptedValue)))
//    }
//
//    def keyToSpec(key: String): SecretKeySpec = {
//        var keyBytes: Array[Byte] = (key).getBytes("UTF-8")
//        val sha: MessageDigest = MessageDigest.getInstance("SHA-1")
//        keyBytes = sha.digest(keyBytes)
//        keyBytes = util.Arrays.copyOf(keyBytes, 16)
//        new SecretKeySpec(keyBytes, "AES")
//    }
//}