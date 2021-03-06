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
dropAll(_,[],[]).
dropAll(X,[X|T],_):- dropAll(X,T,_).
dropAll(X,[H|T],[H|_]):- dropAll(X,T,_).

% Ex2.1
% fromList(+List,-Graph)
fromList([_],[]).
fromList([H1,H2|T],[e(H1,H2)|L]):- fromList([H2|T],L).

% Ex2.2
% fromCircList(+List,-Graph)
fromCircList([I],[e(I,I)]).
fromCircList(H1,[T1],[e(T1,H1)|L]):- !.
fromCircList([H1,H2|T],[e(H1,H2)|L]):- fromCircList(H1,[H1,H2|T],[e(H1,H2)|L]).
fromCircList(H,[H1,H2|T],[e(H1,H2)|L]):- fromCircList(H,[H2|T],L).

% Ex2.3
% dropNode(+Graph, +Node, -OutGraph)
% drop all edges starting and leaving from a Node
% use dropAll defined in 1.1
dropNode(G,N,O):- dropAll(e(N,_),G,G2), dropAll(e(_,N),G2,O).

% Ex2.4
% reaching(+Graph, +Node, -List)
% all the nodes that can be reached in 1 step from Node
% possibly use findall, looking for e(Node,_) combined
% with member(?Elem,?List)
member(H,[H|T]).
member(X,[H|T]):- member(X,T).
reaching(G,N,L):- findall(X,member(e(N,X),G),L).

% Ex2.5
% anypath(+Graph, +Node1, +Node2, -ListPath)
% a path from Node1 to Node2
% a path from N1 to N2 exists if there is a e(N1,N2)
% a path from N1 to N2 is OK if N3 can be reached from N1,
% and then there is a path from N2 to N3, recursively
anypath(G,N1,N2,[e(N1,N2)]):- member(e(N1,N2),G).
anypath(G,N1,N2,[e(N1,N3)|T]):- member(e(N1,N3),G), anypath(G,N3,N2,T).

% Ex2.6
% allreaching(+Graph, +Node, -List)
% all the nodes that can be reached from Node
% Suppose the graph is NOT circular!
% Use findall and anyPath!
allreaching(G,N,L):- findall(N2,anypath(G,N,N2,LL),L).
