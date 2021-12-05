package it.pps.course.u13

object FoldingExample extends App {
  sealed trait Sequence[+A]

  object Sequence {
    case class Cons[+A](first: A, others: Sequence[A]) extends Sequence[A]

    case object Empty extends Sequence[Nothing]

    def apply[A](elems: A*): Sequence[A] = {
      var s:Sequence[A] = Empty
      elems.reverse.foreach(a => s = Cons(a,s))
      s
    }

    // Do the two below have anything in common to be factored out?
    def sum(s: Sequence[Int]): Int = s match {
      case Cons(h,t) => h + sum(t)
      case _ => 0
    }

    def concat[A](s: Sequence[A], s2: Sequence[A]): Sequence[A] = s match {
      case Cons(h,t) => Cons(h,concat(t,s2))
      case _ => s2
    }

    // A pure FP new functionality
    // Takes a sequence , a final element , and a composition function
    def foldRight[A,B](s: Sequence[A])(zero: B)(f: (A,B)=>B): B = s match {
      case Cons(a,as) => f(a, foldRight(as)(zero)(f))
      case _ => zero
    }

    // Also providing its OOP version, by modular "pimping"
    implicit class RichSequence[A](s: Sequence[A]) {
      def foldRight[B](zero: B)(f:(A,B) => B): B = Sequence.foldRight(s)(zero)(f)
    }
  }

  import Sequence._
  println(sum(Sequence(10,20,30)))
  println(concat(Sequence(10,20,30),Sequence(40,50)))

  val seq = Sequence (10 ,20 ,30 ,20)
  println( foldRight(seq)(0)(_+_) )
  println( seq.foldRight(0)(_+_) )
  println( seq.foldRight(1)(_*_) )
  println( seq.foldRight(Int.MaxValue)(_ min _) )
  println( seq.foldRight(Sequence(40,50,60))(Cons(_,_)) )
  println( seq.foldRight[Sequence[Int]](Empty)((a,b)=> if(a==20) b else Cons(a,b)))
  println( seq.foldRight[Sequence[String]](Empty)((a,b)=>Cons("a"+a,b)))
}
