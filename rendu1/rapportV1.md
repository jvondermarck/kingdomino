
# Projet A31 - Rapport Kingdomino

# Sommaire
1. ### Introduction
2. ### Choix de conception
   #### a. Contraintes
   #### b.  Structure
   #### c. Package `Set`
   #### d. Package `Game`
   #### e. Package `View`
   #### f. Autres
3. ### L'exécutable
4. ### Annexe

## 1.  Introduction

L'objectif de ce projet est de concevoir une application permettant de jouer au jeu de société Kingdomino.

## 2. Choix de conception

### a. Contraintes
- La contrainte principale est de respecter une structure MVC. Donc nous avons un package `model`, `controller`, `view`.
### b. Structure
- Nous avons voulu bien ordonnée notre projet, alors nous avons créé différent package dans `launcher` (contient le Main),`model`, `controller`, `view`. Voici une représentation de la structure des packages contenant nos classes

```
....

├── controller
│ └── Controller.java
├── launcher
│ └── LauncherKingdomino.java
├── model
│ ├── entities
│ │ ├── Castle.java
│ │ ├── Deck.java
│ │ ├── Domino.java
│ │ ├── Graph.java
│ │ ├── KINGCOLOR.java
│ │ ├── King.java
│ │ ├── Player.java
│ │ └── Tile.java
│ ├── Game.java
│ ├── Observer.java
│ └── set
│     ├── GameModeFactory.java
│     ├── GameMode.java
│     ├── mode
│     │ ├── Harmony.java
│     │ ├── MiddleKingdom.java
│     │ └── MightyDuel.java
│     ├── number
│     │ ├── Duo.java
│     │ ├── Quatro.java
│     │ └── Trio.java
│     ├── NumberPlayer.java
│     └── NumberPlayerStrategy.java
├── utilities
│ └── CSVReader.java
│ └── FontUtilities.java
└── view
    ├── KingDominoEnd.java
    ├── KingDominoGame.java
    ├── KingDominoStart.java
    └── Window.java

```

### c. Package `Set`
- Pour la classe `GameMode` nous avons hésité à choisir entre le Pattern Stratégie et Template Method, mais nous avons décidé d'utiliser le Pattern Template Method, car le joueur pourra sélectionner plusieurs modes de jeu en pour une partie, donc une stratégie ne semble pas efficace pour ce type de choix, car cela nous restreindrait à un unique mode de jeu.

- La stratégie concernant NumberPlayer permet de choisir le nombre de joueurs donc soit 2, 3 ou 4.
### d. Package `Utilities`
- Une pure invention (GRASP) nous permet d'isoler la classe `CSVReader` (qui permet de lire et stocker les données) et `FontUtilities` (permet d'importer des polices d'écriture personnalisée au format .ogg, .tff pour pouvoir les utiliser en JavaSwing ...), cela permet de diminuer la cohésion et couplage

### d. Package `Game`
- On utilise le Pattern Singleton sur la classe `Game` afin de pouvoir créer qu'une seule instance de notre partie (cela n'est pas très intéressant dans notre cas, car nous devons respecter la structure MVC, mais appliquer le Pattern Singleton nous à sembler important de l'appliquer ici, car la représentation d'une seule partie en cours nous semble importante.)

- Nous avons créé une `GameModeFactory` qui nous permet de choisir les différents modes de jeu. Nous avons une classe abstraite `GameMode` et plusieurs classes concrètes, ce qui fait
  qu'une Factory soit très utile quand l'utilisateur choisira sur l'interface graphique le ou les modes de jeu qui souhaite.

- Dans le Package `entities`, nous avons la classe `Castle` qui extends la classe `Tile` parce que nous voulions représenter notre château comme une tuile plaçable sur le `Graph`d'un `Player`, différente méthode aurais pu être utilisé comme l'implémentation d'une interface `Posable` par exemple qui permettrai aux classes qui implémentent cette méthode d'être posable, mais le premier choix nous paraissait plus pratique.

