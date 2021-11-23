package it.pps.course.prolog

import alice.tuprolog.{Prolog, SolveInfo, Theory}

object Scala2PSimpleExample extends App {
  val engine: Prolog = new Prolog

  // Compound terms
  engine.setTheory(new Theory(
    """
      manager(person(john,smith,1283)).
      clerk(person(jim,white,3475)).
      clerk(person(george,red,8765)).
      chief(person(john,smith,1283),person(george,red,8765)).
    """))
  println(engine.solve("chief(X,person(Y,Z,8765)).").getSolution)
  //chief(person(john,smith,1283),person(george,red,8765))

  // Universal facts
  engine.addTheory(new Theory(
    """
      plus(X,0,X).
      plus(0,X,X).
    """))
  println(engine.solve("plus(1,0,X).").getSolution)
  //plus(1,0,1)
  println(engine.solve("plus(0,1,X).").getSolution)
  //plus(0,1,1)

  // Goal conjunction + Anonymous variables
  engine.addTheory(new Theory(
    """
      father(abraham,isaac).
      father(terach,abraham).
      male(isaac).
    """))
  // Is there a son X of terach and a son Y of X?
  println(engine.solve("father(terach,X),father(X,Y).").getSolution)
  //father(terach,abraham),father(abraham,isaac)
  // Is there a son X of terach which has some son?
  println(engine.solve("father(terach,X),father(X,_).").getSolution)
  //father(terach,abraham),father(abraham,isaac)

  // Rules
  engine.addTheory(new Theory(
    """
      son(X,Y):-father(Y,X),male(X).
      grandfather(X,Z):-father(X,Y),father(Y,Z).
    """))
  println(engine.solve("grandfather(terach,isaac).").getSolution)
  //grandfather(terach,isaac)
  println(engine.solve("grandfather(terach,X).").getSolution)
  //grandfather(terach,isaac)

  // Lists
  engine.addTheory(new Theory(
    """
      search([E|_],E).
      search([_|T],E) :- search(T,E).

      % puts in 2nd arg the position where 3rd is found
      lookup([E|_],zero,E).
      lookup([H|T],s(N),E) :- lookup(T,N,E).

      % concatenates 1st and 2nd list, with output in 3rd
      append([],L,L).
      append([H|T],L,[H|M]):- append(T,L,M).

      create_list(zero,[zero]).
      create_list(s(X),[s(X)|T]):- create_list(X,T).

      erase_all([],[]).
      erase_all([_|T],[zero|T2]):-erase_all(T,T2).


      % caso in cui la testa è l'elemento da cambiare
      %change([X|T],X,Y,L):- change(T,X,Y,[Y|L]).
      % caso in cui la testa non è l'elemento da cambiare
      %change([H|T],X,Y,L):- change(T,X,Y,[H|L]).
      %change([],_,_,[]).
    """))
  println(engine.solve("search([a,b,a,d],a).").getSolution)
  println(engine.solve("append([a,b],[c,d],X).").getSolution)
  // append([a,b],[b,d],[a,b,c,d])
  println(engine.solve("lookup([a,b,c,b],X,b).").getSolution)
  // lookup([a,b,c,b],s(zero),b)
  //println(engine.solve("change([a,b,c,d,a,b],a,a1,X).").getSolution)
}
