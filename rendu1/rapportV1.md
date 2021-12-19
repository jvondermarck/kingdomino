# Projet A31 - Rapport Kingdomino”

# Sommaire 
1.  ### Introduction
2.  ### Choix de conception


## 1.  Introduction

- L'objectif de ce projet est de concevoir une application permettant de jouer au jeu de société Kingdomino.
- Si en clonant notre projet, en compilant vous avez des erreurs, il faut cliquer droit sur les dossiers :`font`, `docs`, `img`, et `out` ->"Make directory as" -> "Ressources root".  

## 2. Choix de conception

- La contrainte principale est de respecter une structure MVC. Donc nous avons un package `model`, `controller`, `view`.
- Nous avons voulue bien ordonnée notre projet, alors nous avons créé différent package dans `model`, `controller`, `view`. Voici une représentation de la structure des packages contenant nos classe

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

- Dans le package `Set`, concernant la classe `GameMode` nous avons hésitez à choisir entre le pattern Stratégie et Template method, mais nous avons décider d'utiliser le pattern Template method 
  car le joueur pourra selectionner plusieurs mode de jeu en pour une partie, donc une stratégie ne semble pas efficace pour ce type de choix car cela nous restreindrai à un unique mode de jeu.
- La stratégie concernant NumberPlayer permet de choisir le nombre de joueurs donc soit 2, 3 ou 4. 
- Une pure invention (GRASP) nous permet d'isoler la classe `CSVReader` (qui permet de lire et stocker les données) et `FontUtilities` (permet d'importer des polices d'écriture personalisé au format .ogg, .tff pour pouvoir les utilisées en JavaSwing ...), cela permet de diminuer la cohésion et couplage
- On utilise le pattern Singleton sur la classe `Game` afin de pouvoir créé qu'une seul instance de notre partie (cela n'est pas très intéressant dans notre cas car nous devons respecter la structure MVC, mais appliquer le pattern Singleton nous a sembler important de l'appliquer ici car la représentation d'une seul partie en cours nous semble importante.)
- Nous avons créer une `GameModeFactory` qui nous permet de choisir les différents mode de jeu. Nous avons une classe abstraite `GameMode` et plusieurs classes concrètes, ce qui fait 
 qu'une Factory soit très utile quand l'utilisateur choisira sur l'interface graphique le ou les modes de jeu qui souhaite.
- Pour gérer les différentes vue, nous avons la classe `Window` qui nous permet de gérer les différentes vue. Elle instancie un seul JFrame, et les autres vues reprennent cette instance pour créer leur nouvelle fenetre. La classe `Window` a aussi toutes les instances du Model (`Game.java`) et du Controller (`Controller.java`). Elle stock ces attributs en protected afin que tout le package `Vue` puisse accéder à ces variables (cela evite de faire des méthodes get).        


    
    
