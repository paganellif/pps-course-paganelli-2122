package it.pps.book

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

}
