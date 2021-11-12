package it.pps.course.u05lab

import scala.collection.immutable.List

object Ex4 extends App {
  def sequence[A](list: List[Option[A]]): Option[List[A]] = {
    if(list.contains(Option.empty)) Option.empty
    else
      Option(List.from(list.collect({case x if x.nonEmpty => x.get})))
  }

  println(sequence[Int](List(Some(1),Some(2),Some(3)))) // Some(List(1, 2, 3))
  println(sequence(List(Some(1),None,Some(3)))) // None

}
