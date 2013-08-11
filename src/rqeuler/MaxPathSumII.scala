package rqeuler

import scala.io.Source

/**
 *
 * USEFUL STUFF IN HERE:  Iterators editing a list in place, moving a list of immutable lists to a list of mutable lists
 * invert values from max value per row, use brute force with short circuiting
 * possible number of paths = 2 to the (num rows - 1)
 *
 * undo the inverted system?  try it with just caching the diagonals?  crraaaaaaap
 */
object MaxPathSumII {

  var diffed = List[collection.mutable.LinkedList[Int]]()
  var pathCount = 0
  var lowestSum = -1
  var lowestPath: List[Tuple2[Int,Int]]= null
  var lookup = List[List[Int]]()
  val data = Source.fromFile("/Users/Lauren/Documents/triangle.txt").getLines.toList.map(x => x.split(" ").toList.map(x => Integer.parseInt(x)))
  //val data = Source.fromFile("/Users/Lauren/Documents/testGetLines.txt").getLines.toList.map(x => x.split(" ").toList.map(x => Integer.parseInt(x)))
  var cache = List[collection.mutable.LinkedList[Int]]()



  def main(args: Array[String]) {
    doCaching()
  }

  /**
   * do it just like we did manually
   * start at top of the array (y index = 0)
   * if length == 1, do nothing
   * if length > 1, add the greater of the two options in the y index above you  2]\
   * unless your x value is 0 or is length - 1, then add the option that corresponds to you in the row above
   */
  def doCaching() {
    // move data to mutable linked list:
    for (y <- 0 to (data.length - 1)) {
      var row = collection.mutable.LinkedList(data(y) :_*)
      cache = cache :+ row
    }
    println("cache: " + cache)

    val yit = cache.iterator
    var previous = collection.mutable.LinkedList[Int]()

    while (yit.hasNext) {
      var row = yit.next()
      //println("row = " + row)
      if (row.length == 1) {
        cache = cache :+ row
      } else {
        var rowLength = row.length - 1
        var xit = row.iterator
        var xcount = 0
        while (xit.hasNext) {
          var value = xit.next()
          if (xcount == 0) {
            value += previous(0)
          } else if (xcount == rowLength) {
            value += previous(xcount - 1)
          } else {
            value += List(previous(xcount),previous(xcount - 1)).max
          }
          row(xcount) = value
          xcount += 1
        }
        cache = cache :+ row
      }
      previous = row
    }
    //println("updated cache: " + cache)
    println(cache(cache.length - 1).max)
  }

}
