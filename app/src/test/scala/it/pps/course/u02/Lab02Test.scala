package it.pps.course.u02

import it.pps.course.u02.Lab02._
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

  @Test def fibTest(): Unit = {
    assertEquals(fib(0), 0)
    assertEquals(fib(1), 1)
    assertEquals(fib(5), 5)
  }

  @Test def tailFibTest(): Unit = {
    assertEquals(tailFib(0), 0)
    assertEquals(tailFib(1), 1)
    assertEquals(tailFib(5), 5)
  }

  @Test def parityAnonymousFuncTest(): Unit = {
    assertEquals(parityLiteralFunc(2), "even")
    assertEquals(parityLiteralFunc(3), "odd")
  }

  @Test def parityMethodSyntaxTest(): Unit = {
    assertEquals(parityMethodSyntax(2), "even")
    assertEquals(parityMethodSyntax(3), "odd")
  }

  @Test def emptyPredicateTest(): Unit = {
    assertFalse(empty("empty"))
    assertTrue(empty(""))
  }

  @Test def notEmptyPredicateTest(): Unit = {
    assertFalse(notEmpty(""))
    assertTrue(notEmpty("notEmpty"))
  }

  @Test def genericNegTest(): Unit = {
    val intPred: (Int => Boolean) = (a: Int) => if (a % 2 == 0) true else false
    val negIntPred = genericNeg(intPred)
    val stringPred: (String => Boolean) = (a: String) => a.contains("HELLO")
    val negStringPred = genericNeg(stringPred)

    assertFalse(intPred(3))
    assertTrue(negIntPred(3))

    assertTrue(stringPred("HELLO LELLO"))
    assertFalse(negStringPred("HELLO LELLO"))
  }

  @Test def curryingTest(): Unit = {
    assertTrue(p1(1)(1)(1))
    assertFalse(p2(3,2,1))
    assertTrue(p3(5)(10)(15))
    assertFalse(p4(15,10,5))
  }

  @Test def compositionTest(): Unit = {
    assertEquals(compose[Int](_-1,_*2)(5), 9)
    assertEquals(compose[String](_.concat(" LELLO"),_.concat("ELLO"))("H"), "HELLO LELLO")
  }

}
