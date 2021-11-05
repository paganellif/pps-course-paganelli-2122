package it.pps.course.u02

object Currying extends App {

  // standard function with no currying
  def mult(x: Double, y: Double): Double = x*y

  // function with currying
  // curriedMult has actually type: Double => (Double => Double)
  def curriedMult(x: Double)(y: Double): Double = x*y

  // slightly different syntax at the call side..
  println(mult(10,2)) // 20
  println(curriedMult(10)(2)) // 20

  // curriedMult can be partially applied!
  val twice: Double => Double = curriedMult(2)
  val triple: Double => Double = curriedMult(3)
  val half: Double => Double = curriedMult(0.5)

  println(twice(10)) // 20
  println(triple(10)) // 30
  println(half(10)) // 5


  // => is right-associative, hence it is equal to:
  //val curr...: Double => (Double => Double) = x => (y => x*y)
  val curriedMultAsFunction: Double => Double => Double = x => y => x*y

  println(curriedMultAsFunction(10)(2)) // 20
  println(curriedMultAsFunction) // u02.Currying$$$Lambda$7/...
  println(curriedMultAsFunction(10)) // u02.Currying$$$Lambda$12/...

}
