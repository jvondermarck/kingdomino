# Projet A31 - Mode emploi

# Sommaire
1. ### Introduction
2. ### Procédure d'installation du projet
3. ### Procédure d'installation de l'exécutable
4. ### L'exécutable
5. ### Membres de l'équipe

_____

## 1. Introduction 
### Projet
L'objectif de ce projet est de concevoir une application permettant de jouer au jeu de société Kingdomino.

Ce projet à était réalisé à l'aide du langage `Java`.
JDK utilisé : [`Azul Zulu OpenJDK 16`](https://www.azul.com/downloads/)).

### Nos rapports
-  [Rapport V1](https://git.unistra.fr/vondermarck-yanovskyy/a31-kingdomino/-/blob/master/rendu1/rapportV1.md)
-  [Rapport V2](rapportV2.md)

## 2. Procédure d'installation du projet
Si vous voulez exécuter le code présent dans le repertoire ``rendu1``
veuillez ouvrir ce repertoire directement avec IntelJ. Sinon cet erreur peu apparaitre.
```
Erreur : impossible de trouver ou de charger la classe principale launcher.LauncherKingdominoRendu1
Causé par : java.lang.ClassNotFoundException: launcher.LauncherKingdominoRendu1
```

> Si en voulant tester le Swing sur un ordinateur Linux, l'affichage graphique ne s'affiche pas bien, il suffit d'aller dans la classe `Window` et d'aller à la ligne 33 et enlever la ligne `frame.setLocationRelativeTo(null);` (cela permet d'avoir la fenêtre d'affiché au mileu de l'écran de l'ordinateur)

## 3. Procédure d'installation de l'exécutable

| Windows  | Linux & Mac OS |
|:-:|:-:|
| Télécharger le jeu <a href="https://git.unistra.fr/vondermarck-yanovskyy/a31-kingdomino/-/blob/master/rendu2/a31-kingdomino_V2.jar?inline=false">ici</a>, exécuter, et voilà ! | Télécharger le .jar <a href="https://git.unistra.fr/vondermarck-yanovskyy/a31-kingdomino/-/blob/master/rendu2/a31-kingdomino_V2.jar?inline=false">ici</a>, ouvrer un terminal et diriger vous vers le dossier ou vous avez sauvegarder l'exécutable, puis taper : <br />  `$ java -jar a31-kingdomino_V2.jar.jar` |

> Si vous avez un soucis lors de l'éxécution du fichier `.jar`, nous vous invitons à suivre les instructions pour mettre à jour Java  [ici.](https://www.java.com/fr/download/)
> Et de faire en sorte d'avoir installé [`Azul Zulu OpenJDK` (https://www.azul.com/downloads/) (version supérieur à 16).

## 4. L'exécutable
### Écran d'accueil
1. Premièrement, quand nous lançons l'exécutable, nous sommes amenés sur une petite fenêtre, permettant de choisir le mode de jeu (qui n'est pas obligatoire) et de sélectionner le nombre de joueurs. Une fois que l'utilisateur clique sur le bouton Start, il sera amené sur une nouvelle fenêtre, qui lui permettra de joueur au jeu.
   ![image](https://imgur.com/5SSojbq.png)

### Écran en partie
2. Après avoir choisi le mode de jeu et le nombre de joueurs (ici 4), cette fenêtre de jeu permet d'assurer la totalité d'une partie.
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

### Vidéo
Voici une vidéo ou une partie est jouer.
[![IMAGE_ALT](https://img.youtube.com/vi/DUabSjffzgM/0.jpg)](https://www.youtube.com/watch?v=DUabSjffzgM)
(vidéo youtube)

## 5. Membres de l'équipe
- Julien Von Der Marck
- Alexander Yanovskyy