
/**
  * Represents a block in the chain
  * @param index the index of this block in the chain
  * @param payload the payload data
  */
case class SimpleBlock(index: Int, payload: String, hash: String, lastHash: String)
