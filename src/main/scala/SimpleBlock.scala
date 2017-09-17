
import java.security.MessageDigest
import java.time.Instant

/**
  * Represents a block in the chain
  *
  * @param index    the index of this block in the chain
  * @param lastHash hash of the previous block
  * @param payload  the payload data
  * @param createTs ISO-8601 timestamp of when this block was created
  */
case class SimpleBlock(index: Int,
                       lastHash: String,
                       payload: String,
                       createTs: String = Instant.now().toString) {

  val hash: String =
    MessageDigest.getInstance("MD5")
      .digest(s"$index$lastHash$payload$createTs".getBytes("UTF-8"))
      .map("%02x".format(_))
      .mkString

}
