<h1 align="center"><img src="https://git.unistra.fr/vondermarck-yanovskyy/a31-kingdomino/-/raw/master/img/background.jpg" width="400px"/><br/>
  Kingdomino
</h1>
<p align="center">In Kingdomino, you are a lord seeking new lands in which to expand your kingdom. <br> You must explore all the lands, including wheat fields, lakes, and mountains, in order to spot the best plots, while competing with other lords to acquire them first.</p>

<div align="center">
  <img align="center" src="https://img.shields.io/badge/Open%20JDK-16?style=for-the-badge">
 <img >
</div>

# Table of contents

- ### Introduction
- ### Procedure for installing the project locally
- ### Procedure for installing the executable
- ### The executable
- ### Team members

---

## Introduction

### Project

The objective of this project is to design an application to play the board game Kingdomino.

- This project was realized using the [`Java language.`](https://www.java.com/fr/download/manual.jsp/)
- JDK used: [`Azul Zulu OpenJDK v16`](https://www.azul.com/downloads/).

### Our reports

- [Report V1](render1/reportV1.md)
- [Report V2](render2/reportV2.md)

### Rules of the game and specifications

- [Rules & Specifications](Rules.md)

## 2. Procedure for installing the project locally

- To install our project, **clone** our repository, and in an empty directory, type the following command: `git clone https://git.unistra.fr/vondermarck-yanovskyy/a31-kingdomino.git`

### Possible problems encountered

#### Execution problem

> If you want to execute the code in the directory `rendu1` please open this directory directly with IntelJ. Otherwise this error may appear.

```
Error: Could not find or load main class launcher.LauncherKingdominoRendu1
Causé par : java.lang.ClassNotFoundException: launcher.LauncherKingdominoRendu1
```

#### Problème d'affichage de l'interface graphique

> If when testing Swing on a Linux computer, the GUI does not display well, simply go to the `Window` class and go to line 33 and remove the `frame.setLocationRelativeTo(null);` line (this allows the window to be displayed in the middle of the computer screen)

## 3. Procedure for installing the executable

|                                                                                       Windows                                                                                        |                                                                                                                                      Linux & Mac OS                                                                                                                                      |
| :----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| Download the game <a href="https://git.unistra.fr/vondermarck-yanovskyy/a31-kingdomino/-/blob/master/rendu2/a31-kingdomino_V2.jar?inline=false">here</a>, execute, and here you go ! | Download the .jar <a href="https://git.unistra.fr/vondermarck-yanovskyy/a31-kingdomino/-/blob/master/rendu2/a31-kingdomino_V2.jar?inline=false">here</a>, open a terminal and go to the folder where you saved the executable, then type: <br /> `$ java -jar a31-kingdomino_V2.jar.jar` |

> If you have problems running the `.jar` file, please follow the instructions for updating Java [here](https://www.java.com/fr/download/) And make sure you have installed [`Azul Zulu OpenJDK`](https://www.azul.com/downloads/) (version higher than 16).

## 4. The executable

### Home screen

1. First, when we launch the executable, we are brought to a small window, allowing us to choose the game mode (which is not mandatory) and to select the number of players. Once the user clicks on the Start button, he will be taken to a new window, which will allow him to play the game.
   ![image](https://imgur.com/5SSojbq.png)

### Screen in part

2. After choosing the game mode and the number of players (here 4), this game window allows you to play the whole game.
   ![image](https://imgur.com/uy7Tjw6.png)

- First of all, each player must place their castle on their kingdom. In addition, each player can change their player name by simply clicking on their name.
  ![image](https://imgur.com/uy7Tjw6.png)
- Then, before they can choose their domino, the person must show the dominoes.
  ![image](https://imgur.com/3K96Woy.png)
- Each player must choose their domino, but not take the same one as another player.
  ![image](https://imgur.com/MZV1ybP.png)
- When a player clicks to select his domino, a small square is displayed with the colour of the King (In a future version, instead of just displaying a colour, we will implement an image with the King of the player in question in each of the small squares).

### End of game

![image](https://imgur.com/Y60uKcQ.png)

### Video

Here is a video where a game is played. (youtube video)

[![IMAGE_ALT](https://img.youtube.com/vi/DUabSjffzgM/0.jpg)](https://www.youtube.com/watch?v=DUabSjffzgM)

### Explanation of our domino placement buttons

- First of all, when you leave the mouse on one of the 4 buttons for a few seconds, you will see a small information text.

#### The rotation buttons

- ![IMAGE_ALT](https://imgur.com/0MRfkq3.jpg) : The purpose of this button is to move the domino in either the vertical or horizontal direction.
- ![IMAGE_ALT](https://imgur.com/aENnmLV.jpg) :
  This button reverses the domino. The left tile will be placed on the right, and vice versa.

#### The placement of the domino

- If the button is horizontal, there will be a button with a letter "L" (Left) and "R" (Right).
- If the button is vertical, there will be a button with a letter "U" (Up) and "D" (Down).
  > The purpose of these two buttons, depending on whether the domino is vertical or horizontal, is to place the domino in the direction the player wants.
  > In fact, the tile that will be at the top left (on the left screen, above the 4 buttons), will be the tile placed where the player will click on his kingdom.
  > By choosing whether he wants his other tile to the left, right, top or bottom, his entire domino will be placed in his kingdom.

#### Example

![IMAGE_ALT](https://imgur.com/0xk88X3.jpg)

- The player clicks on the purple arrow. You can see that the yellow tile, which is at the top left of the preview screen, is where the player clicks.
- The player has selected the "L" (left) button, so the other tile of the domino, which is horizontally (the green tile), will be to the left of the yellow tile.

## 5. Contributing

- Julien Von Der Marck
- Alexander Yanovskyy

<table>
  <tr>
    <td align="center"><a href="https://github.com/jvondermarck"><img src="https://avatars.githubusercontent.com/u/62793491?v=4" width="100px;" alt=""/><br /><sub><b>Julien Von Der Marck</b></sub></a><br /><a href="https://github.com/creativecommons/project_creativecommons.org/commits?author=Akpjunior94" title="Code">💻</a></td>
    <td align="center"><a href="https://git.unistra.fr/yanovskyy"><img src="https://git.unistra.fr/assets/no_avatar-849f9c04a3a0d0cea2424ae97b27447dc64a7dbfae83c036c45b403392f0e8ba.png" width="100px;" alt=""/><br /><sub><b>Alexander Yanovskyy</b></sub></a><br /><a href="https://github.com/creativecommons/project_creativecommons.org/commits?author=Akpjunior94" title="Code">💻</a></td>

  </tr>
