package rqeuler

import scala.io.Source

/**
 * invert values from max value per row, use brute force with short circuiting
 * possible number of paths = 2 to the (num rows - 1)
 *
 * undo the inverted system?  try it with just caching the diagonals?  crraaaaaaap
 */
object MaxPathSum {

    var diffed = List[List[Int]]()
    var pathCount = 0
    var lowestSum = -1
    var lowestPath: List[Tuple2[Int,Int]]= null
    var lookup = List[List[Int]]()
    var cache = collection.mutable.HashMap[String,Int]()
    val data = Source.fromFile("/Users/Lauren/Documents/triangle.txt").getLines.toList.map(x => x.split(" ").toList.map(x => Integer.parseInt(x)))
    //val data = Source.fromFile("/Users/Lauren/Documents/testGetLines.txt").getLines.toList.map(x => x.split(" ").toList.map(x => Integer.parseInt(x)))


  def main(args: Array[String]) {
    //val input = "75\n95 64\n17 47 82\n18 35 87 10\n20 04 82 47 65\n19 01 23 75 03 34\n88 02 77 73 07 63 67\n99 65 04 28 06 16 70 92\n41 41 26 56 83 40 80 70 33\n41 48 72 33 47 32 37 16 94 29\n53 71 44 65 25 43 91 52 97 51 14\n70 11 33 28 77 73 17 78 39 68 17 57\n91 71 52 38 17 14 91 43 58 50 27 29 48\n63 66 04 68 89 53 67 30 73 16 69 87 40 31\n04 62 98 27 23 09 70 98 73 93 38 53 60 04 23"
    //val input = "3\n7 4\n2 4 6\n8 5 9 3"



    //val inputStrings = input.split("\\n").toList
    //val data2 = inputStrings.map(x => x.split(" ").toList.map(x => Integer.parseInt(x)))
    //println(data)
    //println(data2)
    diffed = rewriteArray(data)
    doCaching()
    lookup = data.reverse
    var a = 0
    var startPoints = diffed(0).length - 1
    //println("startpoints: " + startPoints)
    for (a <- 0 to startPoints) {
      traverse(a, 0, 0, List())
    }
    //println("values: ")
    var total = 0
    for ((x,y) <- lowestPath) {
      var tempValue = lookup(y)(x)
      //println(tempValue)
      total += tempValue
    }
    println("final path is: " + lowestPath)
    println("final sum is: " + total)

  }

  def rewriteArray(data: List[List[Int]]): List[List[Int]] = {
    var tempDiffed = List[List[Int]]()
    var a = data.length - 1
    while (a >= 0) {
      var max = data(a).max
      var tempArray: List[Int] = data(a).map(x => max - x)
      tempDiffed = tempDiffed :+ tempArray
      a -= 1
    }

    return tempDiffed
  }

  /** traversing rules
    *   if your position is within 1, you can access that value in the upper layer except:
    *   if your value = 1, you can only access the first position of the upper layer.
    *   if your value = n, you can only access n-1 of the upper layer
    *   last traversal system spawned another call to traverse at every decision point
    *   x is location along an individual array, y is location among the stack of arrays, sum is the total, path is the history
    */
  def traverse(x: Int, y: Int, sum: Int, path: List[Tuple2[Int,Int]]) {



    //println("x value is: " + x + ", y value is: " + y + ", cell value is: " + diffed(y)(x))
    //println("this row is: " + diffed(y))

    // add current position
    var newPath = path :+ (x,y)
    //println("path is now: " + newPath)
    var key = x + "_" + y
    var newSum = 0
    var isCached = false
    if (cache.contains(key)) {
      println("cache hit")
      newSum = cache(key) + sum
      println("path is: " + newPath + ", sum is: " + newSum + ", original sum was: " + sum)
      isCached = true
    } else {
      newSum = diffed(y)(x) + sum
    }
    //println("sum is: " + newSum)

    // move again if there are still rows above us and our newSum is still lower than our lowestSum.
    // if newSum is already higher than lowestSum, we can stop this path
    if (!isCached && y < (diffed.length - 1)) {
      if (lowestSum > 0 && newSum > lowestSum) {
        println("useless path! x,y value is: " + x + ", " + y + ".  lowest sum is: " + lowestSum + ", and the current sum is already: " + newSum + ", path was: " + newPath)

        // do nothing
      } else {
        // see if we need to spawn one traverse or two
        if (x == (diffed(y).length - 1)) {
          traverse(x-1, y+1, newSum, newPath)
        } else if (x == 0) {
          traverse(0, y+1, newSum, newPath)
        } else {
          traverse(x-1, y+1, newSum, newPath)
          traverse(x, y+1, newSum, newPath)
        }
      }
    } else {
      pathCount += 1
      if (lowestSum < 0 || newSum < lowestSum) {
        lowestSum = newSum
        lowestPath = newPath
      }
      //println("we made it to the end, path was: " + newPath + ", and the sum was: " + newSum)
      println("current pathcount is: " + pathCount)
      //println("current lowest sum is: " + lowestSum)
      //println("current lowest path is: " + lowestPath)
      //println("lowest sum wins")
    }
  }



    def doCaching() {
      // do stuff!
      println("diffed: " + diffed)
      var length = diffed.length
      for (a <- 0 to (length - 1)) {
        var tempPath = diffed.slice(a,length) // this gives us a slice from different end points to the beginning
        println(tempPath)
        var sum = tempPath.map(x => x(0)).sum
        var key = 0 + "_" + a
        cache(key) = sum
        //reversedData.splice()
      }
      println(cache)
    }

}
