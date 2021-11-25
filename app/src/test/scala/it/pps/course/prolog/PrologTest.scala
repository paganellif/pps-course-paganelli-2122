package it.pps.course.prolog

import alice.tuprolog.{NoSolutionException, Prolog}
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

  test("Ex2.3"){
    assert(engine.solve("sum([1,2,3],6).").isSuccess)
    assert(engine.solve("sum([],0).").isSuccess)
    assert(engine.solve("sum([2,4,6,8,10],X).").getSolution.toString == "sum([2,4,6,8,10],30)")
    assertThrows[NoSolutionException]{
      engine.solve("sum([2,4,6,8,10],0).").getSolution
    }
  }

  test("Ex2.4"){
    assert(engine.solve("average([1,2,3],2).").isSuccess)
    assert(engine.solve("average([],0).").isSuccess)
    assert(engine.solve("average([2,4,6,8,10],X).").getSolution.toString == "average([2,4,6,8,10],6)")
    assertThrows[NoSolutionException]{
      engine.solve("average([2,4,6,8,10],0).").getSolution
    }
  }

  test("Ex2.5"){
    assert(engine.solve("max([1,2,3],3).").isSuccess)
    assert(engine.solve("max([2,400,6,84],X).").getSolution.toString == "max([2,400,6,84],400)")
    assertThrows[NoSolutionException]{
      engine.solve("max([],0).").getSolution
      engine.solve("max([2,4,6,8,10],0).").getSolution
    }
  }

  test("Ex2.6"){
    assert(engine.solve("minmax([1,2,3],3,1).").isSuccess)
    assert(engine.solve("minmax([1],1,1).").isSuccess)
    assert(engine.solve("minmax([2,400,6,84],X,Y).").getSolution.toString == "minmax([2,400,6,84],400,2)")
    assertThrows[NoSolutionException]{
      engine.solve("minmax([],0,0).").getSolution
      engine.solve("minmax([2,4,6,8,10],2,6).").getSolution
    }
  }

  test("Ex3.1"){
    assert(engine.solve("same([1,2,3],[1,2,3]).").isSuccess)
    assert(engine.solve("same([1],[1]).").isSuccess)
    assert(engine.solve("same([],[]).").isSuccess)
    assert(engine.solve("same([2,400,6,84],X).").getSolution.toString == "same([2,400,6,84],[2,400,6,84])")
    assertThrows[NoSolutionException]{
      engine.solve("same([],[1,2,3]).").getSolution
      engine.solve("same([2,4,6,8,10],[1,2]).").getSolution
    }
  }

  test("Ex3.2"){
    assert(engine.solve("all_bigger([1,2,3],[0,1,2]).").isSuccess)
    assert(engine.solve("all_bigger([1],[-3]).").isSuccess)
    assertThrows[NoSolutionException]{
      engine.solve("all_bigger([],[1,2,3]).").getSolution
      engine.solve("all_bigger([2,4,6,8,10],[1,2]).").getSolution
    }
  }

  test("Ex3.3"){
    assert(engine.solve("sublist([1,2],[5,3,2,1]).").isSuccess)
    assert(engine.solve("sublist([1,2],[1,2]).").isSuccess)
    assert(engine.solve("sublist([1],[-3,1,4,0]).").isSuccess)
    assert(engine.solve("sublist([1,3,4,2],[-3,1,4,0,3,5,6,2]).").isSuccess)
    assert(engine.solve("sublist([],[1,2,3]).").isSuccess)
    assertThrows[NoSolutionException]{
      engine.solve("sublist([0],[1,2,3]).").getSolution
      engine.solve("sublist([2,4,6,8,10],[1,2]).").getSolution
    }
  }

  test("Ex4.1"){
    assert(engine.solve("seq(0,[]).").isSuccess)
    assert(engine.solve("seq(3,[0,0,0]).").isSuccess)
    assert(engine.solve("seq(5,X).").getSolution.toString == "seq(5,[0,0,0,0,0])")
    assertThrows[NoSolutionException]{
      engine.solve("seq(2,[1,2,3]).").getSolution
      engine.solve("seq(2,[1,2]).").getSolution
    }
  }

  test("Ex4.2"){
    assert(engine.solve("seqR(0,[0]).").isSuccess)
    assert(engine.solve("seqR(3,[3,2,1,0]).").isSuccess)
    assert(engine.solve("seqR(5,X).").getSolution.toString == "seqR(5,[5,4,3,2,1,0])")
    assertThrows[NoSolutionException]{
      engine.solve("seqR(2,[2,1]).").getSolution
      engine.solve("seqR(3,[0]).").getSolution
    }
  }
}
