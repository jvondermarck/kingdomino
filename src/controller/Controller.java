package controller;

import model.Game;
import model.set.number.Duo;
import view.KingDominoStart;
import view.Window;

public class Controller {
    private Game _game;

    public Controller(Game game){
        this._game = game;
    }

    public void setGameMode(){
        //Game.getInstance().
    }

    public void instanciateDeck(){
        this._game.createDeck();
    }

    public void switchToDuo(){ _game.setTwoPlayers();}
    public void switchToTrio(){ _game.setThreePlayers();}
    public void switchToQuatro(){ _game.setQuatroPlayers();}


}
