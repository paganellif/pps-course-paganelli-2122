package it.pps.course.u02

import it.pps.course.u02.Lab02.{Square, perimeter}
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class ShapeTest {

  @Test def squarePerimeterTest(): Unit = {
    assertEquals(8, perimeter(Square(2)))
  }
}
