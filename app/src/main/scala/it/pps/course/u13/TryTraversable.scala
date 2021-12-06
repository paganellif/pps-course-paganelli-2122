package it.pps.course.u13

import it.pps.course.u13.FoldingExample.Sequence
import it.pps.course.u13.TraversableImplicits.traversableMaybe.foreach

import scala.annotation.tailrec

sealed trait Maybe[+A]

object Maybe {
  case class Just[+A](a: A) extends Maybe[A]

  case object Empty extends Maybe[Nothing]

  def apply[A](a: A): Maybe[A] = Just(a)

  def apply[A](): Maybe[A] = Empty
}

trait Traversable[F[_]] {
  // given a traversable object t, apply f on each element of it
  def foreach[A,U](t: F[A], f: (A) => U): Unit
}

object Traversable {
  implicit class RichTraversable[F[_]:Traversable ,A](ta: F[A]) {
    // pimping foreach and foldRight

    def foreach[U](f: (A)=> U) = implicitly[Traversable[F]].foreach(ta,f)

    def foldRight[U](z: U)(op: (A,U)=>U ): U = {
      var reversed: List[A] = Nil
      foreach(a => reversed = a :: reversed) // create a reversed list
      var out = z
      reversed.foreach(a => out = op(a,out))
      out
    }
  }
}

object TraversableImplicits {
  // Basically declaring how Sequence can be considered a Traversable
  implicit object traversableSequence extends Traversable[Sequence] {
    @tailrec
    override def foreach[A, U](t: Sequence[A], f: (A) => U): Unit = t match {
      case Sequence.Cons(h, t) => {
        f(h);
        foreach(t, f)
      }
      case Sequence.Empty => {}
    }
  }

  // Basically declaring how Maybe can be considered a Traversable
  implicit object traversableMaybe extends Traversable[Maybe] {
    override def foreach[A, U](t: Maybe[A], f: (A) => U): Unit = t match {
      case Maybe.Just(a) => f(a)
      case Maybe.Empty => {}
    }
  }
}

object TryTraversable extends App {
  import Traversable._, TraversableImplicits._

  val s = Sequence (10 ,20 ,30)
  var x=0
  s.foreach(print) // 102030
  println ()
  s.foreach(a => x+=a)
  println(x) // 60
  println(s.foldRight(0)(_+_)) // 60
  println(s.foldRight(1)(_*_)) // 6000
  println(s.foldRight(" ")(_+_)) // "102030"
  println(s.foldRight(Sequence(40,50,60))(Sequence.Cons(_,_)))

  println(Maybe(10).foldRight(5)(_+_)) // 15
  println(Maybe[Int]().foldRight(5)(_+_)) // 5
}