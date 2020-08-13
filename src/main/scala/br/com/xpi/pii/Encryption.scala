package br.com.anonymize.pii

import org.apache.log4j.Logger
import org.apache.spark.sql.{SparkSession, Row, DataFrame}
import br.com.anonymize.utils.Helpers
import br.com.anonymize.utils.FileUtils
import com.typesafe.config.ConfigFactory
import java.security.MessageDigest
import java.util
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import org.apache.commons.codec.binary.Base64


object Encryption {

    val log: Logger = Logger.getLogger(Decrypto.getClass)
    val configManager = ConfigFactory.load()

    def encrypt(key: String, value: String): String = {
        val cipher: Cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, keyToSpec(key))
        Base64.encodeBase64String(cipher.doFinal(value.getBytes("UTF-8")))
    }

    def decrypt(key: String, encryptedValue: String): String = {
        val cipher: Cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
        cipher.init(Cipher.DECRYPT_MODE, keyToSpec(key))
        new String(cipher.doFinal(Base64.decodeBase64(encryptedValue)))
    }

    def keyToSpec(key: String): SecretKeySpec = {
        var keyBytes: Array[Byte] = (key).getBytes("UTF-8")
        val sha: MessageDigest = MessageDigest.getInstance("SHA-1")
        keyBytes = sha.digest(keyBytes)
        keyBytes = util.Arrays.copyOf(keyBytes, 16)
        new SecretKeySpec(keyBytes, "AES")
    }
}