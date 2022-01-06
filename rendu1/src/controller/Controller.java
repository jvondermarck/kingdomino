package controller;
import model.Game;
import model.Observer;
import view.KingDominoGame;

import java.io.IOException;

public class Controller {
    private final Game _game;

    public Controller(Game game){
        this._game = game;
    }

    public void instanciateDeck(int numberPlayer){
        this._game.createDeck(numberPlayer);
    }

    public void instanciatePlayer(int numberPlayer){
        _game.createPlayers(numberPlayer);
    }

    public void putDominoOnTable(){
        _game.putDominoOnTable();
    }

    // Methods for the strategy about the number of players
    public void switchToDuo(){ _game.setTwoPlayers();}
    public void switchToTrio(){ _game.setThreePlayers();}
    public void switchToQuatro(){ _game.setQuatroPlayers();}

    // Methods about the game mode
    public void callHarmony(){ _game.factorHarmonyMode();  }
    public void callMiddleKingdom(){ _game.factorMiddleKingdom(); }

    public void callRotationDomino(int index) { _game.rotateDomino(index); }
    public void callReverseDomino(int index) { _game.reverseDomino(index); }

    public void setCastle(int index, int x, int y) { _game.setCastlePlayer(index, x, y); }

    public void addObserver(Observer observer){
        _game.addObservers(observer);
    }
    public void instantiateKingdominoGame() throws IOException { addObserver(new KingDominoGame()) ;}

}

