package model;

import model.entities.Deck;
import model.entities.Domino;
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
    private List<Domino> _actualDominoes;
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
        createPlayers(this._numberplayer.getNumberPlayers());
    }

    public void setTwoPlayers() {  setNumberPlayer( this._numberplayer.getTwoPlayers()); }

    public void setThreePlayers()  {  setNumberPlayer(this._numberplayer.getThreePlayers());  }

    public void setQuatroPlayers()  {  setNumberPlayer(this._numberplayer.getQuatroPlayers()); }


    public void createPlayers(int numberPlayer){
        _listPlayers = new ArrayList<>();
        for(int i = 0; i < numberPlayer; i++){
            _listPlayers.add(new Player());
        }
    }

    public void createDeck(int numberPlayer){
        _deck = new Deck(numberPlayer);
    }

    public List<Domino> getActualDominoes(){
        return this._actualDominoes;
    }

    public void putDominoOnTable(){
        _actualDominoes = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            _actualDominoes.add(_deck.giveADomino());
        }
        //Collections.sort(_actualDominoes.getId());
        notifyObservers();
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
