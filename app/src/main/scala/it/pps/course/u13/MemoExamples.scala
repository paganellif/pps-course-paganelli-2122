package it.pps.course.u13

import scala.collection.mutable

object MemoHelper {
  def memorize[I,O](f: I=>O): I => O = new mutable.HashMap[I,O](){
    override def apply(key: I): O = getOrElseUpdate(key,f(key))
  }
}

object MemoExamples extends App {
  import MemoHelper._

  // factorial could not be a def
  val factorial: Int => Int = memorize(x => {
    println("Calling factorial with input "+x)
    if(x == 0) 1 else x*factorial(x-1)
  })
  println("Result with 5: "+factorial(5))
  println("Result with 7: "+factorial(7))
  println("Result with 3: "+factorial(3))

  object singletonList {
    private val memorized: Any => List[Any] = memorize(x => {
      println("Calling singleton with input "+x)
      List(x)
    })
    def apply[A](a:A): List[A] = memorized(a).asInstanceOf[List[A]]
  }

  println(singletonList(1))
  println(singletonList(2))
  println(singletonList(1)) // won't generate new lists
}
