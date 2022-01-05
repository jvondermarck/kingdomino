package model.entities;

import utilities.CSVReader;

import java.util.*;

public class Deck {
    private final List<Domino> _listDominoes;
    private static final Random _rand = new Random();

    public Deck(int numberPlayer){
        _listDominoes = new ArrayList<>();
        List<List<String>> dataCSV = CSVReader.readCSV("kingdomino.csv");
        for(int i = 1; i < dataCSV.size(); i++){
            Domino domino = generateDomino(i, dataCSV);
            _listDominoes.add(domino);
        }
        this.shuffle();

        if(numberPlayer == 3){
            removeDomino(12);
        }
        else if(numberPlayer == 2){
            removeDomino(24);
        }
    }

    private void removeDomino(int iteration){
        for(int i = 0; i < iteration; i++){
            int random = _rand.nextInt(_listDominoes.size());
            _listDominoes.remove(random);
        }
    }

    private Domino generateDomino(int i, List<List<String>> dataCSV){
        int id = Integer.parseInt(dataCSV.get(i).get(0)); // 0 == ID
        //First tile
        String colorSuit = dataCSV.get(i).get(2); // 2 == color first suit
        int crownsSuit = Integer.parseInt(dataCSV.get(i).get(5)); // 5 == crownws first suit
        Tile tile1 = new Tile(crownsSuit, colorSuit);
        //Second tile
        colorSuit = dataCSV.get(i).get(4); // 4 == color second suit
        crownsSuit = Integer.parseInt(dataCSV.get(i).get(6)); // 6 == crowns second suit
        Tile tile2 = new Tile(crownsSuit, colorSuit);

        return new Domino(id, tile1, tile2);
    }

    public List<Domino> getStack(){
        return this._listDominoes;
    }

    public void shuffle(){
        Collections.shuffle(this._listDominoes, _rand);
    }

    public Domino giveADomino(){
        int random = _rand.nextInt(_listDominoes.size());
        Domino domino = this._listDominoes.get(random);
        this._listDominoes.remove(domino);
        return domino;
    }

    public List<Domino> getListDominoes() {
        return _listDominoes;
    }
}
