package it.pps.course.prolog

import alice.tuprolog.{NoSolutionException, Prolog}
import org.junit.runner.RunWith
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PrologTestLab11 extends AnyFunSuite with Matchers {
  val engine: Prolog = PrologLab("prolog/lab11.pl")

  test("Ex1"){
    assert(engine.solve("dropAny(10,[10,20,10,30,10],L).").isSuccess)
    assert(List("dropAny(10,[10,20,10,30,10],[20,10,30,10])",
                "dropAny(10,[10,20,10,30,10],[10,20,30,10])",
                "dropAny(10,[10,20,10,30,10],[10,20,10,30])")
      .contains(engine.solve("dropAny(10,[10,20,10,30,10],L).").getSolution.toString)
    )
    assertThrows[NoSolutionException]{
      engine.solve("dropAny(5,[10,20,10,30,10],L).").getSolution
    }
  }

  test("Ex1.1"){
    assert(engine.solve("dropFirst(10,[10,20,10,30,10],[20,10,30,10]).").isSuccess)
    assert(engine.solve("dropFirst(5,[5,5,5,5,5],[5,5,5,5]).").isSuccess)
    assert(engine.solve("dropFirst(3,[1,2,3],[1,2]).").isSuccess)
    assert(engine.solve("dropFirst(10,[10,20,10,30,10],L).").getSolution.toString == "dropFirst(10,[10,20,10,30,10],[20,10,30,10])" )
    assertThrows[NoSolutionException]{
      engine.solve("dropFirst(11,[10,20,10,30,10],L).").getSolution
    }
/*
    assert(engine.solve("dropLast(10,[10,20,10,30,10],[10,20,10,30]).").isSuccess)
    assert(engine.solve("dropLast(10,[10,20,10,30,10],L).").getSolution.toString == "dropLast(10,[10,20,10,30,10],[10,20,10,30])" )
    assertThrows[NoSolutionException]{
      engine.solve("dropLast(11,[10,20,10,30,10],L).").getSolution
    }

    assert(engine.solve("dropAll(10,[10,20,10,30,10],[20,30]).").isSuccess)
    assert(engine.solve("dropAll(10,[10,20,10,30,10],L).").getSolution.toString == "dropAll(10,[10,20,10,30,10],[20,30])" )
    assertThrows[NoSolutionException]{
      engine.solve("dropAll(11,[10,20,10,30,10],L).").getSolution
    }
  }*/
}
