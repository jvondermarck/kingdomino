package model.entities;

import java.util.NoSuchElementException;

public class Graph {
    private final Tile[][] _arrayTiles;

    public Graph(){
        _arrayTiles = new Tile[5][5];
    }

    public void setDomino(Domino domino, int x, int y){
        if(_arrayTiles[x][y] instanceof Castle || _arrayTiles[x][x] != null){
            throw new NoSuchElementException("Placement occupied !");
        }

        //Check if it is near a castle
        if(_arrayTiles[x+1][y] instanceof Castle || _arrayTiles[x-1][y] instanceof Castle || _arrayTiles[x][y+1] instanceof Castle || _arrayTiles[x][y-1] instanceof Castle){
            _arrayTiles[x][y] = domino.getTile()[0][0];
            //If the domino is like that :
            // O O
            // X X
            if(domino.isXX()){
                if(_arrayTiles[x+1][y] instanceof Castle && isPlaceAvailable(x+1, y)){
                    _arrayTiles[x-1][y] = domino.getTile()[0][1];
                }
                else if(_arrayTiles[x-1][y] instanceof Castle && isPlaceAvailable(x-1, y)){
                    _arrayTiles[x+1][y] = domino.getTile()[0][1];
                }
            }

            //If the domino is like that :
            // O X
            // O X
            else if(domino.isXY()){
                if(_arrayTiles[x][y+1] instanceof Castle){
                    _arrayTiles[x][y-1] = domino.getTile()[1][0];
                }
                else if(_arrayTiles[x][y-1] instanceof Castle ){
                    _arrayTiles[x][y+1] = domino.getTile()[1][0];
                }
            }
        }

        //Check if a compatible Tile is avaiable near pos x y
        else if(_arrayTiles[x+1][y].getColor().equals(domino.getTile()[0][0].getColor()) || _arrayTiles[x-1][y].getColor().equals(domino.getTile()[0][0].getColor()) ||
                _arrayTiles[x][y+1].getColor().equals(domino.getTile()[0][0].getColor()) || _arrayTiles[x][y-1].getColor().equals(domino.getTile()[0][0].getColor())){

            _arrayTiles[x][y] = domino.getTile()[0][0];
            if(domino.isXX() && isPlaceAvailable(x+1, y)){
                _arrayTiles[x-1][y] = domino.getTile()[0][1];
            }

            else if(domino.isXY() && isPlaceAvailable(x, y+1)){
                _arrayTiles[x][y+1] = domino.getTile()[1][0];
            }
        }


    }

    public boolean isPlaceAvailable(int x, int y){
        return _arrayTiles[x][y] != null;
    }

    public void setCastle(int x, int y, Castle castle){
        this._arrayTiles[x][y] = castle;
    }

}
