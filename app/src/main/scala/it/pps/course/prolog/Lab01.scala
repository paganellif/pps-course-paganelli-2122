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
      search_anytwo(X, [X|Xs]):- search(X,Xs).
      search_anytwo(X, [Y|Xs]):- search_anytwo(X,Xs).
    """))

    // Ex2.1
    engine.addTheory(new Theory(
      """
      % size(List, Size)
      % Size will contain the number of elements in List
      size([],0).
      size([_|T],M) :- size(T,N), M is N+1.
    """))

    // Ex2.2
    engine.addTheory(new Theory(
      """
      % size2(List,Size)
      % Size will contain the number of elements in List,
      % written using notation zero, s(zero), s(s(zero))..
      size2([],zero).
      size2([H|L],s(N)):- size2(L,N).
    """))

    // Ex2.3
    engine.addTheory(new Theory(
      """
      % sum(List,Sum)
      sum([],0).
      sum([H|L],N):- sum(L,M), N is M+H.
    """))

    // Ex2.4
    engine.addTheory(new Theory(
      """
      % average(List,Average)
      % it uses average(List,Count,Sum,Average)
      average([],0).
      average(List,A) :- average(List,0,0,A).
      average([],C,S,A) :- A is S/C.
      average([X|Xs],C,S,A) :- C2 is C+1, S2 is S+X, average(Xs,C2,S2,A).
    """))

    // Ex2.5
    engine.addTheory(new Theory(
      """
      % max(List,Max)
      % Max is the biggest element in List
      % Suppose the list has at least one element
      max([H],H).
      max([H|T],Max):- max(T,Max,H).

      max([H|T],Max,TempMax):- H >= TempMax, max(T,Max,H).
      max([H|T],Max,TempMax):- H < TempMax, max(T,Max,TempMax).

      max([],Max,TempMax):- Max is TempMax.
    """))

    // Ex2.6
    engine.addTheory(new Theory(
      """
      % minmax(List,Max,Min)
      % Max is the biggest element in List
      % Min is the smallest element in List
      % Suppose the list has at least one element
      minmax([H],H,H).
      minmax([H|T],Max,Min):- minmax(T,Max,Min,H,H).

      % ci sono 4 possibili scenari che possono verificarsi:
      % caso in cui la testa è maggiore di TempMax e maggiore di TempMin -> aggiorno solo TempMax
      minmax([H|T],Max,Min,TempMax,TempMin):- H >= TempMax, H > TempMin, minmax(T,Max,Min,H,TempMin).

      % caso in cui la testa è minore di TempMax e minore di TempMin -> aggiorno solo TempMin
      minmax([H|T],Max,Min,TempMax,TempMin):- H < TempMax, H =< TempMin, minmax(T,Max,Min,TempMax,H).

      % caso in cui la testa è maggiore di TempMax e minore di TempMin -> aggiorno tutte due
      minmax([H|T],Max,Min,TempMax,TempMin):- H >= TempMax, H =< TempMin, minmax(T,Max,Min,H,H).

      % caso in cui la testa è minore di TempMax e maggiore di TempMin -> non aggiorno nessuno
      minmax([H|T],Max,Min,TempMax,TempMin):- H < TempMax, H > TempMin, minmax(T,Max,Min,TempMax,TempMin).

      minmax([],Max,Min,TempMax,TempMin):- Max is TempMax, Min is TempMin.
    """))

    // Ex3.1
    engine.addTheory(new Theory(
      """
      % same(List1,List2)
      % are the two lists exactly the same?
      same([],[]).
      same([X|Xs],[X|Ys]):- same(Xs,Ys).
    """))

    // Ex3.2
    engine.addTheory(new Theory(
      """
      % all_bigger(List1,List2)
      % all elements in List1 are bigger than those in List2, 1 by 1
      all_bigger([],[]).
      all_bigger([H1|T1],[H2|T2]):- H1 > H2, all_bigger(T1,T2).
    """))

    // Ex3.3
    engine.addTheory(new Theory(
      """
      % sublist(List1,List2)
      % List1 should contain elements all also in List2

      sublist([],_).
      sublist([H1|T1],L2):- search(H1,L2), sublist(T1, L2).
    """))

    // Ex4.1
    engine.addTheory(new Theory(
      """
      % seq(N,List)

      seq(0,[]).
      seq(N,[0|T]):- N > 0, N2 is N-1, seq(N2,T).
    """))

    // Ex4.2
    engine.addTheory(new Theory(
      """
      % seqR(N,List)
      seqR(0,[0]).
      seqR(N,[N|T]):- N > 0, M is N-1, seqR(M,T).
    """))

    // Ex4.3
    engine.addTheory(new Theory(
      """
      % last(L1,I,L12)
      append([],L,L).
      append([H|T],L,[H|M]):- append(T,L,M).

      last([],I,[I]).
      last([H|T],I,[H|M]):- last(T,I,M).

      % seqR2(N,List)
      seqR2(0,[0]).
      seqR2(N,L):- I is N-1, last(M,N,L), seqR2(I,M).
    """))

    // Ex4.4
    engine.addTheory(new Theory(
      """
      % inv(List,List)
      inv([I],[I]).
      inv([H1|T1],[H2|T2]):- last(Y,H1,T2), last(X,H2,T1), inv(X,Y).

      % double(List,List)
      % suggestion: remember predicate append/3
      double(L1,L11):- append(L1,L1,L11).

      % times(List,N,List)
      times(_,0,[]).
      times(L,1,L).
      times(L1,N,L1N):- N > 0, M is N-1, append(X,L1,L1N), times(L1,M,X).

      % proj(List,List)
    """))

    engine
  }
}
