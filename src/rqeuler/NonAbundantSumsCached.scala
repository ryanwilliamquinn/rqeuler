package rqeuler

/**
A perfect number is a number for which the sum of its proper divisors is exactly equal to the number.
  For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.

  A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.

  As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant numbers is 24.
  By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers.
  However, this upper limit cannot be reduced any further by analysis even though it is known that the greatest number that cannot
  be expressed as the sum of two abundant numbers is less than this limit.

  Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.

  number = abundant if sum of divisors is greater than itself

  find all positive integers which are not the sum of two abundant numbers
  we know that the biggest is 28123

  one way:
  find all abundant numbers less than 28123
  find all sums of these that are less than 28123
  find all numbers that are not part of this list
  gnarly

  another way:
  find all abundant numbers less than 28123
  for all numbers less than 28123, check if you can add up to that number
  could possible do some caching?

  anyway have to find all abundant numbers
  */


object NonAbundantSumsCached {

  def main(args: Array[String]) {

    val start = System.currentTimeMillis
    //println("answer is: " + add("2100\n18000"))
    var abundantNumbers = List[Int]()
    var sumOfFactors = 0
    var factors = List[Int]()
    var max = 28123
    for (x <- 1 to max) {
      var factors = Utility.getFactors(x, false)
      var sumOfFactors = factors.sum
      if (sumOfFactors > x) {
        //println("factors for " + x + ": " + factors)
        //printf("x is: %d, sum of factors is %d\n", x, sumOfFactors)
        abundantNumbers = abundantNumbers.::(x)
      }
    }
    abundantNumbers = abundantNumbers.reverse

    println(abundantNumbers)
    //println(abundantNumbers.length)

    var abundantNumbersLength = abundantNumbers.length - 1
    var nonAbundantNumbers = List[Int]()
    var cachedSums = List[Int]()
    var a = 0
    var b = 1
    while (a < abundantNumbersLength) {
      var aNum = abundantNumbers(a)
      var b = a + 1
      while (b < abundantNumbersLength) {
        if (b > a) {
          var bNum = abundantNumbers(b)
          var tempSum = aNum+bNum
          if (aNum + bNum < max) {
            //printf("a is %d, b is %d, tempsum is %d\n", a, b, tempSum)
            cachedSums = cachedSums.::(tempSum)
          }

        }
        b += 1
      }
      cachedSums = cachedSums.distinct
      a += 1
    }
    val stop = System.currentTimeMillis
    println("Time taken: " + (stop - start) + "ms")

    println("cachedsums length: " + cachedSums.length)

    for (y <- 1 to max) {
      if (!cachedSums.contains(y)) {
        nonAbundantNumbers = nonAbundantNumbers.::(y)
      }
    }

    println("numbers not the sum of two abundant numbers: " + nonAbundantNumbers)
    println(nonAbundantNumbers.sum)
  }



}
