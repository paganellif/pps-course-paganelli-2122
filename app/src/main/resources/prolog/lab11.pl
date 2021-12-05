% Es1
% dropAny(?Elem,?List,?OutList)
dropAny(X,[X|T],T).
dropAny(X,[H|Xs],[H|L]):- dropAny(X,Xs,L).

% Es1.1
% dropFirst: drops only the first occurrence (showing no alternative results)
dropFirst(X,[X|T],T):- !.
dropFirst(X,[H|Ti],[H|To]):- dropFirst(X,Ti,To).

% dropLast: drops only the last occurrence (showing no alternative results)
dropLast(X,[H|Ti],[H|To]):- dropLast(X,Ti,To).
dropLast(X,[X|T],T):- !.

% dropAll: drop all occurrences, returning a single list as result
dropAll(_,[],[]):- !.
dropAll(X,[X|Ti],To):- dropAll(X,Ti,To).
dropAll(X,[H|Ti],[H|To]):- dropAll(X,Ti,To).


% Ex2.1
% fromList(+List,-Graph)
fromList([_],[]).
fromList([H1,H2|T],[e(H1,H2)|L]):- fromList([H2|T],L).

% Ex2.2
% fromCircList(+List,-Graph)
fromCircList([I],[e(I,I)]).
fromCircList(H1,[T1],[e(T1,H1)|L]):- !.
fromCircList([H1,H2|T],[e(H1,H2)|L]):- fromCircList(H1,[H1,H2|T],[e(H1,H„2)|L]).
fromCircList(H,[H1,H2|T],[e(H1,H2)|L]):- fromCircList(H,[H2|T],L).

% Ex2.3
% dropNode(+Graph, +Node, -OutGraph)
% drop all edges starting and leaving from a Node
% use dropAll defined in 1.1
dropNode(G,N,O):- dropAll(e(N,_),G,G2), dropAll(e(_,N),G2,O).