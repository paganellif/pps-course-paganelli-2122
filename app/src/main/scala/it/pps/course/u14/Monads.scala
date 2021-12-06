package it.pps.course.u14

object Monads extends App {
  // A function returning an optional value
  def getRandom(): Option[Double] = if (Math.random <0.9) Some(Math.random) else None

  // Summing three random: an imperative solution
  def sum1 = {
    val (n1,n2,n3) = (getRandom(),getRandom(),getRandom())
    if (n1.isEmpty || n2.isEmpty || n3.isEmpty) None else Some(n1.get+n2.get+n3.get)
  }

  // Using map and flatMap
  def sum2 = getRandom().flatMap(x => getRandom().flatMap(y => getRandom().map(z => x+y+z)))

  // A completely equivalent version using for comprehensios
  def sum3 = for (x <- getRandom(); y <- getRandom(); z <- getRandom()) yield x+y+z

  for (i <- 0 to 19) println(sum1,sum2,sum3) // three equivalent solutions
  println(List(10,20.30).flatMap(i => List(i,i))) // 10,10,20,20,30,30

  // Using for as a sort of space explorer
  println(for (x <- List(10,20,30); y <- List("A","B","C")) yield (x+y))
}
