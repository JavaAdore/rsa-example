import java.io.UnsupportedEncodingException
import java.nio.file.{Files, Paths}
import java.security.interfaces.RSAPublicKey
import java.security.spec.{PKCS8EncodedKeySpec, X509EncodedKeySpec}
import java.security._
import java.util.Base64
import javax.crypto.{BadPaddingException, Cipher, IllegalBlockSizeException, NoSuchPaddingException}
class Main {



  // loading private key from classpath
  var privateKeyContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("test-private-key.pem").toURI)))

  // loading public key from classpath
  var publicKeyContent = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("test-public-key.pem").toURI)))

  // removing comments, empty lines from private key
  privateKeyContent = privateKeyContent.replaceAll("\\n", "")
                                       .replace("-----BEGIN PRIVATE KEY-----", "")
                                       .replace("-----END PRIVATE KEY-----", "")
                                       .replaceAll("\\r","")
  // removing comments, empty lines from public key
  publicKeyContent = publicKeyContent.replaceAll("\\n", "")
                                      .replace("-----BEGIN PUBLIC KEY-----", "")
                                      .replace("-----END PUBLIC KEY-----", "")
                                      .replaceAll("\\r","")

  // initiating instance of RSA key factory
  val kf = KeyFactory.getInstance("RSA")
  // initiating instance of PKCS8EncodedKeySpec
  val keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder.decode(privateKeyContent))
  // generating private key instance
  val privateKey = kf.generatePrivate(keySpecPKCS8)

  // initiating instance of X509EncodedKeySpec
  val keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder.decode(publicKeyContent))

  // generating public key instance
  val publicKey = kf.generatePublic(keySpecX509).asInstanceOf[RSAPublicKey]


  // exceptions might be thrown
  @throws[NoSuchAlgorithmException]
  @throws[NoSuchPaddingException]
  @throws[UnsupportedEncodingException]
  @throws[IllegalBlockSizeException]
  @throws[BadPaddingException]
  @throws[InvalidKeyException]
  private def encryptText(msg: String, privateKey: PrivateKey):String = {
    // initiating instance of RSA
    var cipher = Cipher.getInstance("RSA")
    // initialize cipher for encryption using private key
    cipher.init(Cipher.ENCRYPT_MODE, privateKey)
    // encrypted message
    val encryptedValue:Array[Byte] = cipher.doFinal(msg.getBytes("UTF-8"))
    //  encoding encrypted message
    val encodedMessage:Array[Byte] = Base64.getEncoder.encode(encryptedValue)
    // returning encrypted message as a string
    new String(encodedMessage)
  }


  // exceptions might be thrown
  @throws[NoSuchAlgorithmException]
  @throws[NoSuchPaddingException]
  @throws[UnsupportedEncodingException]
  @throws[IllegalBlockSizeException]
  @throws[BadPaddingException]
  @throws[InvalidKeyException]
  def encryptText(msg: String):String = {
    encryptText(msg,privateKey)
  }

 private def decryptText(msg: String, publicKey: PublicKey):String = {
    // initiating instance of RSA
    var cipher = Cipher.getInstance("RSA")
    // initialize cipher for decryption using public key
    cipher.init(Cipher.DECRYPT_MODE, publicKey)
    // decoded encrypted message
   val decodedEncryptedMessage = Base64.getDecoder.decode(msg)
    // decrypting message
    val decryptedMessage:Array[Byte] = cipher.doFinal(decodedEncryptedMessage);
    // returning message as a string
    new String(decryptedMessage)
  }

  def decryptText(msg: String):String = {
    decryptText(msg,publicKey)
  }


  def main(stringArr:Array[String]) {
    val encryptedValue = encryptText("welcome mahmoud it works perfectly")
    println(s"encrypted value is $encryptedValue")
    val decryptedValue = decryptText(encryptedValue)
    println(s"decrypted message is $decryptedValue")

  }
 }
