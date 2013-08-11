package rqeuler

import scala.collection.mutable.ArrayBuffer

/**

    A permutation is an ordered arrangement of objects.
    For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4.
    If all of the permutations are listed numerically or alphabetically, we call it lexicographic order.
    The lexicographic permutations of 0, 1 and 2 are:

    012   021   102   120   201   210

    ordering goes from lowest to highest numerically

    What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?

 */
object LexicographicPermutations {

  var shift = 0


  def main(args: Array[String]) {
    val start = System.currentTimeMillis

    var sequence = ArrayBuffer(0,1,2,3,4,5,6,7,8,9)
    var state = ""
    for (x <- 0 to 999999) {
      if (x == 0) {
        state = sequence.mkString
      } else {
        if (x == 1 || x % 2 == 1) {
          state = twiddle(sequence).mkString
        } else {
          state = reshift(sequence).mkString
        }
      }
    }
    val stop = System.currentTimeMillis
    println("Time taken: " + (stop - start) + "ms")


    println(state)


  }

  def twiddle(input: ArrayBuffer[Int]):ArrayBuffer[Int] = {
    val length = input.length - 1
    if (length < 1) {
      return ArrayBuffer[Int]()
    }
    var originalLastValue = input(length)
    var originalSecondToLastValue = input(length - 1)
    input(length) = originalSecondToLastValue
    input(length - 1) = originalLastValue
    input
  }

  // shift index is what level we are shifting on
  def reshift(input: ArrayBuffer[Int]):ArrayBuffer[Int] = {
    // shift happens at input length - (1 + shiftIndex + 2) // 1 is for the 0 based length, 2 is to account for twiddles, shift is to account for more
    var shiftIndex = input.length - (3 + shift)
    //println("this is the input we are shifting: " + input.toList)
    //println("in reshift, shift is: " + shift + ", shift index is: " + shiftIndex)
    if (shiftIndex >= 0) {
      // have to test if we can do a smaller shift!
      // test we can do a smaller shift by seeing if the number in shiftIndex + 1 is the min of the numerbers to its right
      var rightShiftValue = input(shiftIndex+1)
      var maxRightValue = input.slice(shiftIndex+1, input.length).max
      //println("max right value is: " + maxRightValue)
      //println("right shift value is: " + rightShiftValue)
      if (maxRightValue > rightShiftValue) {
        shift -= 1
        reshift(input)
        return input
      }
      // we need to get min of last two bits, check if it is greater than current bit
      var numberToShiftAway = input(shiftIndex)
      //println("number to shift away is: " + numberToShiftAway)
      var remainder = input.slice(shiftIndex + 1, input.length)
      //println("remainder is: " + remainder.toList)
      var remainderGreaterThanShift = remainder.filter(x => x > numberToShiftAway)
      if (remainderGreaterThanShift.length > 0) { // we can make a shift!
        var newShiftNumber = remainderGreaterThanShift.min
        var newShiftNumberIndex = remainder.indexOf(newShiftNumber)
        // replace newShiftNumber with number to shift away
        remainder(newShiftNumberIndex) = numberToShiftAway
        input(shiftIndex) = newShiftNumber
        remainder = remainder.sorted
        //println("sorted remainder is: " + remainder.toList)
        var z = 0
        //println("shiftIndex + 1 is: " + (shiftIndex + 1))
        //println("input length - 1 is: " + (input.length - 1))
        //println("remainder section length is: " + remainderGreaterThanShift.length)

        for(y <- (shiftIndex + 1) to input.length - 1) {
          //printf("y is %d, z is %d\n", y, z)
          input(y) = remainder(z)
          z += 1
        }
        //println("new shift number is: " + newShiftNumber)
      } else {  // no more shifts available here
        shift += 1
        return reshift(input)
        // but we have to go back and do all the other damn shifts
      }
    }
    //println("input is: " + input.toList)
    input
  }

}
