
## Projet A31 “Kingdomino”



1.  ### Introduction
2.  ### Choix de conception


## 1.  Introduction

L'objectif de ce projet est de concevoir une application permettant de jouer au jeu de société Kingdomino.

## 2. Choix de conception

**Séance du 01/12/2021**

La contrainte principale est de respecter une structure MVC. Donc nous avons un package model, controller, view.

Nous avons différentes classe dans le package model :

-   Graph : representation de notre grille
-   Dominoes : représentation de la tuile qu’on pourra pas placer sur le graphe. Il sera initialisé à l’aide d’un CSV.
-   King : représente la case de départ du joueur.
-   GameMode : classe qui permet d'exécuter une stratégie.
-   GameModeStrategy : interface de choisir une stratégie
-   Le package model comprend différent classes :
    -   Dynastie
    -   Empire du milieu
    -   Harmoni
    -   Le Grand Duel

Nous avons décidé d’utiliser le paterne Strategy concernant les différentes règles de jeux additionnelles qui sont : Dynastie, Empire du milieu, Harmonie et Le Grand Duel, le tout dans un package mode.