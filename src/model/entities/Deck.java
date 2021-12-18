package model.entities;

import utilities.CSVReader;

import java.util.*;

public class Deck {
    private final List<Domino> _listdominoes;
    private static final Random _rand = new Random();

    public Deck(int numberPlayer){
        _listdominoes = new ArrayList<>();
        List<List<String>> dataCSV = CSVReader.readCSV("kingdomino.csv");
        for(int i = 1; i < dataCSV.size(); i++){
            Domino domino = generateDomino(i, dataCSV);
            _listdominoes.add(domino);
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
            int random = _rand.nextInt(_listdominoes.size());
            _listdominoes.remove(random);
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
        return this._listdominoes;
    }

    public Integer sizeOfDeck(){
        return this._listdominoes.size();
    }

    public void shuffle(){
        Collections.shuffle(this._listdominoes, _rand);
    }

    public Domino giveADomino(){
        int random = _rand.nextInt(_listdominoes.size());
        Domino domino = this._listdominoes.get(random);
        this._listdominoes.remove(domino);
        return domino;
    }



}
