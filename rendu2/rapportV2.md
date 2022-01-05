
# Projet A31 - 2e Rapport Kingdomino

# Sommaire
1. ### Introduction
2. ### Choix de conception
   #### a. Contraintes
   #### b. Structure
   #### c. Package `Game`
   #### d. Package `View`
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

### c. Package `Game`
- Par rapport au premier rendu nous avons enlever le pattern Singleton dans la classe `Game` car il n'y avait aucune utilité.

### d. Package `View`
- Pour obtenir des informations du modèle, on préfèrera y accéder directement sans passer par la classe `Controller`. 
- Notre classe `Window` n'implèmente plus notre interface `Observer` car il était inutile qu'elle ai les méthodes de cette interface vu que c'est juste une classe qui permet l'instantiation du JFrame et des propriétés de la fenetre. 
- Dans une prochaine version nous aurions voulu re-factoriser la classe `KingdominoGame` en plusieurs sous classes mais il était trop comtraignant de le réaliser dû à tous nos attributs de cette classe.

## 3. L'exécutable
### Écran d'accueil
1. Premièrement, quand nous lançons l'exécutable, nous sommes amenés sur une petite fenêtre, permettant de choisir le mode de jeu (qui n'est pas obligatoire) et de sélectionner le nombre de joueurs. Une fois que l'utilisateur clique sur le bouton Start, il sera amené sur une nouvelle fenêtre, qui lui permettra de joueur au jeu.
    ![image](https://imgur.com/5SSojbq.png)

### Écran en partie
2. Après avoir choisi le mode de jeu et le nombre de joueurs (ici 4), cette fenêtre de jeu permet d'assurer la totalité d'une partie.\
   ![image](https://imgur.com/uy7Tjw6.png)

- Tout d'abord, chacun des joueurs doit placer leur château sur leur graphe. Par ailleurs, chacun des joueurs peut modifier leur nom de joueur en cliquant, tout simplement, sur leur nom.
![image](https://imgur.com/uy7Tjw6.png)

- Ensuite, avant de pouvoir choisir leur domino, la personne doit montrer les dominos.
  ![image](https://imgur.com/3K96Woy.png) 
- Chacun des joueurs doit choisir leur domino, tout en ne prenant pas le même qu'un autre joueur.
  ![image](https://imgur.com/MZV1ybP.png) 
- Quand un joueur clique pour sélectionner son domino, un petit carré s'affiche avec la couleur du Roi (Dans une prochaine version, au lieu d'afficher simplement une couleur, nous implémenterons une image avec le Roi du joueur en question dans chacun des petits carrés).

### Fin de partie
![image](https://imgur.com/Y60uKcQ.png)


## 4. Annexe
- Si en voulant tester le Swing sur un ordinateur Linux, l'affichage graphique ne s'affiche pas bien, il suffit d'aller dans la classe `Window` et d'aller à la ligne 33 et enlever la ligne `frame.setLocationRelativeTo(null);` (cela permet d'avoir la fenêtre d'affiché au mileu de l'écran de l'ordinateur)
- Si il y a un problème lors du lancement du JAR, veuillez installer sur le site [Azul](https://www.azul.com/downloads/?package=jdk) une version supérieur à Java 16. Il faudra aussi avoir d'installé [Java](https://www.java.com/fr/download/manual.jsp).
