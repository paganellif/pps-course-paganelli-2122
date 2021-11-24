package it.pps.course.prolog

import alice.tuprolog.{Prolog, Theory}

object Lab01 {
  def apply(): Prolog = {
    val engine: Prolog = new Prolog

    // Ex1.1
    engine.setTheory(new Theory(
      """
      % search(Elem, List)
      search(X, [X|_]).
      search(X, [_|Xs]) :- search(X, Xs).
    """))

    // Ex1.2
    engine.addTheory(new Theory(
      """
      % search2(Elem, List)
      % looks for two consecutive occurrences of Elem
      search2(X, [X,X|_]).
      search2(X, [_|Xs]):- search2(X,Xs).
    """))

    // Ex1.3
    engine.addTheory(new Theory(
      """
      % search_two(Elem,List)
      % looks for two occurrences of Elem with any element in between!
      search_two(X, [X,Y,X|_]).
      search_two(X, [_|Xs]):- search_two(X,Xs).
    """))

    // Ex1.4
    engine.addTheory(new Theory(
      """
      % search_anytwo(Elem,List)
      % looks for any Elem that occurs two times, anywhere
      search_anytwo(X, [X,_,X]).
      search_anytwo(X, [X|Xs]):- search(X,Xs), search_anytwo(X,Xs).
    """))

    engine
  }

  /*
  // query
  println(engine.solve("search(a,[a,b,c]).").getSolution) // search(a,[a,b,c])
  //println(engine.solve("search(a,[c,d,e]).").getSolution) // NoSolutionException

  // iteration
  println(engine.solve("search(X,[a,b,c]).").getSolution) // search(a,[a,b,c])

  // generation
  println(engine.solve("search(a,X).").getSolution) // search(a,[a|_2124])
  println(engine.solve("search(a,[X,b,Y,Z]).").getSolution) // search(a,[a,b,Y,Z])
  println(engine.solve("search(X,Y).").getSolution) // search(X,[X|_2150])


  println(engine.solve("search2(a,[b,c,a,a,d,e,a,a,g,h]).").getSolution) // search2(a,[b,c,a,a,d,e,a,a,g,h])
  println(engine.solve("search2(a,[b,c,a,a,a,d,e]).").getSolution) // search2(a,[b,c,a,a,a,d,e])
  println(engine.solve("search2(X,[b,c,a,a,d,d,e]).").getSolution) // search2(a,[b,c,a,a,d,d,e])
  println(engine.solve("search2(a,L).").getSolution) // search2(a,[a|[a|_2204]])
  println(engine.solve("search2(a,[_,_,a,_,a,_]).").getSolution) // search2(a,[a,a,a,_2217,a,_2218])
   */
}
