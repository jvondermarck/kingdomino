package controller;
import model.Game;
import model.Observer;
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

    public void instanciatePlayer(int numberPlayer){
        _game.createPlayers(numberPlayer);
    }

    public void putDominoOnTable(){ _game.putDominoOnTable(); }

    // Methods for the strategy about the number of players
    public void switchToDuo(){ _game.setTwoPlayers();}
    public void switchToTrio(){ _game.setThreePlayers();}
    public void switchToQuatro(){ _game.setQuatroPlayers();}
    public int getNumberPlayer() { return _game.getNumberPlayer(); }

    // Methods about the game mode
    public void callHarmony(){ _game.factorHarmonyMode();  }
    public void callMiddleKingdom(){ _game.factorMiddleKingdom(); }

    public void callRotationDomino(int index) { _game.rotateDomino(index); }
    public void callReverseDomino(int index) { _game.reverseDomino(index); }
    public void callSetDirectionDomino(int index, int direction) { _game.setDirectionDomino(index, direction);}

    public void setCastle(int index, int x, int y) { _game.setCastlePlayer(index, x, y); }
    public void setDominoOnGraph(int indexDomino, int indexPlayer, int x, int y) { _game.setDominoOnGraph(indexDomino, indexPlayer, x,y);}

    public void addObserver(Observer observer){
        _game.addObservers(observer);
    }
    public void instantiateKingdominoGame() throws IOException { new KingDominoGame() ;}

    public void calculScorePlayer () { _game.calculateScores(); }

    public int getTotalScorePlayer(int index) { return _game.getPlayer(index).getTotalScore(); }

    public int getYellowTilesScore(int index) { return _game.getPlayer(index).getYellowTilesScore(); }
    public int getDarkGreenTilesScore(int index) { return _game.getPlayer(index).getDarkGreenTilesScore(); }
    public int getBlueTilesScore(int index) { return _game.getPlayer(index).getBlueTilesScore(); }
    public int getBlackTilesScore(int index) { return _game.getPlayer(index).getBlackTilesScore(); }
    public int getBrownTilesScore(int index) { return _game.getPlayer(index).getBrownTilesScore(); }
    public int getLightGreenTilesScore(int index) { return _game.getPlayer(index).getLightGreenTilesScore(); }

    public List<String> getGameMode() { return _game.get_listGameModeString(); }
    public int getHarmonyBonus(int index) { return _game.getPlayer(index).getHarmonyBonus(); }
    public int getMiddleKingdom(int index) { return _game.getPlayer(index).getMiddleKingdom(); }
}

