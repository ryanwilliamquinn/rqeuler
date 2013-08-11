package rqeuler

/**
 * Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
 * repeat cycle length will be driven by the largest prime factor of the dividend
 * (but not connected with the length of the representation of that factor -- see 1/7 in decimal),
 * but the first cycle length may differ from the repeat unit (e.g. 11/28 = 1/4+1/7 in decimal).
 * When x = 1/k for some integer k, it also shows that the period of 1/k is the same as the order of 10 modulo k
 *
 */
object RecurringDecimal {

  def main(args: Array[String]) {
    var start = System.currentTimeMillis
    var myData = (1 to 1000).map(x => periodLengths(x.toLong))
    val stop = System.currentTimeMillis
    println("Time taken: " + (stop - start) + "ms")
    println(myData.indexOf(myData.max) + 1)

  }

  def periodLengths(num:Long):Int = {
    var primePeriodLengths = List[Int]()
    var factorMap = Utility.primeFactorization(num)
    //var numZeros = List(if (factorMap.contains("2")) factorMap("2") else 0, if (factorMap.contains("5")) factorMap("5") else 0).max
    var filteredFactorMap = factorMap.filterKeys(x => !(x.equals("2") || x.equals("5"))).keysIterator
    while(filteredFactorMap.hasNext) {
      var prime = Integer.parseInt(filteredFactorMap.next())
      var power = 0
      var factors = Utility.getFactors(prime - 1, true)
      var isFoundK = false
      var lowestK = 0
      while (!isFoundK) {
        var i = 0
        while (i < factors.length) { // iterate the possible factors, looking for the smallest one that will divide evenly
          power = factors(i)
          var pe = BigDecimal("10").pow(power) - 1
          if (pe % prime == 0) {
            isFoundK = true
            lowestK = power
            i = factors.length
          } else {
            power += 1
            pe = BigDecimal("10").pow(power) - 1
          }
          i += 1
        }
      }
      var primeFactor = 1
      var primeFactorValue = BigDecimal(prime).pow(primeFactor)
      var kValue = BigDecimal("10").pow(lowestK) - 1

      while (kValue > primeFactorValue ) {
        primeFactor += 1
        primeFactorValue = BigDecimal(prime).pow(primeFactor)
      }
      var n = factorMap(prime.toString)
      var k = lowestK
      var e = primeFactor
      var period = 0d
      if (n <= e && n <= 3) {
        period = k
      } else {
        period = k * Math.pow(prime, (n - e))
      }
      primePeriodLengths = primePeriodLengths.::(period.toInt)
    }
    var lcm = 0
    if (primePeriodLengths.length > 1) {
      lcm = Utility.lowestCommonMultiple(primePeriodLengths(0), primePeriodLengths(1))
      for (i <- 1 to (primePeriodLengths.length - 2)) {
        var p2 = primePeriodLengths(i + 1)
        lcm = Utility.lowestCommonMultiple(lcm, p2)
      }
    } else if (primePeriodLengths.length > 0) {
      lcm = primePeriodLengths(0)
    }
    lcm
  }
}
