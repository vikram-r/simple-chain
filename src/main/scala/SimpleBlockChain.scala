
class SimpleBlockChain(val blockChain: List[SimpleBlock]) {

  def this(genesisPayload: String) = this(List(SimpleBlock(index = 0, lastHash = "0", payload = genesisPayload)))

  def genesisBlock: SimpleBlock = blockChain.head

  def :+(payload: String): SimpleBlockChain = {
    new SimpleBlockChain(blockChain :+ SimpleBlock(
      index = blockChain.last.index + 1,
      lastHash = blockChain.last.hash,
      payload = payload
    ))
  }
}

object SimpleBlockChain {
  def validateBlock(prev: SimpleBlock, next: SimpleBlock): Boolean = {
    next.index == prev.index + 1 && next.lastHash == prev.hash
  }

  def validateChain(expectedGenesis: SimpleBlock, chain: SimpleBlockChain): Boolean = {
    chain.genesisBlock == expectedGenesis &&
      chain.blockChain.sliding(2).forall(p ⇒ p.length == 1 || SimpleBlockChain.validateBlock(p.head, p(1)))
  }
}
