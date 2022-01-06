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

    private final List<Observer> _listObserver;
    private Deck _deck;
    private List<Player> _listPlayers;
    private List<Domino> _listActualDominoes;
    private final NumberPlayer _numberPlayer;
    private final GameModeFactory _factoryGameMode;
    private final List<GameMode> _listGameMode;

    private int _intPlayer;
    private final List<String> _listGameModeString;
    private boolean _dominoesLeft; // to check if there are enough dominoes on the table to play, if no we stop the game

    public Game()
    {
        this._listObserver = new ArrayList<>();
        this._numberPlayer = new NumberPlayer();
        this._listGameMode = new ArrayList<>();
        this._listGameModeString = new ArrayList<>();
        this._factoryGameMode = new GameModeFactory();
        this._dominoesLeft = false;
    }

    public void addGameMode(GameMode gameMode){
        this._listGameMode.add(gameMode); // We add all game mode in a list
        this._listGameModeString.add(gameMode.toString());
        //System.out.println("Game Mode : " + gameMode);
    }

    public void factorHarmonyMode(){ addGameMode(this._factoryGameMode.createHarmony()); } // we create the Harmony mode, and we add it in our list of GameMode
    public void factorMiddleKingdom(){ addGameMode(this._factoryGameMode.createMiddleKingdom()); }

    public void setNumberPlayer(NumberPlayerStrategy strategy){
        this._numberPlayer.setStrategy(strategy);
        _intPlayer = this._numberPlayer.getNumberPlayers();
        createPlayers(this._numberPlayer.getNumberPlayers());
    }

    public int getNumberPlayer(){ return _intPlayer;}

    public void setTwoPlayers() {  setNumberPlayer( this._numberPlayer.getTwoPlayers()); }

    public void setThreePlayers()  {  setNumberPlayer(this._numberPlayer.getThreePlayers());  }

    public void setQuatroPlayers()  {  setNumberPlayer(this._numberPlayer.getQuatroPlayers()); }


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
        return this._listActualDominoes;
    }

    public void putDominoOnTable(){
        _listActualDominoes = new ArrayList<>();
        int numberDominoes = 4;
        if(_numberPlayer.getNumberPlayers() == 3){
            numberDominoes = 3;
        }

        if((_deck.getListDominoes().size() < 3 && numberDominoes == 3) || (_deck.getListDominoes().size() < 4))
        {
            this._dominoesLeft = true; // there are not enoigh dominoes to play
        } else {
            for(int i = 0; i < numberDominoes; i++){
                _listActualDominoes.add(_deck.giveADomino());
                sortDominoTable(_listActualDominoes.size());
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
            if (_listActualDominoes.get(i).getId() > _listActualDominoes.get(i + 1).getId())
            {
                Domino temp = _listActualDominoes.get(i);
                _listActualDominoes.set(i, _listActualDominoes.get(i+1));
                _listActualDominoes.set(i+1,temp);
            }
        }

        // Largest element is fixed, recur for remaining array
        sortDominoTable(n-1);
    }

    public void rotateDomino(int index)
    {
        _listActualDominoes.get(index).rotate();
        notifyObserversRotation();
    }

    public void reverseDomino(int index)
    {
        _listActualDominoes.get(index).reverse();
        notifyObserversRotation();
    }

    public void setDirectionDomino(int index, int direction){
        if(direction == 1) // UP
            _listActualDominoes.get(index).setUpSide();
        if(direction == -1) // DOWN
            _listActualDominoes.get(index).setDownSide();
        if(direction == 2) // RIGHT
            _listActualDominoes.get(index).setRightSide();
        if(direction == -2) // LEFT
            _listActualDominoes.get(index).setLeftSide();
    }

    public void setDominoOnKingdom(int indexDomino, int indexPlayer, int x, int y)
    {
        Domino domino = _listActualDominoes.get(indexDomino);
        _listPlayers.get(indexPlayer).getKingdom().setDomino(domino, x, y);
        notifyObserverDominoKingdom();
    }

    public String getErrorMessageSetDomino(int indexPlayer)
    {
        return _listPlayers.get(indexPlayer).getKingdom().getErrorMessage();
    }


    public boolean isXXDomino(int index)
    {
        return  _listActualDominoes.get(index).isXX();
    }

    public boolean isXYDomino(int index)
    {
        return  _listActualDominoes.get(index).isXY();
    }

    public String getColorTile(int index, int x, int y)
    {
        return  _listActualDominoes.get(index).getColor(x,y);
    }

    public void addObservers(Observer observer){
        this._listObserver.add(observer);
    }

    public void notifyObservers(){
        for(Observer observer : _listObserver){
            observer.updateDominoesOnTable(this);
        }
    }

    public void notifyObserversRotation()
    {
        for(Observer observer : _listObserver){
            observer.updateDominoPreview(this);
        }
    }

    public void notifyObserverDominoKingdom()
    {
        for(Observer observer : _listObserver)
        {
            observer.updatePlayerKingdom(this);
        }
    }

    public boolean isDominoesLeft() {
        return _dominoesLeft;
    }

    public void calculateScores(){
        for(Player p : _listPlayers){
            p.setYellowTilesScoreList(p.getKingdom().getSizeOfADomain("#FDCA40")); // YELLOW TILE
            p.setDarkGreenTilesScoreList(p.getKingdom().getSizeOfADomain("#0A9396")); // DARK GREEN TILE
            p.setBlueTilesScoreList(p.getKingdom().getSizeOfADomain("#2176FF")); // BLUE TILE
            p.setBlackTilesScoreList(p.getKingdom().getSizeOfADomain("#31393C")); // BLACK TILE
            p.setBrownTilesScoreList(p.getKingdom().getSizeOfADomain("#7F4F24")); // BROWN TILE
            p.setLightGreenTilesScoreList(p.getKingdom().getSizeOfADomain("#B5E48C")); // LIGHT GREEN TILE

            for(GameMode g : _listGameMode){
                if(g.executeGameMode(p)){
                    p.addBonus(g.numberBonus());
                }
            }

            p.calculateTotalScore();
        }
    }

    public List<GameMode> getListGameMode(){
        return this._listGameMode;
    }

    public List<String> getListGameModeString() {
        return _listGameModeString;
    }
}
