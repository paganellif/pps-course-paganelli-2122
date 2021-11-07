package it.pps.course.u04

/**
  * Created by mirko on 12/18/16.
  */
object ApplyObjects extends App {

  // even an object can become a sort of "function", with an apply method
  object Square {
    def apply(x:Int) = x*x
  }
  println(Square.apply(5), Square(5)) // same result!

  // an application: defining constants as 0-ary functions
  object Zero {
    def apply(): Int = 0
  }
  val v = Zero() // becomes Zero.apply(), i.e., 0
  val w = Zero // the singleton object Zero
  println(v,w) // (0,..Zero$@54a097cc)

  // another, important application: factories hiding implementation
  trait Person {
    def name: String
    def surname: String
  }

  object Person { // the companion object of trait/class Person
    def apply(name: String, surname: String): Person =
      new PersonImpl(name,surname)

    private class PersonImpl(override val name: String,
                             override val surname:String) extends Person {
      assert(name != null && surname != null)
    }
  }

  val p: Person = Person("mario", "rossi")  // Hiding PersonImpl
  println(p, p.name, p.surname)

}
