
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
- Nous avons voulu bien ordonnée notre projet, alors nous avons créé différent package dans `launcher` (contiens le Main),`model`, `controller`, `view`. Voici une représentation de la structure des packages contenant nos classes

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
    ├── KingDominoGame.java
    ├── KingDominoStart.java
    └── Window.java

```

### c. Package `Set`


### d. Package `Game`
- Par rapport au premier rendu nous avons enlever le pattern Singleton car il n'y avait aucune utilité.

### e. Package `View`
- Pour gérer les différentes vues, nous avons la classe `Window` qui nous permet de gérer les différentes vues.
- Pour obtenir des infos du modèle, on préfèrera y accéder directement sans passer par le controlleur. 


## 3. L'exécutable
### Écran d'accueil
![image](https://imgur.com/5SSojbq.png)
1. Premièrement, quand nous lançons l'exécutable, nous sommes amenés sur une petite fenêtre, permettant de choisir le mode de jeu (qui n'est pas obligatoire) et de sélectionner le nombre de joueurs. Une fois que l'utilisateur clique sur le bouton Start, il sera amené sur une nouvelle fenêtre, qui lui permettra de joueur au jeu Kingdomino.
### Écran en partie
![image](https://imgur.com/uy7Tjw6.png)

2. Après avoir choisi le mode de jeu et le nombre de joueurs (ici 4), cette fenêtre de jeu permet d'assurer la totalité d'une partie.\
- Tout d'abord, chacun des joueurs doit placer leur château sur leur graphe. Par ailleurs, chacun des joueurs peut modifier leur nom de joueur en cliquant, tout simplement, sur leur nom.\
![image](https://imgur.com/uy7Tjw6.png)

- Ensuite, avant de pouvoir choisir leur domino, la personne doit montrer les dominos.\
  ![image](https://imgur.com/3K96Woy.png) 
- Chacun des joueurs doit choisir leur domino, tout en ne prenant pas le même qu'un autre joueur.\
  ![image](https://imgur.com/MZV1ybP.png) 
- Quand un joueur clique pour sélectionner son domino, un petit carré s'affiche avec la couleur du Roi (Dans une prochaine version, au lieu d'afficher simplement une couleur, nous implémenterons une image avec le Roi du joueur en question dans chacun des petits carrés).\

[![IMAGE_ALT](https://img.youtube.com/vi/8-EZ7INDJqg/0.jpg)](https://www.youtube.com/watch?v=8-EZ7INDJqg)
(vidéo youtube)

### Fin de partie
![image](https://imgur.com/Y60uKcQ.png)

## 4. Annexe
- Si en voulant tester le Swing, l'affichage graphique ne s'affiche pas bien, il suffit d'aller dans la classe `Window` et d'aller à la ligne 30 et enlever la ligne `frame.setLocationRelativeTo(null);`
- Si un problème lors du lancement du JAR, veuillez installer sur le site [link](https://www.azul.com/downloads/?package=jdk) en choissant une version supérieur à Java 16. 
