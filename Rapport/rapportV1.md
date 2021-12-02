# Projet A31 - Rapport Kingdomino”

# Sommaire 
1.  ### Introduction
2.  ### Choix de conception


## 1.  Introduction

L'objectif de ce projet est de concevoir une application permettant de jouer au jeu de société Kingdomino.

## 2. Choix de conception

La contrainte principale est de respecter une structure MVC. Donc nous avons un package model, controller, view.

###Nous avons différentes classe et package dans le package `model` :
- `Game` : representation de la partie courante, qui suit le pattern Singleton. Ce qui vas permettre d'avoir une unique instance pour une partie.

Dans le package `entities` : 
-   `Graph` : representation la grille d'un joueur 
-   `Deck` : classe qui permet de faire notre paquet de domino
-   `KINGCOLOR` : enumération qui vas permettre de représenter la couleur du roi
-   `Dominoes` : représentation de la tuile qu’on pourra pas placer sur le graphe. Il sera initialisé à l’aide d’un CSV.
-   `King` : représente la case de départ du joueur.
-   `Player` : (plus explicite je peux pas faire)

###Dans le package `set` :
-   `GameMode` : vas permettre d'executer une stratégie (en suivant le patterne Strategy)
-   `GameModeStrategy` : interface qui vas permettre de choisir une stratégie concernant le mode de jeu 
-   Le package `Mode` comprend différent classes qui vont toutes implémenter la classe `GameModeStrategy` :
    -   ̀`Dynasty`
    -   `MiddleGame`
    -   `Harmony`
    -   `MightyDuel`
-   `NumberPlayer` : une classe qui va permettre de gérer la stratégie concernant le nombre de joueurs    
-   `NumberPlayerStrategy` : l'interface de la stratégie concernant le nombre de joueurs
-   Le package `Number`
    - `Duo` : qui permettre de lancer une partie avec deux joueurs avec des règles spécifiques
    - `AboveTwo` : qui permettra de lancer le jeu avec 3 ou 4 joueurs avec des règles spécifique
    
###Dans le package `View` :

- `Window` : une classe qui va permettre de gérer l'interface graphique en Swing
- `KingDominoStart` : l'interface graphique fait en Swing qui va permettre de préparer le jeu (choix du mode de jeu, du nombre de joueurs)
- `KingDominoGame` : l'interface graphique qui va permettre aux joueurs de jouer au jeu 
- `KingDominoEnd` : l'interface graphique où sera affiché le gagnant et les scores

Nous avons décidé d’utiliser le paterne Strategy concernant les différentes règles de jeux additionnelles qui sont : Dynastie, Empire du milieu, Harmonie et Le Grand Duel, le tout dans un package mode.


    
    
