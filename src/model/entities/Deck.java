package model.entities;

import utilities.CSVReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Domino> _listdominoes;
    private Integer MAXSIZE;

    public Deck(){
        _listdominoes = new ArrayList<>();
        List<List<String>> dataCSV = CSVReader.readCSV("docs/kingdomino.csv");
        MAXSIZE = dataCSV.size();
        for(int i = 0; i < MAXSIZE; i++){
            int id = Integer.parseInt(dataCSV.get(i).get(0)); // 0 == ID
            //Première tuile
            String colorSuit = dataCSV.get(i).get(2); // 2 == color first suit
            int crownsSuit = Integer.parseInt(dataCSV.get(i).get(5)); // 5 == crownws first suit
            Tile tile1 = new Tile(crownsSuit, colorSuit);
            //Deuxième tuile
            colorSuit = dataCSV.get(i).get(4); // 4 == color second suit
            crownsSuit = Integer.parseInt(dataCSV.get(i).get(6)); // 6 == crowns second suit
            Tile tile2 = new Tile(crownsSuit, colorSuit);

            Domino domino = new Domino(id, tile1, tile2);
            _listdominoes.add(domino);
        }
    }

    public List<Domino> getStack(){
        return this._listdominoes;
    }

    public void shuffle(){

    }

    public Domino giveADomino(){
        Random r = new Random();
        return null;
    }

}
