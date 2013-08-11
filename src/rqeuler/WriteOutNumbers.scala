package rqeuler

/**
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
 *
 * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance with British usage.
 */
object WriteOutNumbers {

  // so we append hundred and thousand where appropriate
  // if the last two numbers are 0s we leave out and, otherwise put it in if its greater than 100
  def main(args: Array[String]) {
  var wordMap = collection.immutable.HashMap(0 -> "", 1 -> "one", 2 -> "two", 3 -> "three", 4 -> "four", 5 -> "five", 6 -> "six", 7 -> "seven", 8 -> "eight",
                 9 -> "nine", 10 -> "ten", 11 -> "eleven", 12 -> "twelve", 13 -> "thirteen", 14 -> "fourteen",
                 15 -> "fifteen", 16 -> "sixteen", 17 -> "seventeen", 18 -> "eighteen", 19 -> "nineteen",
                 20 -> "twenty", 30 -> "thirty", 40 -> "forty", 50 -> "fifty", 60 -> "sixty", 70 -> "seventy", 80 -> "eighty", 90 -> "ninety", 1000 -> "onethousand")

    var a = 1
    var numString = ""
    while (a <= 1000) {
      if (wordMap.contains(a)) {
        numString += wordMap(a)
        println(wordMap(a))
      } else if (a > 19 && a < 100) {
        var singles = a % 10
        var tens = a - singles
        //println("singles: " + singles)
        //println("tens: " + tens)
        //println(wordMap(tens) + wordMap(singles))
        numString += wordMap(tens) + wordMap(singles)
        println(wordMap(tens) + wordMap(singles))
      } else {
        if (a % 100 == 0) {
          // we have an even hundreds number
          numString += wordMap(a/100) + "hundred"
          println(wordMap(a/100) + "hundred")
        } else if (a % 100 > 10 && a % 100 < 20) {
          // special section for freaking teens!
          var teens = a % 100
          var hundreds = (a - teens) / 100
          numString += wordMap(hundreds)  + "hundredand" + wordMap(teens)
          println(wordMap(hundreds)  + "hundredand" + wordMap(teens))
        } else {
            var singles = a % 10
            var tens = (a % 100) - singles
            var hundreds = (a - (tens + singles)) / 100
            /*
            println(a)
            println("singles: " + singles)
            println("tens: " + tens)
            println("hundreds: " + hundreds)
            println(wordMap(hundreds) + "hundredand" + wordMap(tens) + wordMap(singles))
            */
            println(wordMap(hundreds) + "hundredand" + wordMap(tens) + wordMap(singles))
            numString += wordMap(hundreds) + "hundredand" + wordMap(tens) + wordMap(singles)
        }
      }
      a += 1

    }

    println(numString.length)
  }

}
