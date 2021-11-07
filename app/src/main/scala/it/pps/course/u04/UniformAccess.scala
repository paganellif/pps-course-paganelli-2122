package it.pps.course.u04

object UniformAccess extends App {

  class A {
    var field = 10
    def meth(s: String):String = this+s
    def ***(s: String):String = this+s   // just a method called ***
    def prop: Int = field                // a getter, note lack of "()"
    def prop_=(i: Int): Unit = field=i   // corresponding setter
    def +:(i: Int):A = { field = field + i; this } // right-associativity
  }

  val obj = new A
  val str = obj.meth("ciao") // four equivalent versions..
  val str2 = obj meth "ciao"
  val str3 = obj *** "ciao"
  val str4 = obj.***("ciao")
  println(str3)
  println(obj.prop); // 10
  obj.prop = 1;
  println(obj.prop) // 1
  10 +: 20 +: 30 +: obj //obj.+:(30).+:(20).+:(10)
  println(obj.prop) // 61

}
