package it.pps.course.prolog

import alice.tuprolog.{Prolog, SolveInfo, Theory}

object Scala2PSimpleExample extends App {
  val engine: Prolog = new Prolog
  engine.setTheory(new Theory("father(abraham,isaac)."))

  val info: SolveInfo = engine.solve("father(abraham,X).")
  println(info.getSolution)
  // "append([1],[2,3],[1,2,3])"

}
