package model.entities;

public class Graph {
    private Domino[][] _arrayDominoes;
    private Tile[][] _arrayTiles;

    public Graph(){
        _arrayDominoes = new Domino[5][5];
        _arrayTiles = new Tile[5][5];
    }

    public Domino getDomino(int x, int y){
        return _arrayDominoes[x][y];
    }

    public void setDomino(Domino domino, int x, int y){
        _arrayTiles[x][y] = domino.getTile()[0][0];

        //Si le domino est comme ça :
        // O O
        // X X
        if(domino.isXX()){// pas videvide
            _arrayTiles[x+1][y] = domino.getTile()[0][1];
        }
        //Si le domino est comme ça :
        // O X
        // O X
        else if(domino.isXY()){
            _arrayTiles[x][y + 1] = domino.getTile()[1][0];
        }

    }

    public boolean isPlaceAvailable(int x, int y){
        return false;
    }
}
