package it.pps.course.u04

// a class with two constructors
class Lamp(on: Boolean) {

  private var state: Boolean = on
  private val firstUse: java.util.Date = new java.util.Date

  // floating code is executed as part of the primary constructor
  println("primary constr. of: "+this)

  // auxiliary constructor, necessarily calling the primary one
  def this() {
    this(false)
    println("auxiliary constr. of: "+this)
  }

  def isOn(): Boolean = state // curly brackets not needed with single-expression

  def on() { // use curly-brackets with "imperative" code
    state = true
  }

  def off() {
    state = false
  }

  override def toString(): String = // string interpolation below...
    s"Lamp {state} first use {firstUse}; identity {super.toString}"

}

// a quick way to define an app's starting point
object UseLamp extends App {

  // code here will be executed as if it were in the main
  println("starting..")
  val v = new Lamp(false)
  println(v)
  println(v.isOn())
  v.on()
  println(v.isOn())

  val v2 = new Lamp(true)
  println(v2.isOn())
  val v3 = new Lamp  // omitting ()
  println(v3.isOn())

  val v4 = new JLamp  // Java interoperability
  println(v4.isOn())
}
