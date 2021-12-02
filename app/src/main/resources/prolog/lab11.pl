% Es1
% dropAny(?Elem,?List,?OutList)
dropAny(X,[X|T],T).
dropAny(X,[H|Xs],[H|L]):- dropAny(X,Xs,L).

% Es1.1
% dropFirst: drops only the first occurrence (showing no alternative results)
dropFirst(X,[X|T],T).
dropFirst(X,[Y|T],T):- dropFirst(X,T,T).

% dropLast: drops only the last occurrence (showing no alternative results)

% dropAll: drop all occurrences, returning a single list as result
