package it.pps.course.u04lab

import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class PowerIteratorsTest {

  val factory = new PowerIteratorsFactoryImpl()

  @Test def testIncremental(): Unit = {
    val pi = factory.incremental(5,_+2) // pi produce 5,7,9,11,13,...
    assertEquals(Option(5), pi.next())
    assertEquals(Option(7), pi.next())
    assertEquals(Option(9), pi.next())
    assertEquals(Option(11), pi.next())
    assertEquals(List(5, 7, 9, 11), pi.allSoFar()) // elementi già prodotti
    for (i <- 0 until 10)
      pi.next(); // procedo in avanti per un po'..
    assertEquals(Option(33), pi.next()) // sono arrivato a 33
  }

  @Test def testRandomBoolean(): Unit = {
    val size = 5
    val pi = factory.randomBooleans(size)

    assertNotEquals(Option.empty, pi.next())
    assertNotEquals(Option.empty, pi.next())
    assertNotEquals(Option.empty, pi.next())
    assertNotEquals(Option.empty, pi.next())
    assertNotEquals(Option.empty, pi.next())
    assertEquals(Option.empty, pi.next())
    assertEquals(size, pi.allSoFar().length)
  }
}