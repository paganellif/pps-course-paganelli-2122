package it.pps.course.u04

object Equality extends App {

  class Pair[A,B](val x: A, val y: B)

  class PairWithEq[A,B](val x: A, val y: B){
    // IntelliJ generation of code..
    def canEqual(other: Any): Boolean = other.isInstanceOf[PairWithEq[_,_]]

    override def equals(other: Any): Boolean = other match {
      case that: PairWithEq[A,B] =>
        (that canEqual this) && x == that.x && y == that.y
      case _ => false
    }

    override def hashCode(): Int = {
      val state = Seq(x, y)
      state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
    }
  }

  case class CasePair[A,B](x: A, y: B)

  println( new Pair(10,20) == new Pair(10,20))  // false
  println( new PairWithEq(10,20) == new PairWithEq(10,20)) // true
  println( CasePair(10,20) == CasePair(10,20)) // true.. don't need 'new'

  val p = new Pair(10,20)
  println( p eq p) // true.. is reference equality!


}
