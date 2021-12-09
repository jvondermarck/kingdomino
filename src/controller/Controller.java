package controller;

import model.Game;

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


}
