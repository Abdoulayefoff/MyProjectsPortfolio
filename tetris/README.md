Implémentation d'un jeu d'assemblage de formes avec architecture MVC et design patterns.

Ce projet universitaire réalisé dans le cadre du cours "Méthode de Conception" à l'Université de Caen Normandie consiste en la création d'un jeu où le joueur doit assembler différentes formes pour occuper le moins d'espace possible, avec une architecture modulaire et l'utilisation de plusieurs design patterns.
Principe du jeu : Inspiré du Tetris, le joueur doit assembler des pièces en les faisant pivoter et déplacer pour minimiser l'espace occupé. Le score est calculé en fonction du plus petit rectangle englobant toutes les pièces. 

## Générer librairie piecepuzzles
- cd piecespuzzles && ant dist

## Lancer le jeu assemblage gui
- cd jeuAssemblage && ant run

## Lancer tests piecespuzzles
- cd piecespuzzles && ant test

## Lancer tests jeu assemblage
- cd jeuAssemblage && ant test

## Jeu console
jeuAssemblage/src/views/console/Demo
