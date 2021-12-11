package controller;
import model.Game;

public class Controller {
    private final Game _game;

    public Controller(Game game){
        this._game = game;
    }

    public void instanciateDeck(){
        this._game.createDeck();
    }

    public void switchToDuo(){ _game.setTwoPlayers();}
    public void switchToTrio(){ _game.setThreePlayers();}
    public void switchToQuatro(){ _game.setQuatroPlayers();}

    public void callHarmony(){ _game.factorHarmonyMode();  }
    public void callMiddleKingdom(){ _game.factorMiddleKingdom(); }


}
