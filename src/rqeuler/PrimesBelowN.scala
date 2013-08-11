package rqeuler

/**
 * need a fast way to determine if a number is prime
 */
object PrimesBelowN {

  var primesList = List[Long]()

  def main(args: Array[String]) {
    val myList = go3(2000000)
    println(myList)

  }

  def go3(max: Int): BigInt = {
    val prime = Array.fill(max + 1){true}
    var a=0
    for (a <- 2 to max) {
      if (prime(a)) { // if the value of the prime is false, skip it
        var b = a + a // do the initial addition, since we don't want to influence 2 and 3
        while (b <= max) {
          prime(b) = false
          b += a
        }
      }
    }

    var sum : BigInt = 0
    var c = 0
    for (c <- 2 to max) {
      if (prime(c)) {
        primesList = c :: primesList
        sum += c
      }
    }
    primesList.reverse
    sum
  }

  def getPrimesList(max: Int): List[Long] = {
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

}
