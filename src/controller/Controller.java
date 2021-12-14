package controller;
import model.Game;
import model.Observer;

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

    // Methods for the strategy about the number of players
    public void switchToDuo(){ _game.setTwoPlayers();}
    public void switchToTrio(){ _game.setThreePlayers();}
    public void switchToQuatro(){ _game.setQuatroPlayers();}

    public void callHarmony(){ _game.factorHarmonyMode();  }
    public void callMiddleKingdom(){ _game.factorMiddleKingdom(); }

    public void addObserver(Observer observer){
        _game.addObservers(observer);
    }

}

