package it.pps.course.u13

import scala.annotation.tailrec

// the type-class: a A-generic trait defining operations over A
trait Plus[A] {
  // what operations apply Plus a viable "context" for A?
  def plus(a1: A, a2: A): A
}

object Plus {
  // A module with our algorithms using Plus type-class
  // recall: implicitly[T] finds an implicit value of type T

  def plus[A: Plus](a1: A, a2: A) = implicitly[Plus[A]].plus(a1, a2)

  @tailrec
  def plusAll[A: Plus](list: List[A]): Option[A] = list match {
    case Nil => None
    case h :: Nil => Some(h)
    case h1 :: h2 :: t => plusAll(plus(h1, h2) :: t)
  }

  // pimping operator |+| as an alias for plus
  implicit class RichPlus[A: Plus](a: A) {
    def |+|(a2: A): A = Plus.plus(a, a2)
  }
}

object PlusImplicits {
  import Plus.RichPlus

  implicit object plusInt extends Plus[Int] {
    override def plus(a1: Int, a2: Int) = a1 + a2
  }

  implicit object plusString extends Plus[String] {
    override def plus(a1: String, a2: String) = a1 concat a2
  }

  implicit def plusList[A:Plus] = new Plus[List[A]] {
    override def plus(a1: List[A], a2: List[A]): List[A] = a1 ++ a2
  }

  implicit def plusTuple2[A:Plus,B:Plus] = new Plus[(A,B)] {
    override def plus(a1: (A,B), a2: (A,B)): (A,B) = (a1._1 |+| a2._1, a1._2 |+| a2._2)
  }
}

object PlusExamples extends App {
  import Plus._, PlusImplicits._

  println(plus(150,210)) // 360
  println(plus("a","b")) // "ab"
  println(plus(List(1,2,3),List(4,5,6))) // List(1, 2, 3, 4, 5, 6)
  println(plus((1,"2"),(10,"20"))) // (11,"220")
  println((1,"2") |+| (10,"20")) // also as operator
  println(plusAll(List((1,10),(2,10),(3,10)))) // Some((6,30))
}