### e. Package `View`
- Pour gérer les différentes vues, nous avons la classe `Window` qui nous permet de gérer les différentes vues.

### f. Autres
- Nous avons modifié le csv fournit, en effet nous avons seulement modifié les couleurs (Ex : dark green -> couleur en hex), nous avons choisie de faire ce choix, car cela nous permet facilement de changer, mais aussi d'appliquer ses couleurs là en Swing. (Cela évite l'over engineering)


## 3. L'exécutable
### Ecran d'accueil
![image](https://i.imgur.com/eJz7y6o.png)
1. Premièrement, quand nous lancons l'exécutable, nous sommes amenés sur une petite fenêtre, permettant de choisir le mode de jeu (qui n'est pas obligatoire) et de sélectionner le nombre de joueurs. Une fois que l'utilisateur clique sur le bouton Start, il sera amené sur une nouvelle fenêtre, qui lui permettra de joueur au jeu Kingdomino.
### Ecran en partie
![image](https://i.imgur.com/qUoIDui.png)

2. Après avoir choisi le mode de jeu et le nombre de joueurs (ici 4), cette fenêtre de jeu permet d'assurer la totalité d'une partie.\
![image](https://i.imgur.com/UuEB7PT.png)
- Tout d'abord, chacun des joueurs doivent placer leur chateau sur leur graphe. Par ailleurs, chacun des joueurs peut modifier leur nom de joueur en cliquant, tout simplement, sur leur nom.\
  ![image](https://i.imgur.com/HsvpiRx.png)
- Ensuite, avant de pouvoir choisir leur domino, la personne doit montrer les dominos.\
  ![image](https://i.imgur.com/oj6gSPP.png) 
- Chacun des joueurs doivent choisir leur domino, tout en ne prenant pas le meme qu'un autre joueur.\
  ![image](https://i.imgur.com/w8GzzFc.png) 
- Quand un joueur clique pour sélectionner son domino, un petit carré s'affiche avec la couleur du Roi (Dans une prochaine version, au lieu d'afficher simplement une couleur, nous implémenterons une image avec le Roi du joueur en question dans chacun des petits carrés).\

[![IMAGE_ALT](https://img.youtube.com/vi/8-EZ7INDJqg/0.jpg)](https://www.youtube.com/watch?v=8-EZ7INDJqg)

- Le joueur, en cliquant sur son domino, obtient une petite zone sur l'interface de jeu, pour pouvoir faire une rotation de son domino et/ou inverser son sens, avant de le placer sur son graph.
- Une fois la sélection des dominos réalisés par l'ensemble des joueurs, l'ordre pour chaque joueur de jouer marche de la facon suivante (pour mettre leur domino sur leur propre graphe) : le joueur dont le roi est placé sur le 1er domino de la ligne commence, et ainsi de suite pour les autres joueurs. 
- Nous n'avons pas encore implémenté graphiquement la mise en place du domino sur le graphe, mais nous avons juste implémenté une phase de test permettant de cliquer aléatoirement sur le graphe de chaque joueur, ainsi de suite. (Il suffira de regarder les indications que donne le jeu, en haut à gauche de l'écran, pour savoir quel joueur doit faire quoi).
- Une fois que tous les joueurs ont cliqué sur leur graphe (dans une prochaine version il sera possible de mettre leur domino sur leur graphe), un nouveau tour commence : de nouveaux dominos sont mis en jeu et les joueurs recommencent à choisir leur domino. (Le joueur ayant sélectionné le 1er domino de la ligne à la partie précédente, commencera à choisir son domino en premier pour cette nouvelle partie, et ainsi de suite pour les autres joueurs).

## 4. Annexe
- Si en voulant tester le Swing, l'affichage graphique ne s'affiche pas bien, il suffit d'aller dans la classe `Window` et d'aller à la ligne 30 et enlever la ligne `frame.setLocationRelativeTo(null);`
