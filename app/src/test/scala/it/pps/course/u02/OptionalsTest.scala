package it.pps.course.u02

import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test
import it.pps.course.u02.Optionals.Option._

class OptionalsTest {

  @Test def filterOptionTest(): Unit = {
    val filtered = filter[Int](Optionals.Option.Some(5))(_)
    assertTrue(!isEmpty(filtered(_ > 2)))
    assertTrue(isEmpty(filtered(_ > 8)))
  }

  @Test def mapOptionTest(): Unit = {
    assertEquals(true, getOrElse(map(Some(5))(_ > 2), None))
    assertTrue(isEmpty(map(None[Int]())(_ > 2)))
  }

  @Test def map2OptionTest(): Unit = {
    val mapped = map2[Int, Boolean](Some(2),Some(2))(_)
    assertTrue(getOrElse(mapped((a,b) => a == b), false))
  }

}
