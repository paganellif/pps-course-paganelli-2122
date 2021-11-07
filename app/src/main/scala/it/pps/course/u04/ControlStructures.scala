package it.pps.course.u04

object ControlStructures extends App {

  val b = true
  println(if (b) "OK" else "NO") // if is actually an expression
  println(b match {case true => "OK" case _ => "NO"}) // equivalent version
  val s = if (b) {  // recall that bodies are a special case of expr
    println("OK"); "OK"
  } else "NO"

  val any: AnyVal = if (b) 1 else true // least upperbound type!
  val i: Int = if (b) 1 else throw new Error() // Int vs Nothing

  def gcd(x: Int, y: Int): Int = { // imperative style gcd
    var a = x
    var b = y
    while (a != b) {
      if (a > b) a = a - b else b = b - a
    }
    a
  }
  println(gcd(10, 18))

}
