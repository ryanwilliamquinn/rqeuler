package rqeuler

/**
 *
    n! means n × (n − 1) × ... × 3 × 2 × 1

    For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
    and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.

    Find the sum of the digits in the number 100!
 */
object Factorial {

  def main(args: Array[String]) {
    println(factorial("100").toCharArray.map(x => x.asDigit).sum)

    //println("final product is: " + product)
    //println("sum is: " + product.toString().toCharArray.map(x => x.asDigit).sum)
  }

  def factorial(first: String): String = {
    if (first.equals("1")) {
      return first
    } else {
      //println("first: " + first + ", second: " + secondFactor)
      return BigMultiplier.multiply(first, factorial((Integer.parseInt(first) - 1).toString))
    }
  }

}
