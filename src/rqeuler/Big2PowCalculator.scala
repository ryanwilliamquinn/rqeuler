package rqeuler

object Big2PowCalculator {

  def main(args: Array[String]) {

    var sum = "1"
    var a= 0
    var b=0
    var toAdd = ""
    while (a < 1000) { // while(a < 4) = 2^4 = 16
      var sumLength = sum.length
      b = sumLength - 1
      var myChars = sum.toCharArray
      while (b >= 0) {
        var numberPart = (2 * myChars(b).asDigit).toString
        var zerosPart = ""
        if (b+1 < sumLength) {
          zerosPart = (1 to (sumLength - (b + 1))).toList.map(x => "0").mkString("")
        }
        toAdd += numberPart + zerosPart + "\n"
        b -= 1
      }
      sum = BigSum.add(toAdd.trim)
      toAdd = ""
      a += 1
    }
    println("new sum is: " + sum)
    println("sum of all digits is: " + sum.toCharArray.map(x => x.asDigit).sum)
  }

}
