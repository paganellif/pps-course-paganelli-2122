package it.pps.course.u13

trait Sortable[A] {
  // a strategy for sorting elements of type A 2
  def compare(a1: A, a2: A): Boolean
}

object Sortable {
  // A module with our algorithms using Sortable, in 3 styles
  // explicit OO style
  def compare[A](a1: A, a2: A)(sortable: Sortable[A]): Boolean = sortable.compare(a1, a2)

  // variant with implicits
  def min[A](a1: A, a2: A)(implicit s: Sortable[A]): A = if (s.compare(a1, a2)) a2 else a1

  // variant with context bound
  def sortPair[A: Sortable](pair: (A,A)): (A,A) =
    if (implicitly[Sortable[A]].compare(pair._1,pair._2)) pair else (pair._2,pair._1)
}

object SortableImplicits {
  implicit object sortableInt extends Sortable[Int] {
    override def compare(a1: Int, a2: Int): Boolean = a1<a2
  }

  implicit object sortableString extends Sortable[String] {
    override def compare(a1: String, a2: String): Boolean = a1.compare(a2) > 0
  }
}

object SortableExamples extends App { import Sortable._
  import SortableImplicits._

  println(compare(150,210)(sortableInt))
  println(min("150","210"))
  println(sortPair( ("150","200")))
}