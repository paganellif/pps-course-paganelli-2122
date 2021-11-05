package it.pps.course.u02

import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test

import it.pps.course.u02.Modules._
import it.pps.course.u02.Optionals._
import it.pps.course.u03.Lists.List._
import it.pps.course.u03.Streams._

class Lab02Test {

  @Test def testDrop(): Unit ={
    val lst = Cons(10, Cons(20, Cons(30, Nil())))
    assertEquals(drop(lst, 1), Cons(20, Cons(30, Nil())))
    assertEquals(drop(lst, 2), Cons(30, Nil()))
    assertEquals(drop(lst, 5), Nil())
  }

  @Test def testFlatMap(): Unit = {
    val lst = Cons(10, Cons(20, Cons(30, Nil())))
    assertEquals(flatMap(lst)(v => Cons(v+1, Nil())), Cons(11, Cons(21, Cons(31, Nil()))))
  }

  @Test def testMap(): Unit = {
    val lst = Cons(10, Cons(20, Cons(30, Nil())))
    assertEquals(mapWithFlatMap(lst)(v => v+1), Cons(11, Cons(21, Cons(31, Nil()))))
  }
  @Test def testMax(): Unit = {
    val lst = Cons(50, Cons(20, Cons(30, Nil())))
    assertEquals(Option.Some(50), max(lst))
    assertEquals(Option.None(), max(Nil()))
  }

  @Test def testFoldLeft(): Unit ={
    val lst2 = Cons(3, Cons(7, Cons(1, Cons(5, Nil()))))
    assertEquals(-16, foldLeft(lst2)(0)(_-_))
  }

  @Test def testFoldRight(): Unit ={
    val lst2 = Cons(3, Cons(7, Cons(1, Cons(5, Nil()))))
    assertEquals(-8, foldRight(lst2)(0)(_-_))
  }

  @Test def testTeacherCourses(): Unit = {
    val p1: Person = Person.Student("mario",2015)
    val p2: Person = Person.Student("mario",2015)
    val p3: Person = Person.Student("mario",2015)
    val p4: Person = Person.Teacher("mario","OOP")
    val p5: Person = Person.Teacher("mario","SD")
    val p6: Person = Person.Teacher("mario","ASW")

    val personList = Cons(p1, Cons(p2, Cons(p3, Cons(p4, Cons(p5, Cons(p6, Nil()))))))

    assertEquals(Cons("OOP", Cons("SD", Cons("ASW", Nil()))), teachersCoursesList(personList))
  }

  @Test def testDropStream(): Unit = {
    val s = Stream.take(Stream.iterate(0)(_+1))(10)
    val expected = Cons(6, Cons(7, Cons(8, Cons(9, Nil()))))
    assertEquals(expected, Stream.toList(Stream.drop(s)(6)))
  }

  @Test def testConstant(): Unit = {
    val expected = Cons("x", Cons("x", Cons("x", Cons("x", Cons("x", Nil())))))
    assertEquals(expected, Stream.toList(Stream.take(Stream.constant("x"))(5)))
  }

  @Test def TestFibonacci(): Unit = {
    val expected = Cons(0, Cons(1, Cons(1, Cons(2, Cons(3, Cons(5, Cons(8, Cons(13, Nil()))))))))
    assertEquals(expected, Stream.toList(Stream.take(Stream.fibs)(8)))
  }

}
