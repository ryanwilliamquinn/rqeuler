package rqeuler

/**
  Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:

  21 22 23 24 25
  20  7  8  9 10
  19  6  1  2 11
  18  5  4  3 12
  17 16 15 14 13

  43 44 45 46 47 48 49
  42 21 22 23 24 25 26
  41 20  7  8  9 10 27
  40 19  6  1  2 11 28
  39 18  5  4  3 12 29
  38 17 16 15 14 13 30
  37 36 35 34 33 32 31
  It can be verified that the sum of the numbers on the diagonals is 101.

  What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
  */
object NumberSpiralDiagonals {


  // maximum = dimension squared
  // only works for odd numbers
  // 1 is always in the middle
  // max is always upper right
  // corners are max - (n-1)
  // diagonal is
  // total is n - (n-1)

  def main(args: Array[String]) {
    var counter = 0
    var size = 1001
    var max = size * size
    var n = max
    var sum = max
    var subtractor = 1
    while (n > 1) {
      //println("in outer loop, n is: " + n)
      //println("subtractor is: " + subtractor)
      while (counter < 4) {
        n = n - (size - subtractor)
        //println("n is: " + n)
        if (n > 0) {
          sum += n
        }
        counter += 1
      }
      counter = 0
      subtractor += 2
    }
    println("sum is: "+ sum)
  }



}
