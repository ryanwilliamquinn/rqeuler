package rqeuler

/**
 *

Euler discovered the remarkable quadratic formula:

n² + n + 41

It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39.
However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly when n = 41, 41² + 41 + 41 is clearly divisible by 41.

The incredible formula  n² − 79n + 1601 was discovered, which produces 80 primes for the consecutive values n = 0 to 79.
The product of the coefficients, −79 and 1601, is −126479.

Considering quadratics of the form:

    n² + an + b, where |a| < 1000 and |b| < 1000

    where |n| is the modulus/absolute value of n
    e.g. |11| = 11 and |−4| = 4

Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes for consecutive values of n,
starting with n = 0.

 */
object QuadraticPrimes {



  // find a and b where a < 1000 and b < 1000 that produces the most primes for consecutive values of n, starting with 0
  // for the equation n2 +an + b

  // brute force iterate all primes under 1000 for every a, check against another list of the first 1000 primes

  def main(args: Array[String]) {
    val start = System.currentTimeMillis()

    val primesUnder1000 = Utility.getPrimesList(1000)
    val primesUnder1000000 = Utility.getPrimesList(1000000)
    var a = -999
    var b = -999l
    var output = 0l
    var maxLength = 0
    var coefficients = ""
    var productOfCoefficients = 0l


    while (a < 1000) {
      for (i <- 0 to primesUnder1000.length - 1) {
        b = primesUnder1000(i)
        var n = 0
        var outputIsPrime = true
        while (outputIsPrime) {
          output = n*n + a*n + b
          //printf("output is: %d, n is %d, a is %d, b is %d\n", output, n, a, b)
          if (!primesUnder1000000.contains(output)) {
            outputIsPrime = false
          } else {
            n += 1
            if (n > maxLength) {
              coefficients = "a, b = " + a + ", " + b
              productOfCoefficients = a * b
              maxLength = n
            }
          }
        }
      }
      a += 1
    }
    val stop = System.currentTimeMillis
    println("Time taken: " + (stop - start) + "ms")
    println("maxLength is: " + maxLength)
    println(coefficients)
    println("product of coefficients is: " + productOfCoefficients)



  }

}
