package it.pps.course.prolog

import alice.tuprolog.{NoSolutionException, Prolog, Term, Theory}
import it.pps.course.prolog.Lab01
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PrologTest extends AnyFunSuite with Matchers {
  val engine: Prolog = Lab01()

  test("Ex1.1"){
    assert(engine.solve("search(a,[a,b,c]).").isSuccess)
    assertThrows[NoSolutionException]{
      engine.solve("search(a,[c,d,e]).").getSolution
    }
  }

  test("Ex1.2"){
    assert(engine.solve("search2(a,[b,c,a,a,d,e,a,a,g,h]).").isSuccess)
    assert(engine.solve("search2(a,[b,c,a,a,a,d,e]).").isSuccess)
    assert(engine.solve("search2(X,[b,c,a,d,d,e]).").isSuccess)
    assertThrows[NoSolutionException]{
      engine.solve("search2(b,[b,c,a,a,a,d,e]).").getSolution
    }
  }

  test("Ex1.3"){
    assert(engine.solve("search_two(a,[b,c,a,d,a,d,e]).").isSuccess)
    assertThrows[NoSolutionException]{
      engine.solve("search_two(a,[b,c,a,a,d,e]).").getSolution
    }
  }

  test("Ex1.4"){
    assert(engine.solve("search_anytwo(a,[b,c,a,a,d,e]).").isSuccess)
    assert(engine.solve("search_anytwo(a,[b,c,a,d,e,a,d,e]).").isSuccess)
    assertThrows[NoSolutionException]{
      engine.solve("search_anytwo(z,[b,c,a,d,e,a,d,e]).").getSolution
    }
  }

  test("Ex2.1"){
    assert(engine.solve("size([],0).").isSuccess)
    assert(engine.solve("size([a,b,c],3).").isSuccess)
    assert(engine.solve("size([a,b,c,d],X).").getSolution.toString == "size([a,b,c,d],4)")
    assertThrows[NoSolutionException]{
      engine.solve("size([1,2],0).").getSolution
    }
  }

  test("Ex2.2"){
    assert(engine.solve("size2([],zero).").isSuccess)
    assert(engine.solve("size2([a,b,c],s(s(s(zero)))).").isSuccess)
    assert(engine.solve("size2([a,b,c,d],X).").getSolution.toString == "size2([a,b,c,d],s(s(s(s(zero)))))")
    assertThrows[NoSolutionException]{
      engine.solve("size2([1,2],s(s(s(zero)))).").getSolution
    }
  }


}
