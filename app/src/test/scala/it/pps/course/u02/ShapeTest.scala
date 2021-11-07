package it.pps.course.u02

import it.pps.course.u02.Lab02.{Circle, Rectangle, Shape, Square}
import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

class ShapeTest {

  private val square: Shape = Square(2)
  private val rectangle: Shape = Rectangle(5,10)
  private val circle: Shape = Circle(2)

  @Test def squarePerimeterTest(): Unit = {
    assertEquals(8, this.square.perimeter)
  }

  @Test def squareAreaTest(): Unit = {
    assertEquals(4, this.square.area)
  }

  @Test def rectanglePerimeterTest(): Unit = {
    assertEquals(30, this.rectangle.perimeter)
  }

  @Test def rectangleAreaTest(): Unit = {
    assertEquals(50, this.rectangle.area)
  }

  @Test def circlePerimeterTest(): Unit = {
    assertEquals(13, this.circle.perimeter.round)
  }

  @Test def circleAreaTest(): Unit = {
    assertEquals(13, this.circle.area.round)
  }


}
