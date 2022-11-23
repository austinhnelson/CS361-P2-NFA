# Project 2: Nondeterministic Finite Automata (NFA)
* Author: Austin Nelson, Patrick Santana
* Class: CS121 Section #002
* Semester: Fall 2022

## Overview
A program that simulates a nondeterministic finite automata (NFA), given a specific formatted input file. The NFA includes a function to produce an equivalent deterministic finite automata (DFA) to the specific instance. 

## Reflection

This project definitely was a lot more involved than the previous project (P1) but involved a very similar structure to the DFA implementation. Implementing the eClosure method was a bit of a struggle at first as it took us some time to understand how the recursion should work. However, after writing out the algorithim (DFS) on paper, implementing it was not a big struggle. The eClosure method eventually broke when we implemented the getDFA method when the case of epsilon transitions were back and forth between states as it got into an infinite loop and never broke. Once we fixed that, through the use of the debugger, there weren't any more issues with the recursion and we feel like we understand it pretty well.

We didn't exactly remember at first the process of converting NFAs to DFAs but once we brushed up on it, the process was relatively smooth. There isn't anything in particular with the project that still isn't clear as we worked out most of the issues in the process. Some techniques we implemented for testing and debugging the code was adding an NFA toString() method to print out along with the DFA toString(). This helped verify that our NFA was correctly reading in the input file and creating an equivalent machine. In addition, we also used the debugger with command line arguements to step through different cases and make sure our toDFA() was traversing correctly, for both DFS and BFS. 

I wouldn't say there was much we would change about the design process (besides starting earlier!) but adding git branches is something that might be useful in the future for our development. There were a couple times we both had similar 
code to push and having a seperate branches could be helpful (and merging a single main). If we could go back in time, I would give us the recommendation to 
write everything out of paper (algorithims, input machines) before we started coding so we could clealy understand the task. 

## Compiling and Using
Compiling:
$javac fa/nfa/*.java

To Run:
$java fa/nfa/NFADriver.java <input file name>

Input File Structure: First line - names of the final states. Second line - name of the start state. Third line - any other states in the machine. Fourth line - consists of the transitions inputted as three characters each e.g. a1b Fifth line on - strings for the machine to test if valid

## Sources used
https://www.geeksforgeeks.org/conversion-from-nfa-to-dfa/ 