This university project, carried out as part of my Computer Science degree at the University of Caen Normandy, involves the creation of a complete CoreWar environment, where multiple programs written in RedCode (a language similar to assembly) compete within a shared virtual memory.

CoreWar is a programming game in which multiple programs battle inside a virtual memory. The goal is to be the last program with at least one process still alive.

To compile the project, simply run ant in the project's root directory. If this is the first time compiling, you must first run ant init.

To run the tests:
ant test

A program validator is available. It checks whether all the programs in the progs folder are valid. To run it:
ant checker
Note: a sh shell must be available.

It is also possible to check specific files by running the checker.sh script:
scripts/checker.sh <program 1> <program 2> ... <program n>

To generate the documentation:
ant doc

To create a JAR archive:
ant jar

To launch a game with terminal display:
java -cp build corewar.Main <program 1> <program 2> ... <program n>

To launch a game with graphical display:
java -cp build interfaces.CorewarWindow <program 1> <program 2> ... <program n>

To open the main menu:
java -cp build interfaces.MainMenu

The genetic algorithm accepts several parameters: the number of generations, the selection mode, and the initial programs. The available selection modes are elitism (e) and roulette (w).
scripts/algo_gen.sh <number of generations> <selection mode> <program 1> <program 2> ... <program n>

It is also possible to run the genetic algorithm without providing initial programs. In this case, you must specify the number of programs to be selected at each generation:
scripts/algo_gen.sh <number of programs to select> <number of generations> <selection mode>

The genetic algorithm writes the best program from the final generation to progs/best.red. It also generates a CSV file detailing the evolution of certain metrics over the generations. To generate corresponding graphs based on this data, you can run the script:
scripts/plotmaker.py
