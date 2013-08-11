package rqeuler

import scala.io.Source

/**
 *

Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first names, begin by sorting it into alphabetical order.
Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.
For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list.
So, COLIN would obtain a score of 938 × 53 = 49714.
What is the total of all the name scores in the file?

 */
object NameValue {


  def main(args: Array[String]) {
    var data = Source.fromFile("/tmp/names.txt").getLines.toList.map(x => x.replace("\"", "")).mkString.split(",").map(x => x.toLowerCase).sorted
    var numData = data.map(x => x.toCharArray.toList).map(x => x.map(x => x - 96))
    var tempData = List[Int]()
    var sum = 0
    for (x <- 1 to numData.length) {
      tempData = numData(x - 1)
      sum += (tempData.sum * x)
    }
    println(sum)
  }

}
