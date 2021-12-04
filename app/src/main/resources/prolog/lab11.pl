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
dropAll(X,[X|Ti],To):- dropAll(X,Ti,To).
dropAll(X,[H|Ti],[H|To]):- dropAll(X,Ti,To).
dropAll(X,[],[]):- !.

% Ex2.1
% fromList(+List,-Graph)
fromList([_],[]).
fromList([H1,H2|T],[e(H1,H2)|L]):- fromList([H2|T],L).

% Ex2.2
% fromCircList(+List,-Graph)
fromCircList([_],[e(_,_)]).
fromCircList([H1,H2|T],[e(H1,H2)|L]):- fromCircList([H1,H2|T],[e(H1,H2)|L]).