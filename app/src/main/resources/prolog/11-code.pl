append([],L,L).
append([I|Is],L,[I|Os]):-append(Is,L,Os).

% member2(List, Elem, ListWithoutElem)
member2([X|Xs],X,Xs).
member2([X|Xs],E,[X|Ys]):-member2(Xs,E,Ys).

% permutation(Ilist, Olist)
permutation([],[]).
permutation(Xs, [X | Ys]) :-
	member2(Xs,X,Zs),
	permutation(Zs, Ys).
	
interval(A,B,A).
interval(A,B,X):- A2 is A+1, A2<B, interval(A2,B,X).

neighbour(A,B,A,B2):-B2 is B+1.
neighbour(A,B,A,B2):-B2 is B-1.
neighbour(A,B,A2,B):-A2 is A+1.
neighbour(A,B,A2,B):-A2 is A-1.

gridlink(N,M,link(X,Y,X2,Y2)):-
	interval(0,N,X),
	interval(0,M,Y),
	neighbour(X,Y,X2,Y2),
	X2>0,Y2>0,X2<N,Y2<M.
	

map(L,E,OE,OL):-findall(OE,member(E,L),OL).

%findall([E,X], (member(E,[10,20,30,40,50]),E>20,X is E+1) ,L).


last([E],E).
last([_|T],E) :- last(T,E).

test(last, "t1", last([a,b],b), [last([a,b],b)]).
test(last, "t2", last([a,b],_), [last([a,b],b)]).
test(last, "empty", last([],_), []).

test_one(Pred, Name, Act, Exp, Flag) :- 
	test(Pred,Name, Goal, Exp), 
	findall(Goal, Goal, Actual), 
	check(Actual, Exp, Flag).
check(Expected,Expected,ok).
check(Actual,Expected,no):- Actual \= Expected.

test_all(Predicate, Result) :- 
	findall( (N,Flag) , 
	test_one(Predicate,N,_,_,Flag), Result).

% test_all(last, Res).

% merge(List1,List2,OutList)
% merge two sorted lists
merge(Xs,[],Xs):-!.
merge([],Ys,Ys).
merge([X|Xs],[Y|Ys],[X|Zs]):-X<Y,!,merge(Xs,[Y|Ys],Zs).
merge([X|Xs],[X|Ys],[X,X|Zs]):-!,merge(Xs,Ys,Zs).
merge([X|Xs],[Y|Ys],[Y|Zs]):-merge([X|Xs],Ys,Zs).

lookup([E|_],0,E):- !.
lookup([H|T],s(N),E) :- lookup(T,N,E).

% quicksort (+Ilist,-Olist)

quicksort([],[]).
quicksort([X|Xs],Ys):-
	partition(Xs,X,Ls,Bs),
	quicksort(Ls,LOs),
	quicksort(Bs,BOs),
	append(LOs,[X|BOs],Ys).

% partition (Ilist,Pivot,Littles,Bigs)

partition([],_,[],[]).
partition([X|Xs],Y,[X|Ls],Bs):- X<Y, !, partition(Xs,Y,Ls,Bs).
partition([X|Xs],Y,Ls,[X|Bs]):- partition(Xs,Y,Ls,Bs).


% leftlist(+Tree,-List), returns the left-most branch as a list
leftlist(nil,[]).
leftlist(tree(nil,E,_),[E]):-!.
leftlist(tree(T,E,_),[E|L]) :- leftlist(T,L).

% search(+Tree,?Elem), search Elem in Tree
search(tree(_,E,_),E).
search(tree(L,_,_),E):- search(L,E).
search(tree(_,_,R),E):- search(R,E).

% leaves(+Tree,-ListLeaves), returns the list of leaves
leaves(nil,[]).			% handling void tree
leaves(tree(nil,E,nil),[E]):-!.	% handling a leaf
leaves(tree(L,_,R),O):-	% general case
	leaves(L,OL), 		% OL are leaves on left
	leaves(R,OR),		% OR are leaves on right
	append(OL,OR,O).	% O appends the two
	
