
import java.security.MessageDigest

/**
  * Represents a block in the chain
  *
  * @param index    the index of this block in the chain
  * @param payload  the payload data
  * @param lastHash hash of the previous block
  */
case class SimpleBlock(index: Int, payload: String, lastHash: String) {

  val hash: String =
    MessageDigest.getInstance("MD5")
      .digest(s"$index$payload$lastHash".getBytes("UTF-8"))
      .map("%02x".format(_))
      .mkString

}
