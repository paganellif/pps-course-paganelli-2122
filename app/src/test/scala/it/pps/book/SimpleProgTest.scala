package it.pps.book

import it.pps.book.SimpleProg.isSorted
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions._

class SimpleProgTest {

  @Test def absNegativeTest() = {
    assertEquals(SimpleProg.abs(-42), 42)
  }

  @Test def absPositiveTest() = {
    assertEquals(SimpleProg.abs(42), 42)
  }

  @Test def formattedAbsTest() = {
    assertEquals(SimpleProg.formatAbs(-42), "The absolut value of -42 is 42")
  }

  @Test def intToStringTest() = {
    assertEquals(SimpleProg.objToString(2), "2")
  }

  @Test def stringToStringTest() = {
    assertEquals(SimpleProg.objToString("ciao"), "ciao")
  }

  @Test def isSortedTest(): Unit = {
    val zeroToTen: Array[Int] = Array(0,1,2,3,4,5,6,7,8,9,10)

    val ascOrder = isSorted[Int]((a,b) => a < b)(_)
    val descOrder = isSorted[Int]((a,b) => a > b)(_)

    assertTrue(ascOrder(zeroToTen))
    assertFalse(descOrder(zeroToTen))
    assertTrue(descOrder(zeroToTen.reverse))
    assertFalse(ascOrder(Array()))
  }

}
