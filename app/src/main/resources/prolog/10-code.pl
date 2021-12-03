member([H|T],H,T).
member([H|T],E,[H|T2]):- member(T,E,T2).

permutation([],[]).
permutation(L,[H|TP]) :- member(L,H,T),
                          permutation(T,TP).

quicksort([],[]).
quicksort([X|Xs],Ys):-
	partition(Xs,X,Ls,Bs),
	quicksort(Ls,LOs),
	quicksort(Bs,BOs),
	append(LOs,[X|BOs],Ys).

partition([],_,[],[]).
partition([X|Xs],Y,[X|Ls],Bs):- X<Y, !, partition(Xs,Y,Ls,Bs).
partition([X|Xs],Y,Ls,[X|Bs]):- partition(Xs,Y,Ls,Bs).

sum(X,zero,X).
sum(X,s(Y),s(Z)):-sum(X,Y,Z).
mul(X,zero,zero).
mul(X,s(Y),Z):-mul(X,Y,W),sum(W,X,Z).

dec(s(X),X).

factorial(zero,s(zero)).
factorial(s(X),Y):-factorial(X,Z),mul(s(X),Z,Y).

search([E|_],E).
search([_|T],E) :- search(T,E).

% puts in 2nd arg the position where 3rd is found
lookup([E|_],zero,E).
lookup([H|T],s(N),E) :- lookup(T,N,E).

% concatenates 1st and 2nd list, with output in 3rd  
append([],L,L).
append([H|T],L,[H|M]):- append(T,L,M).

%create_list (5,[5,4,3,2,1,0]).
%example: create_list(s(s(zero)),[s(s(zero)),s(zero),zero])
create_list(zero,[zero]).
create_list(s(X),[s(X)|T]):- create_list(X,T).

%erase_all (L,LO).
%example: erase_all([a,b,c],[zero,zero,zero])
erase_all([],[]).
erase_all([_|T],[zero|T2]):-erase_all(T,T2).
