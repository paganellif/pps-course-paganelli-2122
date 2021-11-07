package it.pps.course.u04

object Iterations extends App {

  def sum(args: Int*): Int = {
    var s = 0
    for (i <- args) { s = s + i}
    s
  }

  println(sum(1,2,3,4,5))

  def printall(args: Int*): Unit = {
    args foreach (print(_)) // infix notation
  }

  printall(1,2,3,4,5)
  println

  // Arrays are generic classes with proper apply method..
  val a: Array[Int] = Array(1,2,3,4,5,6) // a Java-compatible array
  a(2)=30
  println(a, a.apply(1), a(2), a.length) // ([I@1a407d53,2,30,6)

  // equivalent code thanks to Scala's apply, update and varargs
  val b: Array[Int] = Array.apply(1,2,3,4,5,6) // a Java-compatible array
  b.update(2,30)
  println(b, b.apply(1), b.apply(2), b.length) // ([I@3d8c7aca,2,30,6)


}
