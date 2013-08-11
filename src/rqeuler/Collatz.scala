package rqeuler

import scala.collection.immutable.TreeMap

/**
 * Created with IntelliJ IDEA.
 * n → n/2 (n is even)
 * n → 3n + 1 (n is odd)
 * find longest string with starting number under 1 million
 * if you start with 13, string should be 10 (including 13)
 *
 * you could store a bunch of previous numbers, and the additional chain length they had, in a map!
 *
 */

object Collatz {
  //var cache = TreeMap.empty[Long, Int]
  var highestChainLength = 1
  var highestIndex = 1l
  def main(args: Array[String]) {
    val start = System.currentTimeMillis
    (1 to 999999).toList.foreach(x => collatz(x.toLong, 1, x))
    println("start number: " + highestIndex + " has length: " + highestChainLength)
    val stop = System.currentTimeMillis
    println("Time taken: " + (stop - start) + "ms")
  }

  def collatz(num: Long, chain: Int, orig: Int) {
    if (num == 1) {
      isHighest(orig, chain)
    } else {
      var tempNum = num
      if (num % 2 == 0) {
        tempNum = num / 2
      } else {
        tempNum = (3 * num) + 1
      }
      collatz(tempNum, chain + 1, orig)
    }
  }

  // does caching, and value comparison
  def isHighest(orig: Long, lngth: Int) {
    //cache += orig -> lngth
    if (lngth > highestChainLength) {
      // cache it
      highestIndex = orig
      highestChainLength = lngth
    }
  }
}
