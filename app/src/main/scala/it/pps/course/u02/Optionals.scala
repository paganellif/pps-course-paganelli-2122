package it.pps.course.u02

object Optionals extends App {

  sealed trait Option[A] // An Optional data type
  object Option {
    case class None[A]() extends Option[A]
    case class Some[A](a: A) extends Option[A]

    def isEmpty[A](opt: Option[A]): Boolean = opt match {
      case None() => true
      case _ => false
    }

    def getOrElse[A, B >: A](opt: Option[A], orElse: B): B = opt match {
      case Some(a) => a
      case _ => orElse
    }

    def flatMap[A,B](opt: Option[A])(f:A => Option[B]): Option[B] = opt match {
      case Some(a) => f(a)
      case _ => None()
    }

    def filter[A](opt: Option[A])(f: A => Boolean): Option[A] = opt match {
      case None() => None()
      case Some(a) => if (f(a)) Some(a) else None()
    }

    def map[A,B](opt: Option[A])(f: A => B): Option[B] = opt match {
      case Some(a) => Some(f(a))
      case _ => None()
    }

    def map2[A, B](opt1: Option[A], opt2: Option[A])(f: (A,A) => B): Option[B] = (opt1, opt2) match {
      case (Some(a1), Some(a2)) => Some(f(a1, a2))
      case _ => None()
    }

  }

  import Option._
  val s1: Option[Int] = Some(1)
  val s2: Option[Int] = Some(2)
  val s3: Option[Int] = None()

  println(s1) // Some(1)
  println((getOrElse(s1,0), getOrElse(s3,0))) // 1,0
  println(flatMap(s1)(i => Some(i+1))) // Some(2)
  println(flatMap(s1)(i => flatMap(s2)(j => Some(i+j)))) // Some(3)
  println(flatMap(s1)(i => flatMap(s3)(j => Some(i+j)))) // None
}
