# EECS-2001-Assignment-1-Luhn-Algorithm-DFA
Assignment 1 for EECS 2001 Theory of Computation. 

Designed a Deterministic Finite Automata (DFA) for Luhn's algorithm in York University Finite Automaton File Format (YUFAFF) which contains 20 states and 200 transition functions

Java class translates txt file into instructions and is for test cases

**_YUFAFF is defined as the following:_**

The first line of the file contains four positive integers n, m, k and t separated by single
spaces, where n = |Q|, m = |Σ|, k = |F| and t is the number of lines in the file that will be
used to describe transitions of the automaton.

The second line of the file contains m strings σ0, σ1, . . . , σm−1 separated by single spaces.
These strings are the names of the characters of the input alphabet. These strings should
be distinct and should not contain any spaces. Also, the string empty is not allowed as a
character name. 

The third line contains k natural numbers separated by single spaces. These numbers
are the indices of the accepting states.

The following t lines each contain the description of one transition defined by δ.
For deterministic finite automata, each of these t lines contains a natural number i, a
character name x and another natural number j, separated by single spaces. There should
be one line containing i x j if and only if δ(qi
, x) = qj
. The values should satisfy 0 ≤ i < n,
0 ≤ j < n, x ∈ {σ0, . . . , σm−1}. (For deterministic finite automata, we should have t = mn.)
For non-deterministic finite automata, each of the t lines contains a natural number i,
a string x and another natural number j, separated by single spaces. The string x should
either be a character name in {σ0, . . . , σm−1} or should be the string empty to indicate a
ε-transition. There should be one line containing i x j if and only if qj ∈ δ(qi
, x).
Anything after these t lines will be ignored, so you can use them to write comments or
explanations.
