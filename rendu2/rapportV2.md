
# Projet A31 - 2e Rapport Kingdomino

# Sommaire
1. ### Introduction
2. ### Ajustements
   #### a. Package `Model`
   #### b. Package `View`
3. ### Améliorations possibles
4. ### L'exécutable
5. ### Annexe

## 1.  Introduction

- Dans le premier rapport, nous avons expliqué nos choix de conceptions et donner un aperçu de la première version de l'application.
  - Si vous n'avez pas lu le ![premier rapport](https://git.unistra.fr/vondermarck-yanovskyy/a31-kingdomino/-/blob/master/rendu1/rapportV1.md), nous vous invitons vivement à le lire.
- Ce rapport est là pour évoquer les ajustements et les améliorations possibles qui aurait pu être réalisé, et pour finir nous montrons un aperçu de l'application terminé.


## 2. Ajustements

### a. Package`Model`
- Par rapport au premier rendu, nous avons enlevé le pattern Singleton dans la classe `Game` car cela nous restreint au niveau des possibilités d'évolutions par la suite.
  (Ex : pouvoir jouer plusieurs parties à la fin)
- Nous avons renommé la classe `Graph`, en `Kingdom`. En effet, le mot ``Graph`` fait confusion, car cela fait allusion instinctivement à la théorie des graphes pour les personnes qui n'ont pas étais impliqué à la conception de l'application.

### b. Package `View`
- Pour obtenir une ou plusieurs informations provenant du modèle, nous passons au paravent par le Controller. Mais le modèle MVC nous permet d'accéder (consulter) directement les données du modèle. Alors nous accédons directement dans le modèle et non plus par le Controller.
- Notre classe `Window` n'implémente plus notre interface `Observer` car cela n'a aucun intérêt qu'elle implémenter cette interface. En effet, la classe Window à pour utilité d'être un tampon utilitaire pour les différentes vues (KingDominoStart & KingDominoGame), elle n'est pas vue, mais contribue au fonctionnement des vues.
- Nous avons supprimé toutes les variables `protected` dans la classe `Window` car c'est un manque d'encapsulation. En effet, il est mieux de mettre les variables en private et permettre aux autres classes d'accéder aux variables en faisant des méthodes get/set dans la classe `Window`.
- Nous avons supprimé la classe "KingDominoEnd" car elle nous servait à rien.

## 3. Améliorations possibles
- Dans une prochaine version nous aurions voulu re-factoriser la classe `KingdominoGame` en plusieurs sous classes, mais il était trop contraignant de le réaliser dû à tous nos attributs de cette classe `KingdominoGame` à devoir accéder pour pouvoir exécuter ces nouvelles sous-classes, et à notre temps restant avant le rendu final.
    - Nous pouvons mettre dans ses sous classes une classe qui permettrait de gérer les royaumes de chacun des joueurs, que se soit de la création de chaque Royaume jusqu'à l'interaction des joueurs lors de clics sur leur royaume.
    - On peut avoir une autre sous-classe permettant de gérer l'interface des scores à la fin de la partie. 
- Nous aurions pu aussi créer des sous-classes pour la classe `Game`. En effet, dans notre projet s'est une classe très importante qui permet d'orchestrer le déroulement d'une partie. Cette classe collabore principalement avec deux sous-packages qui sont `Set` et `Entities`, nous pensons que créer deux sous-classes aurait été intéressant pour éviter que cette classe ne gère trop de méthodes 

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
## 5. Annexe
- Si en voulant tester le Swing sur un ordinateur Linux, l'affichage graphique ne s'affiche pas bien, il suffit d'aller dans la classe `Window` et d'aller à la ligne 33 et enlever la ligne `frame.setLocationRelativeTo(null);` (cela permet d'avoir la fenêtre d'affiché au mileu de l'écran de l'ordinateur)
- S'il y a un problème lors du lancement du JAR, veuillez installer sur le site [Azul](https://www.azul.com/downloads/?package=jdk) une version supérieur à Java 16. Il faudra aussi avoir d'installé [Java](https://www.java.com/fr/download/manual.jsp).
