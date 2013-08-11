package rqeuler

/**
 *
 *  1 Jan 1900 was a Monday.
 *  Thirty days has September,
 *  April, June and November.
 *  All the rest have thirty-one,
 *  Saving February alone,
 *  Which has twenty-eight, rain or shine.
 *  And on leap years, twenty-nine.
 *  A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
 *  How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)
 */
object Sundays {

  def main(args: Array[String]) {
    val daysLookup = List("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    val monthLookup = List("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    val daysPerMonth = List(31,28,31,30,31,30,31,31,30,31,30,31)
    var year = 1900
    var dayOfWeek = 0 // 0 is monday, 6 is sunday
    var month = 0
    var sundayCounter = 0
    var yearlySundayCounter = 0


    /*
      start at day 0, move a month forward (if month == 1, work out the leap year nonsense
      check use modulo 7 to figure out what day it is.  rinse, repeat
     */

    while (year < 2001) {
      //println("first day of: " + monthLookup(month) + " is " + daysLookup(dayOfWeek) + " for " + year)
      if (dayOfWeek == 6) {
        //println("found sunday, month: " + monthLookup(month) + ", year: " + year)
        if (year > 1900)
        sundayCounter += 1
        yearlySundayCounter +=1
      }
      var daysInMonth = daysPerMonth(month)
      if (month == 1) {
        if ((year % 4 == 0 && year % 100 != 0) ||  (year % 4 == 0 && year % 100 == 0 && year % 400 == 0)) {
          daysInMonth = 29
        }
      }
      dayOfWeek = (dayOfWeek + daysInMonth) % 7
      if (month == 11) {
        println("sunday count for: " + year + " was: " + yearlySundayCounter)
        year += 1
        yearlySundayCounter = 0
        month = 0
      } else {
        month += 1
      }
    }
    println("how many sundays: " + sundayCounter)
  }

}
