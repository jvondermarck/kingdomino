# Projet A31 - 2e Rapport Kingdomino
__________
# Sommaire
1. ### Introduction
2. ### Ajustements
   #### a. Package `Model`
   #### b. Package `View`
3. ### Calcule des scores
4. ### Améliorations possibles
__________

## 1.  Introduction

- Dans le premier rapport, nous avons expliqué nos choix de conceptions et donner un aperçu de la première version de l'application.
    - Si vous n'avez pas lu le [premier rapport](https://git.unistra.fr/vondermarck-yanovskyy/a31-kingdomino/-/blob/master/rendu1/rapportV1.md), nous vous invitons vivement à le lire.
- Ce rapport est là pour évoquer les ajustements et les améliorations possibles qui aurait pu être réalisé, et pour finir nous montrons un aperçu de l'application terminé.
- Veuillez trouver notre mode emploi [ici](https://git.unistra.fr/vondermarck-yanovskyy/a31-kingdomino/-/blob/master/rendu2/README.md).

## 2. Ajustements

### a. Package`Model`
- Par rapport au premier rendu, nous avons enlevé le pattern Singleton dans la classe `Game` car cela nous restreint au niveau des possibilités d'évolutions par la suite.
  (Ex : pouvoir jouer plusieurs parties)
- Nous avons renommé la classe `Graph`, en `Kingdom`. En effet, le mot ``Graph`` fait confusion, car cela fait allusion instinctivement à la théorie des graphes pour les personnes qui n'ont pas étais impliqué à la conception de l'application.

### b. Package `View`
- Pour obtenir une ou plusieurs informations provenant du modèle, nous passons au paravent par le Controller. Mais le modèle MVC nous permet d'accéder (consulter) directement les données du modèle. Alors nous accédons directement dans le modèle et non plus par le Controller.
- Notre classe `Window` n'implémente plus notre interface `Observer` car cela n'a aucun intérêt qu'elle implémenter cette interface. En effet, la classe Window à pour utilité d'être un tampon utilitaire pour les différentes vues (KingDominoStart & KingDominoGame), elle n'est pas vue, mais contribue au fonctionnement des vues.
- Nous avons supprimé toutes les variables `protected` dans la classe `Window` car c'est un manque d'encapsulation. En effet, il est mieux de mettre les variables en private et permettre aux autres classes d'accéder aux variables en faisant des méthodes get/set dans la classe `Window`.
- Nous avons supprimé la classe "KingDominoEnd" car elle nous servait à rien.

## 3. Calcule des scores
En fin de partie, la classe ``Game`` s'occupe de calculer les scores de chacun joueur.

La classe ``Kingdom`` à une fonction qui permet de calculer la taille d'un domaine en fonction de son type donné en paramètre (la couleur en hex) appelé ``getSizeOfADomain`` utilisant les fonctions privées ``countCells`` et ``countCrowns`` (méthode Depth First Traversal utilisé pour parcourir le tableau contenant nos tuiles). 
Cette fonction retourne une liste de liste contenant deux entiers représentant : la taille du domaine et le nombre de couronnes.
(Ils peut avoir plusieurs domaine de taille différente, c'est pour ça que nous renvoyons une liste de liste d'entier.)

Cette fonction sera utilisée dans la classe ``Game`` via la méthode ``calculateScores``, qui vas parcourir la liste ayant tous les joueurs. Chaque joueur à une liste
pour chaque domaine possible, alors cela vas remplir chaque liste des domaines représentative.


```
    public void calculateScores(){
        for(Player p : _listPlayers){
            p.setYellowTilesScoreList(p.getKingdom().getSizeOfADomain("#FDCA40")); // YELLOW TILE
            p.setDarkGreenTilesScoreList(p.getKingdom().getSizeOfADomain("#0A9396")); // DARK GREEN TILE
            p.setBlueTilesScoreList(p.getKingdom().getSizeOfADomain("#2176FF")); // BLUE TILE
            p.setBlackTilesScoreList(p.getKingdom().getSizeOfADomain("#31393C")); // BLACK TILE
            p.setBrownTilesScoreList(p.getKingdom().getSizeOfADomain("#7F4F24")); // BROWN TILE
            p.setLightGreenTilesScoreList(p.getKingdom().getSizeOfADomain("#B5E48C")); // LIGHT GREEN TILE
            
            .....
        }
    }
 ```

Ensuite dans la même boucle, on va parcourir la liste contenant les modes de jeu choisie pour appliquer les points bonus ou non.


 ```   
            .......
             for(GameMode g : _listGameMode){
                if(g.executeGameMode(p)){
                    p.addBonus(g.numberBonus());
                }
            }
            .....
 ```

Pour enfin finir sur l'addition de tous les points.
```
            .....
            p.calculateTotalScore();
```

## 4. Améliorations possibles
- Dans une prochaine version nous aurions voulu re-factoriser la classe `KingdominoGame` en plusieurs sous classes, mais il était trop contraignant de le réaliser dû à tous nos attributs de cette classe `KingdominoGame` à devoir accéder pour pouvoir exécuter ces nouvelles sous-classes, et à notre temps restant avant le rendu final.
    - Nous pouvons mettre dans ses sous classes une classe qui permettrait de gérer les royaumes de chacun des joueurs, que se soit de la création de chaque Royaume jusqu'à l'interaction des joueurs lors de clics sur leur royaume.
    - On peut avoir une autre sous-classe permettant de gérer l'interface des scores à la fin de la partie.
- Nous aurions pu aussi créer des sous-classes pour la classe `Game`. En effet, dans notre projet s'est une classe très importante qui permet d'orchestrer le déroulement d'une partie. Cette classe collabore principalement avec deux sous-packages qui sont `Set` et `Entities`, nous pensons que créer deux sous-classes aurait été intéressant pour éviter que cette classe ne gère trop de méthodes.