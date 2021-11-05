package it.pps.course.u03

import it.pps.course.u02.Modules.Person
import it.pps.course.u02.Modules.Person.Teacher

object Lists {

  // A generic linkedlist
  sealed trait List[E]

  // a companion object (i.e., module) for List
  object List {
    case class Cons[E](head: E, tail: List[E]) extends List[E]
    case class Nil[E]() extends List[E]

    def sum(l: List[Int]): Int = l match {
      case Cons(h, t) => h + sum(t)
      case _ => 0
    }

    def append[A](l1: List[A], l2: List[A]): List[A] = (l1, l2) match {
      case (Cons(h, t), l2) => Cons(h, append(t, l2))
      case _ => l2
    }

    def drop[A](l: List[A], n: Int): List[A] = l match {
      case Cons(_, t) if n > 0 => drop(t, n - 1)
      case _ => l
    }

    def flatMap[A,B](l: List[A])(f: A => List[B]): List[B] = l match {
      case Cons(h, t) => append(f(h), flatMap(t)(f))
      case Nil() => Nil()
    }

    def filter[A](l1: List[A])(pred: A=>Boolean): List[A] = l1 match {
      case Cons(h,t) if (pred(h)) => Cons(h, filter(t)(pred))
      case Cons(_,t) => filter(t)(pred)
      case Nil() => Nil()
    }

    //def filterWithFlatMap[A](l1: List[A])(pred: A=>Boolean): List[A] = l1 match {
    //  case Cons(h, t) => flatMap(l1)()
    //}

    def map[A,B](l: List[A])(mapper: A=>B): List[B] = l match {
      case Cons(h, t) => Cons(mapper(h), map(t)(mapper))
      case Nil() => Nil()
    }

    def mapWithFlatMap[A,B](l: List[A])(mapper: A=>B): List[B] = l match {
      case Cons(h, t) => flatMap(l)(v => Cons(mapper(v), Nil()))
      case Nil() => Nil()
    }

    import it.pps.course.u02.Optionals._
    def max(l: List[Int]): Option[Int] = {
      def _max(l: List[Int], m: Int): Int = l match {
        case Cons(h, t) if h >= m => _max(t, h)
        case Cons(h, t) if h < m => _max(t, m)
        case Nil() => m
      }

      def perform(l: List[Int]): Option[Int] = l match {
        case Cons(h, t) => Option.Some(_max(l, h))
        case Nil() => Option.None()
      }
      perform(l)
    }

    def teachersCoursesList(l: List[Person]): List[String] = l match {
      case Cons(h, t) => map(filter(l)(h => h.isInstanceOf[Teacher]))(v => v.asInstanceOf[Teacher].course)
      case Nil() => Nil()
    }

    def foldLeft[A,B](l: List[A])(acc: B)(f: (B, A) => B): B = l match {
      case Cons(h, t) => f(foldLeft(t)(acc)(f), h)
      case Nil() => acc
    }

    def foldRight[A,B](l: List[A])(acc: B)(f: (A, B) => B): B = l match {
      case Cons(h, t) => f(h, foldRight(t)(acc)(f))
      case Nil() => acc
    }
  }
}

object ListsMain extends App {
  import Lists._
  val l = List.Cons(10, List.Cons(20, List.Cons(30, List.Nil())))
  println(List.sum(l)) // 60
  import List._
  println(append(Cons(5, Nil()), l)) // 5,10,20,30
  println(filter[Int](l)(_ >=20)) // 20,30

}