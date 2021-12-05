package it.pps.course.u13

object Functions extends App {
  // OO-style
  val f: Function2[Int,Int,Int] = new Function2[Int,Int,Int] {
     override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  println(f.apply(3,4))

  // FP-style
  val g: (Int, Int) => Int = (v1: Int, v2: Int) => v1 + v2
  println(g.apply(3,4))

  // functions and libraries
  println(g.curried(3)(4))
  println(g.tupled((3,4)))
  println((((x:Int)=>x+1).andThen(_*2))(5)) //12
  println(Function.chain[Int](Seq(_+1,_*2,_+1))(5)) //13

  // collections as functions
  val h: (Int)=>Boolean = Set(10,20,30)
  println( h(5), h(10) ) // false, true

  val l: (Int)=>String = Seq("a", "b", "c")
  println( l(0), l(1), l(2)) // "a", "b", "c",..

  val m: Map[String,Int] = Map("a"->1, "b"->2)
  println( m("a"), m("b")) // 1,2

  // factories as functions
  case class Point(x: Double, y:Double)

  object XAxisPointFactory extends (Double => Point) {
    override def apply(v1: Double): Point = Point(v1, 0)
  }

  val maker: Double => Point = XAxisPointFactory
  println(maker(5), XAxisPointFactory(5))

  // partial functions
  val pf: PartialFunction[Int,String] = {case n if(n>0) => "positive"}
  println(pf(5)) // pf(-2) gives matchError
  println(Seq(10,20,30,40).collect({case n if(n>20) => "a"+n})) //a30,a40
  println(Seq((1,1),(2,2),(3,3)).collect({case (n1,n2) if(n1>1) => (n2,n1)}))

  // method reference
  def meth(m: Int, n: Int): Int = m+n

  val w: (Int,Int) => Int = meth(_,_)
  val v: (Int,Int) => Int = meth _
  val v2 = meth _
}
