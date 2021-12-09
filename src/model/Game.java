package model;

import model.entities.Deck;
import model.entities.Player;
import model.set.GameMode;
import model.set.NumberPlayer;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Game instance;
    private List<Observer> _observer;
    private Deck _deck;
    private List<Player> _listPlayers;
    private GameMode _gameMode;
    private NumberPlayer _numberplayer;

    private Game()
    {
        this._observer = new ArrayList<>();
        /*
        setGameMode();
        setNumberPlayer();
        createPlayers();
        createDeck();
         */
    }

    public static Game getInstance()
    {
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    public void setGameMode(){
        //this._gameMode = new GameMode();
    }

    public void setNumberPlayer(){
        this._numberplayer= new NumberPlayer();
    }

    public void createPlayers(){
        _listPlayers = new ArrayList<>();

    }

    public void createDeck(){
        _deck = new Deck();

    }

    public void addObservers(Observer observer){
        this._observer.add(observer);
    }

    public void notifyObservers(){
        for(Observer observer : _observer){
            observer.update(this);
        }
    }
}
