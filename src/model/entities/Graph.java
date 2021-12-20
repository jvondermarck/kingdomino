package model.entities;

import java.util.NoSuchElementException;

public class Graph {
    private final Tile[][] _arrayTiles;
    private String _errorMessage;

    public Graph(){
        _arrayTiles = new Tile[5][5];
    }

    public void setDomino(Domino domino, int x, int y){
        _errorMessage = "";
        if(!isPlaceAvailable(x,y)){
            _errorMessage = "Placement occupied !";
            //throw new NoSuchElementException("Placement occupied !");
        }

        //Check if it is near a castle
        if(_arrayTiles[Math.min(x+1, _arrayTiles.length-1)][y] instanceof Castle || _arrayTiles[Math.max(x-1, 0)][y] instanceof Castle
                || _arrayTiles[x][Math.min(y + 1 , _arrayTiles[0].length-1)] instanceof Castle || _arrayTiles[x][Math.max(y - 1 , 0)] instanceof Castle ){

            _arrayTiles[x][y] = domino.getTile()[0][0];

            //If the domino is like that :
            // O O
            // X X
            if(domino.isXX()){
                if(_arrayTiles[x][y+1] instanceof Castle){
                    _arrayTiles[x][y-1] = domino.getTile()[0][1];
                }

                else if(_arrayTiles[x][y-1] instanceof Castle){
                    _arrayTiles[x][y+1] = domino.getTile()[0][1];
                }
                else if(_arrayTiles[x-1][y] instanceof Castle){
                    _arrayTiles[x][y+1] = domino.getTile()[0][1];
                }
                else if(_arrayTiles[x+1][y] instanceof Castle){
                    _arrayTiles[x][y+1] = domino.getTile()[0][1];
                }
            }

            //If the domino is like that :
            // O X
            // O X
            else if(domino.isXY()){
                if(_arrayTiles[x][y+1] instanceof Castle){
                    _arrayTiles[x+1][y] = domino.getTile()[1][0];
                }
                else if(_arrayTiles[x][y-1] instanceof Castle ){
                    _arrayTiles[x+1][y] = domino.getTile()[1][0];
                }
                else if(_arrayTiles[x-1][y] instanceof Castle){
                    _arrayTiles[x+1][y] = domino.getTile()[1][0];
                }
                else if(_arrayTiles[x+1][y] instanceof Castle){
                    _arrayTiles[x-1][y] = domino.getTile()[1][0];
                }
            }
        }

        //Check if a compatible Tile is avaiable near pos x y
        else if(isSameTile(_arrayTiles[Math.min(x+1, _arrayTiles.length-1)][y], domino.getTile()[0][0]) ||  isSameTile(_arrayTiles[Math.max(x-1, 0)][y], domino.getTile()[0][0]) ||
                isSameTile(_arrayTiles[x][Math.min(x-1, _arrayTiles.length-1)], domino.getTile()[0][0]) || isSameTile(_arrayTiles[x][Math.max(y-1, 0)], domino.getTile()[0][0])){

            _arrayTiles[x][y] = domino.getTile()[0][0];
            if(domino.isXX()){
                if(isPlaceAvailable(x, Math.min(y+1, _arrayTiles.length-1))){
                    _arrayTiles[x][y+1] = domino.getTile()[0][1];
                }
                else if(isPlaceAvailable(x, Math.max(y-1, 0))){
                    _arrayTiles[x][y-1] = domino.getTile()[0][1];
                }
            }

            else if(domino.isXY()){
                if(isPlaceAvailable(Math.max(x-1, 0), y)){
                    _arrayTiles[x-1][y] = domino.getTile()[1][0];
                }
                else if(isPlaceAvailable(Math.min(x+1, _arrayTiles.length-1), y)){
                    _arrayTiles[x+1][y] = domino.getTile()[1][0];
                }
            }
        }

        else{
            _errorMessage = "Not near a castle or a same tile !";
            //throw new NoSuchElementException("cant place it because not near a castle or a same tile !");
        }
    }

    public boolean isSameTile(Tile tile1, Tile tile2){
        if(tile1 != null){
            return tile1.getColor().equals(tile2.getColor());
        }
        return false;
    }

    public boolean isPlaceAvailable(int x, int y){
        return _arrayTiles[x][y] == null;
    }

    public void setCastle(int x, int y, Castle castle){
        this._arrayTiles[x][y] = castle;
    }

    public String get_errorMessage() {
        return _errorMessage;
    }

    public Tile[][] getTiles(){
        return this._arrayTiles;
    }

}
