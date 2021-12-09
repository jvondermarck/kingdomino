package model.entities;

import utilities.CSVReader;

import java.util.*;

public class Deck {
    private List<Domino> _listdominoes;
    private  Integer maxsize;
    private static final Random rand = new Random();

    public Deck(){
        _listdominoes = new ArrayList<>();
        List<List<String>> dataCSV = CSVReader.readCSV("docs/kingdomino.csv");
        maxsize = dataCSV.size();
        for(int i = 1; i < maxsize; i++){
            Domino domino = generateDomino(i, dataCSV);
            _listdominoes.add(domino);
        }
        this.shuffle();
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
        Collections.shuffle(this._listdominoes, new Random());
    }

    public Domino giveADomino(){
        int random = rand.nextInt(maxsize);
        Domino domino = this._listdominoes.get(random);
        this._listdominoes.remove(domino);
        return domino;
    }



}
