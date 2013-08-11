package rqeuler

/**
 * Created with IntelliJ IDEA.
 * okay, track a route, each should be 4 long
 *
 */
object RoutesInGrid {

  val length = 4
  val max = length * length
  var options = Array.fill(max + 1){0}
  //var grid = Array.fill(length,length){0}
  var routes = List[Array[Int]]()
  var newRoute = false
  /*

    location = 1
    i call move right, tempLocation = 2 (tempLocation % length != 1

   */

  var location = 1
  def main(args: Array[String]) {
    var f = 0
    for (f <- 1 to max) {

      // can make this simpler
      if (f % length == 0) {
        if (f == max) {
          options(f) = 0
        } else {
          options(f) = 1
        }
      } else if (f < max - length) {
        options(f) = 2
      } else {
        options(f) = 1
      }
      /*
      if (f % length == 1) { // we are in the first column, so we can go down and right  (3x3 = 1,4,7)
        if (f == (max - length + 1)) { // unless we are in the last row, and we can only go right (3x3 = 7)
          options(f) = 1
        } else {
          options(f) = 2
        }
      } else if (f % length == 0) { // we are in the last column
        if (f == max) { // the end point, no options there
          options(f) = 0
        } else {
          options(f) = 1 // last row, can only go down (3x3 = 3,6
        }
      } else if (f > (max - length)) { // this means we are in the bottom row
        if (f != 9) {
          options(f) = 1
        }
      } else {
        options(f) = 2
      }
      */
    }

    var counter = 2



    //println(options.toList)

    while (options.sum > 0) {
      if (location == max) {
        location = 1
        if (newRoute) {
          counter += 1
        }
        newRoute = false
      }
      if ((Math.random() * 10).toInt % 2 == 0) {
        right()
      } else {
        down()
      }
      println("counter: " + counter)
      println("location is: " + location)
      println("remaining options:")
      println(options.toList)
    }
    println("max trails found: " + counter)

    /*
    1,2,3,4,7

    1 2 3
    4 5 6
    7 8 9
    */


    /*

      so, we need to track the routes we take
      if the routes are meaningfully numbered, we can't use them to track if we have been there
      if we have a thing n wide, we can say (do all the 1-right moves first:
      1move one right, then one down, then one right, then one down (done)
      two right moves:
      2two right, one down, one down
      one down moves:
      3one down, one right, one down, one right
      two down moves:
      4two down, one right, one right

      can we track the open paths?

      start, check if right and left are possible?
      both possible, so 1 -> 2,4
      pick first one (always go right first)
      (1->2,3), (2->3,5)
      go right first
      (1->2,3), (2->3,5), (3->6)
      (1->2,3), (2->3,5), (3->6), (6->9)
      route = 1,2,3,6,9

     */
  }

  def right(): Boolean = {
    var tempLocation = location + 1
    println("right")
    if (tempLocation % length != 1) {
      if (options(location) > 0) {
        newRoute = true
        options(location) -= 1
      }
      location += 1
      println("location after += : " + location)
      return true
    }
    false
  }

  def down(): Boolean = {
    var tempLocation = location + length
    println("down")
    if (tempLocation <= max) {
      if (options(location) > 0) {
        newRoute = true
        options(location) -= 1
      }
      location += length
      return true
    }
    false
  }

}
