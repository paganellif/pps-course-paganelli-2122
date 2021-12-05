package it.pps.course.u13

object ListOps extends App {
  val list = List (10 ,20 ,30 ,40)
  // def fold[A1 >: A](z: A1)(op: (A1, A1) => A1): A1
  // def foldLeft[B](z: B)(op: (B, A) => B): B
  // def foldRight[B](z: B)(op: (A, B) => B): B

  println( list.fold(1)(_-_)) // -100:
  println( list.foldLeft(1)(_-_)) // -100: (((1-10)-20)-30)-40
  println( list.foldRight(1)(_-_)) // -20: (10-(20-(30-(40-1))))
  println( list.foldLeft(".")(_+"["+_+"]")) // .[10][20][30][40]
  println( list.foldRight(".")(_+"["+_+"]")) // 10[20[30[40[.]]]]

  //  def scan[B >: A, That](z: B)(op: (B, B) => B)
  //    (implicit cbf: CanBuildFrom[Traversable[A], B, That]): That
  //  def scanLeft[B, That](z: B)(op: (B, A) => B)
  //    (implicit bf: CanBuildFrom[Traversable[A], B, That]): That
  //  def scanRight[B, That](z: B)(op: (A, B) => B)
  //    (implicit bf: CanBuildFrom[Traversable[A], B, That]): That

  println( list.scan(1)(_+_)) // List(1, 11, 31, 61, 101)
  println( list.scanLeft(".")(_+"["+_+"]"))
  // List(., .[10], .[10][20], .[10][20][30], .[10][20][30][40])
  println( list.scanRight(".")(_+"["+_+"]"))
  // List(10[20[30[40[.]]]], 20[30[40[.]]], 30[40[.]], 40[.], .)

  // def reduce[A1 >: A](op: (A1, A1) => A1): A1
  //..
  println( list.sum ) // 100, essentially fold without initial value.
}
