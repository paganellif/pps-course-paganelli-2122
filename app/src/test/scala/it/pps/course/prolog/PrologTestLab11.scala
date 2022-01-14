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
    
    assert(engine.solve("dropLast(10,[10,20,10,30,10],[10,20,10,30]).").isSuccess)
    assert(engine.solve("dropLast(5,[5,5,5,5,5],[5,5,5,5]).").isSuccess)
    assert(engine.solve("dropLast(10,[10,20,10,30,10],L).").getSolution.toString == "dropLast(10,[10,20,10,30,10],[10,20,10,30])" )
    assert(engine.solve("dropLast(10,[10,20,30],[20,30]).").isSuccess)
    assertThrows[NoSolutionException]{
      engine.solve("dropLast(11,[10,20,10,30,10],L).").getSolution
    }

    assert(engine.solve("dropAll(10,[10,20,10,30,10],[20,30]).").isSuccess)
    assert(engine.solve("dropAll(5,[5,5,5,5,5],[]).").isSuccess)
    //assert(engine.solve("dropAll(10,[10,20,10,30,10],L).").getSolution.toString == "dropAll(10,[10,20,10,30,10],[20,30])" )
    assertThrows[NoSolutionException]{
      engine.solve("dropAll(11,[10,20,10,30,10],[]).").getSolution
    }
  }

  test("Ex2.1") {
    assert(engine.solve("fromList([10,20,30],[e(10,20),e(20,30)]).").isSuccess)
    assert(engine.solve("fromList([10,20],[e(10,20)]).").isSuccess)
    assert(engine.solve("fromList([10],[]).").isSuccess)
    assertThrows[NoSolutionException] {
      engine.solve("fromList([10],[e(10,10)]).").getSolution
    }
  }

  test("Ex2.2") {
    assert(engine.solve("fromCircList([10],[e(10,10)]).").isSuccess)
    assert(engine.solve("fromCircList([10,20,30],[e(10,20),e(20,30),e(30,10)]).").isSuccess)
    assert(engine.solve("fromCircList([10,10,10,10,10],[e(10,10),e(10,10),e(10,10),e(10,10),e(10,10)]).").isSuccess)
    assert(engine.solve("fromCircList([10,10,10,10,20],[e(10,10),e(10,10),e(10,10),e(10,20),e(20,10)]).").isSuccess)
    assert(engine.solve("fromCircList([10,20],[e(10,20),e(20,10)]).").isSuccess)
    assertThrows[NoSolutionException] {
      engine.solve("fromCircList([10],[e(10,20)]).").getSolution
    }
  }

  test("Ex2.3") {
    assert(engine.solve("dropAll(e(1,2),[e(1,2),e(1,3),e(2,3)],[e(1,3),e(2,3)]).").isSuccess)
    assert(engine.solve("dropFirst(e(1,_),[e(1,2),e(1,3),e(2,3)],[e(1,3),e(2,3)]).").isSuccess)
    assert(engine.solve("dropAll(e(1,_),[e(1,2),e(1,3),e(2,3)],[e(2,3)]).").isSuccess)
    assert(engine.solve("dropNode([e(1,2),e(1,3),e(2,3)],1,[e(2,3)]).").isSuccess)
    assert(engine.solve("dropNode([e(1,2),e(2,3),e(1,3)],1,[e(2,3)]).").isSuccess)
    assert(engine.solve("dropNode([e(1,2),e(2,3),e(1,3),e(3,2)],1,[e(2,3),e(3,2)]).").isSuccess)
  }

  test("Ex2.4") {
    assert(engine.solve("member(2,[1,2,3,4,5]).").isSuccess)
    assert(engine.solve("member(2,[1,2,3,2,2]).").isSuccess)
    assert(engine.solve("member(e(1,_),[e(1,2),e(1,3),e(2,3)]).").isSuccess)
    //assert(engine.solve("reaching([e(1,2),e(1,3),e(2,3)],1,L).").isSuccess) // -> L/[2,3]
    //assert(engine.solve("reaching([e(1,2),e(1,2),e(2,3)],1,L).").isSuccess) // -> L/[2,2]
    assertThrows[NoSolutionException] {
      engine.solve("member(0,[1,2,3,2,2]).").getSolution
    }
  }
}
