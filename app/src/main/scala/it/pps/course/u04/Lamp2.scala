package it.pps.course.u04

// A compact, still readable version of Lamp2
class Lamp2(
  private var state: Boolean  // indentation emphasises the constructor section
) {

  def isOn: Boolean = state // getters with no side-effect typically come with no ()

  def on() { state = true }  // when return is Unit, might skip its indication

  def off() { state = false } // typically use {..} when returning Unit

  // typically should not report return type when overriding..
  override def toString = "Lamp " + state + " identity " + super.toString
}

object UseLamp2 extends App {

  val v = new Lamp2(false)
  println(v)
  println(v.isOn)
  v.on()
  println(v.isOn)

  // the degeneration.. writing one-line classes
  class Point(val x: Int, val y:Int) // basically, a case class
  val p = new Point(10,20)
  println(p.x, p.y)
}
