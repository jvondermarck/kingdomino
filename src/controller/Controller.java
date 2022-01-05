package controller;
import model.Game;
import model.Observer;
import model.entities.Player;
import view.KingDominoGame;
import java.io.IOException;
import java.util.List;

public class Controller {
    private final Game _game;

    public Controller(Game game){
        this._game = game;
    }

    public void instanciateDeck(int numberPlayer){
        this._game.createDeck(numberPlayer);
    }

    public void putDominoOnTable(){ _game.putDominoOnTable(); }

    // Methods for the strategy about the number of players
    public void switchToDuo(){ _game.setTwoPlayers();}
    public void switchToTrio(){ _game.setThreePlayers();}
    public void switchToQuatro(){ _game.setQuatroPlayers();}

    // Methods about the game mode
    public void callHarmony(){ _game.factorHarmonyMode();  }
    public void callMiddleKingdom(){ _game.factorMiddleKingdom(); }

    public void callRotationDomino(int index) { _game.rotateDomino(index); }
    public void callReverseDomino(int index) { _game.reverseDomino(index); }
    public void callSetDirectionDomino(int index, int direction) { _game.setDirectionDomino(index, direction);}

    public void setCastle(int index, int x, int y) { _game.setCastlePlayer(index, x, y); }
    public void setDominoOnKingdom(int indexDomino, int indexPlayer, int x, int y) { _game.setDominoOnKingdom(indexDomino, indexPlayer, x,y);}

    public void addObserver(Observer observer){
        _game.addObservers(observer);
    }
    public void instantiateKingdominoGame() throws IOException { new KingDominoGame() ;}

    public void calculScorePlayer () { _game.calculateScores(); }
}

