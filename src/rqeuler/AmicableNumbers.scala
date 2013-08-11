package rqeuler

/**
 *
 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
 * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.

 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284.
 * The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.

 * Evaluate the sum of all the amicable numbers under 10000.
 *
 * check each number for sum of proper divisors, then check that next number.  cache it all obviously
 */
object AmicableNumbers {

  def main(args: Array[String]) {
    var firstSum = 0
    var secondSum = 0
    var amicableNumbers = List[Int]()
    for (b <- 2 to 9999) {
      //println(b)
      firstSum = sumOfProperDivisors(b)
      if (firstSum != b) {
        secondSum = sumOfProperDivisors(firstSum)
      }
      if (b == secondSum) {
        amicableNumbers = amicableNumbers :+ firstSum
        amicableNumbers = amicableNumbers :+ secondSum
      }
    }
    println(amicableNumbers.distinct.sum)
  }

  def sumOfProperDivisors(number: Int):Int = {
    var a = 1
    var sum = 0
    while (a <= (number / 2)) {
      if (number % a == 0) {
        sum += a
      }
      a += 1
    }
    return sum
  }

}
