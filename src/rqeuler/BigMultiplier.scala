package rqeuler

/**
 * Help me multiply two arbitrarily big numbers together
 */
object BigMultiplier {

  def main(args: Array[String]) {
    println(multiply("6720", "3"))
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
    sum = BigSum.add(toAdd.trim)
    sum
  }
}
