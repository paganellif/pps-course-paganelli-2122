package it.pps.course.u13

case class MyIntOp(value: Int) {
  def ***(n: Int): Int = n match {
    case 0 => 1
    case n => ***(n-1)*value
  }
}

object MyIntOp {
  implicit def toMyIntOp(n: Int): MyIntOp = MyIntOp(n)
}

object MyListHelpers {
  implicit class MyList(list: List[Double]) {
    def |+|(l: List[Double]): List[Double] = (list,l) match {
      case (i1 :: l1, i2 :: l2) => (i1 + i2) :: (l1 |+| l2)
      case _ => Nil
    }
    def switchFirstAndSecond: List[Double] = list match {
      case i1 :: i2 :: l => i2 :: i1 :: l
      case _ => list
    }
  }
}

object PimpMyLibrary extends App {
  import MyIntOp._, MyListHelpers._ // Int and List have been ’pimped’
  println(new MyIntOp(10).***(2))
  println (2 *** 3)
  println(List(10.0,20.0,30.0) |+| List(30.0,40.0,50.0))
  println(List(10.0,20.0,30.0).switchFirstAndSecond)
}
