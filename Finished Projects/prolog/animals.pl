
/*
animal_facts.pl
animal_rules.pl
animal_qs.pl
*/

/* Animal facts */
/* Creating some animals*/

tiger(tigger).
tiger(jumpy).

lion(mufasa).
lion(scar).
lion(simba).

cow(chum).
cow(whatever).

dog(bow).
dog(barky).

cat(purrs).
cat(meow).

lizard(harry).
shark(xavier).
ostrich(mina).



/* Creating some rules and descriptions */
/* Animal Rules */

mammal(X) :- tiger(X); lion(X); cow(X); dog(X); cat(X).
carnivore(X) :- tiger(X); lion(X).
chase(X,Y) :- ((lion(X); tiger(X)), mammal(Y), \+ lion(Y), \+ tiger(Y)); (dog(X), cat(Y)).
catches(X,Y) :- chase(X,Y), carnivore(X).

/* Doing some testing */
/* Animal Questions */
tiger_exists() :- tiger(X), write('Tigers: '), write(X).
tiger_and_cow() :- tiger(X), cow(Y), write('Tiger: '), write(X), write(" Cow: "), write(Y).
what_is_lion() :- lion(X), mammal(X), carnivore(X).
what_is_cow() :- cow(X), mammal(X), carnivore(X).
tigers_eat() :- tiger(X), catches(X, Y), write('Tiger catches: '), write(Y).



