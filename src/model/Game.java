package model;

import model.entities.Deck;
import model.entities.Domino;
import model.entities.Player;
import model.set.GameMode;
import model.set.GameModeFactory;
import model.set.NumberPlayer;
import model.set.NumberPlayerStrategy;

import java.util.*;

public class Game {
    private static Game instance;
    private final List<Observer> _observer;

    private Deck _deck;
    private List<Player> _listPlayers;
    private List<Domino> _actualDominoes;
    private final NumberPlayer _numberplayer;

    private final GameModeFactory _factoryGameMode;
    private final List<GameMode> _listGameMode;
    private final List<String> _listGameModeString;
    private boolean _dominoesLeft; // to check if there are enough dominoes on the table to play, if no we stop the game

    private Game()
    {
        this._observer = new ArrayList<>();
        this._numberplayer = new NumberPlayer();
        this._listGameMode = new ArrayList<>();
        this._listGameModeString = new ArrayList<>();
        this._factoryGameMode = new GameModeFactory();
        this._dominoesLeft = false;
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
        this._listGameModeString.add(gameMode.toString());
        System.out.println("Game Mode : " + gameMode);
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

    public void setCastlePlayer(int index, int x, int y)
    {
        _listPlayers.get(index).setCastle(x,y);
    }

    public void createDeck(int numberPlayer){
        _deck = new Deck(numberPlayer);
    }

    public Player getPlayer(int index)
    {
        return _listPlayers.get(index);
    }

    public List<Domino> getActualDominoes(){
        return this._actualDominoes;
    }

    public void putDominoOnTable(){
        _actualDominoes = new ArrayList<>();
        int numberDominoes = 4;
        if(_numberplayer.getNumberPlayers() == 3){
            numberDominoes = 3;
        }

        if((_deck.get_listdominoes().size() < 3 && numberDominoes == 3) || (_deck.get_listdominoes().size() < 4))
        {
            this._dominoesLeft = true; // there are not enoigh dominoes to play
        } else {
            for(int i = 0; i < numberDominoes; i++){
                _actualDominoes.add(_deck.giveADomino());
                sortDominoTable(_actualDominoes.size());
            }
        }
        notifyObservers();
    }

    public void sortDominoTable(int n)
    {
        // Base case
        if (n == 1)
            return;

        for (int i=0; i<n-1; i++)
        {
            if (_actualDominoes.get(i).getId() > _actualDominoes.get(i + 1).getId())
            {
                Domino temp = _actualDominoes.get(i);
                _actualDominoes.set(i, _actualDominoes.get(i+1));
                _actualDominoes.set(i+1,temp);
            }
        }

        // Largest element is fixed, recur for remaining array
        sortDominoTable(n-1);
    }

    public void rotateDomino(int index)
    {
        _actualDominoes.get(index).rotate();
        notifyObserversRotation();
    }

    public void reverseDomino(int index)
    {
        _actualDominoes.get(index).reverse();
        notifyObserversRotation();
    }

    public void setDirectionDomino(int index, int direction){
        if(direction == 1) // UP
            _actualDominoes.get(index).setUpSide();
        if(direction == -1) // DOWN
            _actualDominoes.get(index).setDownSide();
        if(direction == 2) // RIGHT
            _actualDominoes.get(index).setRightSide();
        if(direction == -2) // LEFT
            _actualDominoes.get(index).setLeftSide();
    }

    public void setDominoOnGraph(int indexDomino, int indexPlayer, int x, int y)
    {
        Domino domino = _actualDominoes.get(indexDomino);
        _listPlayers.get(indexPlayer).getGraph().setDomino(domino, x, y);
        notifyObserverDominoGraph();
    }

    public String getErrorMessageSetDomino(int indexPlayer)
    {
        return _listPlayers.get(indexPlayer).getGraph().get_errorMessage();
    }


    public boolean isXXDomino(int index)
    {
        return  _actualDominoes.get(index).isXX();
    }

    public boolean isXYDomino(int index)
    {
        return  _actualDominoes.get(index).isXY();
    }

    public String getColorTile(int index, int x, int y)
    {
        return  _actualDominoes.get(index).getColor(x,y);
    }

    public void addObservers(Observer observer){
        this._observer.add(observer);
    }

    public void notifyObservers(){
        for(Observer observer : _observer){
            observer.update(this);
        }
    }

    public void notifyObserversRotation()
    {
        for(Observer observer : _observer){
            observer.rotationDomino(this);
        }
    }

    public void notifyObserverDominoGraph()
    {
        for(Observer observer : _observer)
        {
            observer.dominoGraph(this);
        }
    }

    public boolean is_dominoesLeft() {
        return _dominoesLeft;
    }

    public void calculateScores(){
        for(Player p : _listPlayers){
            p.setYellowTilesScoreList(p.getGraph().getSizeOfADomain("#FDCA40")); // YELLOW TILE
            p.setDarkGreenTilesScoreList(p.getGraph().getSizeOfADomain("#0A9396")); // DARK GREEN TILE
            p.setBlueTilesScoreList(p.getGraph().getSizeOfADomain("#2176FF")); // BLUE TILE
            p.setBlackTilesScoreList(p.getGraph().getSizeOfADomain("#31393C")); // BLACK TILE
            p.setBrownTilesScoreList(p.getGraph().getSizeOfADomain("#7F4F24")); // BROWN TILE
            p.setLightGreenTilesScoreList(p.getGraph().getSizeOfADomain("#B5E48C")); // LIGHT GREEN TILE

            for(GameMode g : _listGameMode){
                if(g.executeGameMode(p)){
                    p.addBonus(g);
                }
            }

            p.calculateTotalScore();
        }
    }

    public List<String> get_listGameModeString() {
        return _listGameModeString;
    }
}
