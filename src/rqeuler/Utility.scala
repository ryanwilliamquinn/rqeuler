package rqeuler

/**
 * Created with IntelliJ IDEA.
 * User: Lauren
 * Date: 8/2/13
 * Time: 12:02 21PM
 * To change this template use File | Settings | File Templates.
 */
object Utility {


  // look at this, make it fasterrrrr:   http://www.math.mtu.edu/mathlab/COURSES/holt/dnt/divis2.html
  def getFactors(number: Int, isReturnSelfAsFactor: Boolean): List[Int] = {
    var factorList = List[Int]()
    var length = if (isReturnSelfAsFactor) number else number / 2
    for (x <- 1 to (length)) {
      if (number % x == 0) {
        factorList = factorList :+ x
      }
    }
    factorList
  }


  def multiply(first: String, second: String): String = {
    var sum = ""
    var toAdd = ""
    var firstLength = first.length
    var firstIndexLength = firstLength - 1
    var firstChars = first.toCharArray.map(x => x.asDigit)
    var secondChars = second.toCharArray.map(x => x.asDigit) // get an array of digits
    var secondLength = second.length  // length of the second multiplier
    var zerosPart = ""
    var numberPart = ""
    while (firstIndexLength >= 0) {
      // get the first number and the zeros part
      var firstNumberPart = firstChars(firstIndexLength)
      var firstZerosPart = (1 to ((firstLength - (firstIndexLength + 1)))).toList.map(x => "0").mkString("")
      var secondIndexLength = secondLength - 1 // convert length of the second multiplier to get the 0 based index
      while (secondIndexLength >= 0) {
        var secondNumberPart = secondChars(secondIndexLength)
        var secondZerosPart = ""
        if (secondIndexLength + 1 < secondLength) {
          secondZerosPart = (1 to ((secondLength - (secondIndexLength + 1)))).toList.map(x => "0").mkString("") // make a list n long of zeros, then make it a string
        }
        numberPart = (firstNumberPart * secondNumberPart).toString
        zerosPart = firstZerosPart + secondZerosPart
        toAdd += numberPart + zerosPart + "\n"
        secondIndexLength -= 1
      }
      firstIndexLength -= 1
    }
    sum = add(toAdd.trim)
    sum
  }

  def add(numString: String): String = {
    val numbers = numString.split("\\n").map(x => x.reverse)
    //println("numbers are: " + numbers.toList)
    var a = 0
    var b = 0
    var remainder = 0
    var sum = ""
    var tempSum = 0
    var tempNum = 0
    val loopLength = numbers.reduceLeft((a,b) => if(a.length>b.length) a else b).length - 1 // looplength is the length of the longer number - 1
    for (a <- 0 to loopLength) { // start at the first character of an individual number
      tempSum = 0
      for (b <- 0 to numbers.length - 1) { // start at the first number in the array of numbers
        if (a < numbers(b).length) {
          tempNum = numbers(b)(a).asDigit
          tempSum += tempNum
        }
        // now we deal with remainder
      }
      tempSum += remainder
      remainder = 0
      if (tempSum >= 10) {
        remainder = tempSum / 10
        tempSum = tempSum % 10
      }
      sum = tempSum.toString + sum
    }
    if (remainder > 0) {
      sum = remainder.toString + sum
    }
    sum
  }

  def factorial(n:Int):Int = if (n==0) 1 else n * factorial(n-1)

  def getPrimesList(max: Int): List[Long] = {
    var primesList = List[Long]()
    val prime = Array.fill(max + 1){true}
    for (a <- 2 to max) {
      if (prime(a)) { // if the value of the prime is false, skip it
      var b = a + a // do the initial addition, since we don't want to influence 2 and 3
        while (b <= max) {
          prime(b) = false
          b += a
        }
      }
    }

    for (c <- 2 to max) {
      if (prime(c)) {
        primesList = c :: primesList
      }
    }
    primesList.reverse
  }

  def primeFactorization(input:Long):collection.mutable.HashMap[String,Int] = {
    var num = input
    var primesList = Utility.getPrimesList(1000)
    var primeFactorIndex = 0
    var primeFactor = primesList(0)
    var primesMap = collection.mutable.HashMap[String,Int]()
    var key = ""
    while (primeFactor <= num) {
      //printf("prime is %d, primefactor is %d\n", num, primeFactor)
      if (num % primeFactor == 0) {
        num = num / primeFactor
        primeFactorIndex = 0
        key = primeFactor.toString
        if (primesMap.contains(key)) {
          primesMap += primeFactor.toString -> (primesMap(key) + 1)
        } else {
          primesMap += primeFactor.toString -> 1
        }

        //println("primefactor includes: " + primeFactor + ", prime is: " + prime)
        primeFactor = primesList(0)
      } else {
        primeFactorIndex += 1
        primeFactor = primesList(primeFactorIndex)
      }
      primeFactor = primesList(primeFactorIndex)
    }
    primesMap
  }

  /**
   * First, divide the first number by the second: 210/45 = 4 with the remainder of 30. This means that 210 = 4 x 45 + 30.
   *
   * Next, divide the second number (45) by the remainder from the first step (30). 45/30 = 1 with the remainder of 15. This means that 45 = 1 x 30 + 15.
   *
   * Next, divide the remainder from the first step (30) by the remainder from the second step (15). 30/15 = 2 with a remainder of 0. This means that 30 = 2 x 15 + 0.
   *
   * The GCD of 210 and 45 is 15.
   *
   * You can use this method to find the GCD any time -- just stop dividing once you reach a remainder of 0.
   *
   * Multiply the two original numbers. 210 x 45 = 9,450
   *
   * Divide the result by the GCD of the two numbers. 9,450/15 = 630. 630 is the LCM of 210 and 45.
   *
   * Use Euclid's Algorithm to find the LCM of three numbers. To do this, simply find the LCM of two numbers, and then use that LCM to find the LCM of those two numbers and the third.
   *
   *
   */

  def main(args: Array[String]) {
    println(lowestCommonMultiple(4,2))
  }

  def lowestCommonMultiple(num1:Int, num2:Int):Int = {
    if (num1 == num2) {
      return num1
    }
    var product = num1 * num2
    var gcm = greatestCommonDenominator(num1, num2)
    if (gcm != 0) {
      product / gcm
    } else {
      List(num1,num2).max
    }
  }

  def greatestCommonDenominator(num1:Int, num2:Int):Int = {
    var first = List(num1,num2).max
    var second = List(num1,num2).min
    var remainder = first % second
    var secondRemainder = 0
    if (remainder != 0) {
      secondRemainder = second % remainder
    }
    if (secondRemainder != 0) {
      var tempRemainder = 1
      while (tempRemainder != 0) {
        tempRemainder = remainder % secondRemainder
        if (tempRemainder != 0) {
          secondRemainder = remainder
          remainder = tempRemainder
        }
      }
    }
    secondRemainder
  }


}
