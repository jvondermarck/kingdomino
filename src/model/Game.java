package model;

import model.entities.Deck;
import model.entities.Player;
import model.set.GameMode;
import model.set.GameModeFactory;
import model.set.NumberPlayer;
import model.set.NumberPlayerStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static Game instance;
    private List<Observer> _observer;

    private Deck _deck;
    private List<Player> _listPlayers;
    private NumberPlayer _numberplayer;

    private GameModeFactory _factoryGameMode;
    private List<GameMode> _listGameMode;

    private Game()
    {
        this._observer = new ArrayList<>();
        this._numberplayer = new NumberPlayer();
        this._listGameMode = new ArrayList<>();
        this._factoryGameMode = new GameModeFactory();
    }

    public static Game getInstance()
    {
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    public void addGameMode(GameMode gameMode){
        this._listGameMode.add(gameMode); // We add all game mode in a list
        System.out.println("Game Mode : " + gameMode.toString());
    }

    public void factorHarmonyMode(){ addGameMode(this._factoryGameMode.createHarmony()); } // we create the Harmony mode, and we add it in our list of GameMode
    public void factorMiddleKingdom(){ addGameMode(this._factoryGameMode.createMiddleKingdom()); }

    public void setNumberPlayer(NumberPlayerStrategy strategy){
        this._numberplayer.setStrategy(strategy);
        createPlayers();
    }

    public void setTwoPlayers()
    {
        setNumberPlayer( this._numberplayer.getTwoPlayers());
    }

    public void setThreePlayers()
    {
        setNumberPlayer(this._numberplayer.getThreePlayers());
    }

    public void setQuatroPlayers()
    {
        setNumberPlayer(this._numberplayer.getQuatroPlayers());
    }


    public void createPlayers(){
        _listPlayers = new ArrayList<>();
        System.out.println("nombre de joueurs : " + this._numberplayer.getNumberPlayers());
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

    public void resetElement()
    {

    }
}
