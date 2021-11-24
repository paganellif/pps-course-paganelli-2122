package it.pps.course.prolog

import alice.tuprolog.{NoSolutionException, Prolog, Theory}
import it.pps.course.prolog.Lab01
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PrologTest extends AnyFunSuite with Matchers {
  val engine: Prolog = Lab01()

  test("Ex1.1"){
    assert(engine.solve("search(a,[a,b,c]).").getSolution.toString == "search(a,[a,b,c])")
    assertThrows[NoSolutionException]{
      engine.solve("search(a,[c,d,e]).").getSolution
    }
  }

  test("Ex1.2"){
    assert(engine.solve("search2(a,[b,c,a,a,d,e,a,a,g,h]).").getSolution.toString == "search2(a,[b,c,a,a,d,e,a,a,g,h])")
    assert(engine.solve("search2(a,[b,c,a,a,a,d,e]).").getSolution.toString == "search2(a,[b,c,a,a,a,d,e])")
    assert(engine.solve("search2(X,[b,c,a,d,d,e]).").getSolution.toString == "search2(d,[b,c,a,d,d,e])")
    assertThrows[NoSolutionException]{
      engine.solve("search2(b,[b,c,a,a,a,d,e]).").getSolution
    }
  }

  test("Ex1.3"){
    assert(engine.solve("search_two(a,[b,c,a,d,a,d,e]).").getSolution.toString == "search_two(a,[b,c,a,d,a,d,e])")
    assertThrows[NoSolutionException]{
      engine.solve("search_two(a,[b,c,a,a,d,e]).").getSolution
    }
  }

  test("Ex1.4"){
    assert(engine.solve("search_anytwo(a,[b,c,a,a,d,e]).").getSolution.toString == "search_anytwo(a,[b,c,a,a,d,e])")
    assert(engine.solve("search_anytwo(a,[b,c,a,d,e,a,d,e]).").getSolution.toString == "search_anytwo(a,[b,c,a,d,e,a,d,e])")
    assertThrows[NoSolutionException]{
      engine.solve("search_anytwo(z,[b,c,a,d,e,a,d,e]).").getSolution
    }
  }

}
