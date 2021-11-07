package it.pps.course.u04

object Singleton extends App {

  class C {
    def m():Int = 1
  }

  val c = new C

  println(c.m())

  object O {
    private var n:Int = 0
    def get():Int = n
    def inc(){
      n = n+1
    }
  }

  println(O.get())
  O.inc()
  O.inc()
  println(O.get())

}
