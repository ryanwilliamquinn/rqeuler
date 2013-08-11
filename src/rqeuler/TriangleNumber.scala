package rqeuler

/**
 * find the first triangular number (sum of all numbers 1 -> n (ie 3rd triangular number = 1 + 2 + 3 = 6)) that has 500 factors
 * 1 = 1
 * 3 = 1,3
 * 6 = 1,2,3
 * 10 = 1,2,5,10
 * 15 = 1,3,5,15
 **/
object TriangleNumber {

  var primesList = List[Long]()
  var numFactors = 0

  def main(args: Array[String]) {
    val start = System.currentTimeMillis
    // start off with primes below 1k
    primesList = PrimesBelowN.getPrimesList(1000)
    var num = 1
    var counter = 2
    while (numFactors < 500) {
      calculateFactors(num)
      println("number is: " + num + ", num factors: " + numFactors )
      num = num + counter
      counter += 1
    }
    val stop = System.currentTimeMillis
    println("Time taken: " + (stop - start) + "ms")
  }

  def calculateFactors(n: Long) {
    // prime factorization:
    var factorList = new Array[Int](0) // this array has the job of keeping track of the number of factors for each prime
    var a = 0
    var maxLength = primesList.length - 1
    var tempN = n // tempN is going to be what we use to recursively check how many times a number is divisible by a prime factor
    var tempPrime = 2l
    var maxFactor = n
    for (a <- 0 to maxLength) { // iterate our list of primes
      tempPrime = primesList(a)
      while (tempPrime <= maxFactor && tempN % tempPrime == 0) {

        factorList = addToArray(a, factorList)
        tempN = tempN / tempPrime
      }
      tempN = n // reset the temp value
    }
    for (a <- 0 to factorList.length - 1) {
      factorList(a) = factorList(a) + 1
    }
    numFactors = factorList.toList.foldLeft(1)(_*_)
  }

  def addToArray(index: Int, arr: Array[Int]): Array[Int] = {
    // normal case will be adding to empty array, length is 0, index is 0
    if (arr.length >= (index + 1)) {
      arr(index) = arr(index) + 1
      return arr // short circuit if we have the index, else append to array and try again
    }
    var newArr = arr :+ 0
    addToArray(index, newArr)
  }
}
