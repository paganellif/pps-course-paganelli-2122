package it.pps.course.testing

import org.junit.runner.RunWith
import org.scalacheck.Prop.{exists, forAll}
import org.scalatest.prop.PropertyChecks
import org.scalacheck.{Arbitrary, Gen, Prop, Properties}
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.Checkers
import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.junit.JUnitRunner

import scala.collection.mutable.{BitSet, HashSet, TreeSet}

object ScalaCheckExample extends Properties("String") {

  property("startsWith") = forAll { (a: String, b: String) =>
    (a+b).startsWith(a)
  }

  property("concatenate") = forAll { (a: String, b: String) =>
    (a+b).length >= a.length && (a+b).length >= b.length
  }

  property("substring") = forAll { (a: String, b: String, c: String) =>
    (a+b+c).substring(a.length, a.length+b.length) == b
  }
}

object TestOnNumbers extends Properties("Numbers") {
  property("Sum is associative") = forAll { (a: Int, b: Int, c: Int) =>
    (a + b) + c == a + (b + c)
  }
  property("Sum is commutative") = forAll { (a: Int, b: Int) =>
    a + b == b + a
  }
  property("Sum has zero as identity") = forAll { (a: Int) =>
    a + 0 == a && 0 + a == a
  }
  property("Diff is not associative") = forAll { (a: Int, b: Int) =>
    exists { c: Int => (a - b) - c != a - (b - c) }
  }
}

object TestOnLists extends Properties("Seqs") {
  def genericSizeProp[A:Arbitrary]: Prop = forAll{ (l1: Seq[A], l2: Seq[A]) =>
    (l1++l2).size == l1.size + l2.size
  }
  property("Size of concatenation") = Prop.all(
    genericSizeProp[Int], genericSizeProp[String], genericSizeProp[(Boolean,Double)])

  property("Reverse") = forAll { (l1: Seq[Int]) =>
    l1.reverse.reverse == l1
  }
}

object TestPersons extends Properties("Persons") {
  case class Person(name: String, age: Int) {
    def adult: Boolean = age >= 18
  }

  val adultPersonGen: Gen[Person] = for(name <- Gen.const("Bob");
                                        age <- Gen.choose(18,100))
  yield Person(name, age)

  property("Adultness") = forAll(adultPersonGen) { (p: Person) =>
    p.adult
  }

  implicit val arbitraryPerson: Arbitrary[Person] = Arbitrary(
    for(name <- Gen.oneOf("Bob","Rick");
        age <- Gen.chooseNum(0,100).suchThat(x => x >= 18))
    yield Person(name, age)
  )

  // Using an Arbitrary[Person] (a kind of default generator for a type)
  property("Adultness 2") = forAll { (p: Person) =>
    Prop.collect(p){ p.adult }
  }

  //property("Elder") = exists { (p: Person) => p.age > 200 }
}

@RunWith(classOf[JUnitRunner])
class SetSpec extends AnyPropSpec with PropertyChecks with Checkers with Matchers {
  val examples = Table("set",BitSet.empty, HashSet.empty[Int], TreeSet.empty[Int])
  val examplesGen = Gen.oneOf(BitSet.empty, HashSet.empty[Int], TreeSet.empty[Int])

  property("an empty Set should have size 0") {
    // ScalaTest's property-based testing style
    forAll(examples) { set => set.size should be (0) }
    // ScalaCheck's property-based testing style
    check { Prop.forAll(examplesGen) { set => set.isEmpty } }
  }

  property("invoking head on an empty set should produce NoSuchElementException") {
    // ScalaTest's property-based testing style
    forAll(examples) { set => a [NoSuchElementException] should be thrownBy{set.head} }
    // ScalaCheck's property-based testing style
    check { Prop.forAll(examplesGen) { set =>
      Prop.throws(classOf[NoSuchElementException])(set.head) }
    }
  }

  property("A set must have size >= 0") {
    check { Prop.forAll { (l: Set[Int]) => l.size >= 0 } }
  }
}