% get_ids(+Table,-List)
% gets the List of ids from the Table
get_ids([],[]).
get_ids([user(ID,_,_)|T],[ID|L]):-get_ids(T,L).

% query(+Table,+Id,-Tuple)
% gets the Tuple with Id from the Table
query([user(ID,N,C)|_],ID, user(ID,N,C)).
query([_|T],ID,Tuple):- query(T,ID,Tuple).

% update(+Table,+Id,+NewTuple,-NewTable)
% updates the tuple with Id to Tuple
update([user(ID,_,_)|T],ID,Tuple,[Tuple|T]).
update([H|T],ID,Tuple,[H|Table]):-update(T,ID,Tuple,Table).

% factorial(+N,-Out,?Cache)
% cache is a partial cache of factorials [1,1,2,6,24|_]

factorial(N,Out,Cache):-factorial(N,Out,Cache,0).

factorial(N,Res,[Res|_],N):-!,nonvar(Res).
factorial(N,Out,[H,V|T],I):-
	var(V), !, I2 is I+1, V is H*I2,  
	factorial(N,Out,[V|T],I2).
factorial(N,Out,[_,V|T],I):-
	I2 is I+1, factorial(N,Out,[V|T],I2).
	

% count(+List,+Elem,-Num)
% counts how many occurrences of Elem exist in List
count([],_,0).
count([E|T],E,N) :- !, count(T,E,N2), N is N2+1.
count([_|T],E,N) :-  count(T,E,N).

% count(+List,+Elem,-Num)
% counts how many occurrences of Elem are in List
count(L,E,N) :- count(L,E,0,N).
count([],_,N,N).
count([E|T],E,N,O) :- !, N2 is N+1, count(T,E,N2,O).
count([_|T],E,N,O) :- count(T,E,N,O).

% a goal that writes over a file
write_something:- 
	tell('/home/mirko/aula/out.txt'), % Opens the file
	write(prova(1)), 		
	nl,
	write(prova),tab(2),
	write('str ing'),tab(2),
	write('string'),tab(2),
	put('+'),char_code(C,47),put(C),
	told.				% Closes the file
	
read_terms(L):- 
	see('/home/mirko/aula/in.txt'), % Opens the file to read	
	get_terms(L), 		% reads the terms in it
	seen.				% Closes the file
get_terms([T|L]):- read(T),!,get_terms(L).
get_terms([]).

read_chars(L):- 
	see('/home/mirko/aula/in.txt'), % Opens the file to read	
	get_chars(L), 			% reads the chars in it
	seen.				% Closes the file
get_chars(L):- get(T),!,process(T,L).   % check also get0(_)
process(-1,[]):-!.
process(C,[C|L]):-get_chars(L).

leafs(node(N),[N]):-!.
leafs(NODE,L):- NODE =.. [node,E|T], lleafs(T,L).
lleafs([],[]).
lleafs([H|T],LL):-leafs(H,L),lleafs(T,L2),append(L,L2,LL).

% all(+Term,+List): are all elements in List of kind Term?
all(_,[]).
all(X,[X|T]):-all(X,T).

% all(+Term,+List): are all elements in List of kind Term?
all(_,[]).
all(X,[Y|T]):-copy_term(X,X2),Y=X2,all(X,T).

node(c(X,Y)):-member(X,[0,1,2,3]),member(Y,[0,1,2,3]).

link(c(X,Y),c(X,YY)):-node(c(X,Y)), 
			(YY is Y-1; YY is Y+1) ,
			node(c(X,YY)).

link(c(X,Y),c(XX,Y)):-node(c(X,Y)), 
			(XX is X-1; XX is X+1), 
			node(c(XX,Y)).

factorial(0,1).
factorial(X,Y):- Xm is X-1, factorial(Xm,Y2),	Y is Y2*X.

withcache(P):-cached(P),!.
withcache(P):-once(P),assert(cached(P)).

% withcache(factorial(5,X)).

