package it.pps.course.u03

import it.pps.course.u03.Streams.Stream

object Streams {
  import Lists._
  sealed trait Stream[A]

  object Stream {

    private case class Empty[A]() extends Stream[A]

    private case class Cons[A](head: () => A, tail: () => Stream[A]) extends Stream[A]

    def empty[A](): Stream[A] = Empty()

    def cons[A](hd: => A, tl: => Stream[A]): Stream[A] = {
      lazy val head = hd
      lazy val tail = tl
      Cons(() => head, () => tail)
    }

    def toList[A](stream: Stream[A]): List[A] = stream match {
      case Cons(h, t) => List.Cons(h(), toList(t()))
      case _ => List.Nil()
    }

    def map[A, B](stream: Stream[A])(f: A => B): Stream[B] = stream match {
      case Cons(head, tail) => cons(f(head()), map(tail())(f))
      case _ => Empty()
    }

    def filter[A](stream: Stream[A])(pred: A => Boolean): Stream[A] = stream match {
      case Cons(head, tail) if (pred(head())) => cons(head(), filter(tail())(pred))
      case Cons(head, tail) => filter(tail())(pred)
      case _ => Empty()
    }

    def take[A](stream: Stream[A])(n: Int): Stream[A] = (stream, n) match {
      case (Cons(head, tail), n) if n > 0 => cons(head(), take(tail())(n - 1))
      case _ => Empty()
    }

    def iterate[A](init: => A)(next: A => A): Stream[A] = cons(init, iterate(next(init))(next))

    def drop[A](s: Stream[A])(n: Int): Stream[A] = (s, n) match {
      case (Cons(head, tail), n) if n > 0 => drop(tail())((n - 1))
      case _ => s
    }

    def constant[A](const: A): Stream[A] = Stream.cons(const, constant(const))

    val fibs: Stream[Int] = {
      def _fib(i: Int): Int = {
        if (i <= 0){
          0
        } else if (i == 1){
          1
        } else {
          _fib(i - 1) + _fib(i - 2)
        }
      }
      Stream.map(Stream.iterate(0)(_ + 1))(v => _fib(v))
  }

  }
}

object StreamsMain extends App {
  // var simplifies chaining of functions a bit..
  var str = Stream.iterate(0)(_+1)   // {0,1,2,3,..}
  str = Stream.map(str)(_+1)    // {1,2,3,4,..}
  str = Stream.filter(str)(x => (x < 3 || x > 20)) // {1,2,21,22,..}
  str = Stream.take(str)(10) // {1,2,21,22,..,28}
  println(Stream.toList(str)) // [1,2,21,22,..,28]

  val corec: Stream[Int] = Stream.cons(1, corec) // {1,1,1,..}
  println(Stream.toList(Stream.take(corec)(10))) // [1,1,..,1]
}
