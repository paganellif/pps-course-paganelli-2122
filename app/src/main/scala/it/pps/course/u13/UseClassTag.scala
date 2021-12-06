package it.pps.course.u13

object UseClassTag extends App {
  // without reification
  def f[T](x: Any): Boolean =  x match {
    case v:T => true;
    case _ => false
  }

  println( f[Int](1), f[Int]("1")) // true, true

  import scala.reflect._
  // with reification
  def g[T:ClassTag](x: Any): Boolean = x match {
    case v:T => true;
    case _ => false
  }

  println( g[Int](1), g[Int]("1")) // true, false

  // this would need passing a java.lang.Class in Java
  def createLegacyArray[T:ClassTag](n: Int): Array[T] = implicitly[ClassTag[T]].newArray(n)
  println( createLegacyArray[Int](5) ) // [I@5315b42e
}
