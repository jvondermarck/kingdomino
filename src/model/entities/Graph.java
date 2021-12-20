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

        if(x - 1 < 0 || y - 1 < 0 || x + 1 > _arrayTiles.length - 1 || y + 1 > _arrayTiles.length - 1){
            if(domino.isXX() && y - 1 < 0){
                _errorMessage = "Placement occupied !";
                return;
            }
            else if (domino.isXX() && y + 1 > _arrayTiles.length - 1){
                _errorMessage = "Placement occupied !";
                return;
            }
            else if(domino.isXY() && x - 1 < 0){
                _errorMessage = "Placement occupied !";
                return;
            }
            else if (domino.isXY() && x + 1 > _arrayTiles.length - 1){
                _errorMessage = "Placement occupied !";
                return;
            }
        }

        if(!isPlaceAvailable(x,y)){
            _errorMessage = "Placement occupied !";
            return;
            //throw new NoSuchElementException("Placement occupied !");
        }

        if(isPlaceAvailable(x,y) && !isPlaceAvailable(x-1, y) && !isPlaceAvailable(x+1, y) && !isPlaceAvailable(x,y+1) && !isPlaceAvailable(x,y-1)){
            _errorMessage = "Dead end !";
            return;
            //throw new NoSuchElementException("Placement occupied !");
        }

        if(isCastleHere(x,y)){
            _errorMessage = "Castle is here !";
            return;
            //throw new NoSuchElementException("Placement occupied !");
        }


        //Check if it is near a castle
        if(isCastleHere(x+1,y)|| isCastleHere(x-1,y) || isCastleHere(x, y+1)|| isCastleHere(x,y-1)){
            //If the domino is like that :
            // O O
            // X X
            if(domino.isXX()){
                // .... | ...... | ...
                // .... | CASTLE | X | X
                // .....| .....  | ...
                if(isCastleHere(x,y-1) && isPlaceAvailable(x, y) && isPlaceAvailable(x, y + 1)){
                    _arrayTiles[x][y] = domino.getTile()[0][0];
                    _arrayTiles[x][Math.min(y+1, _arrayTiles.length-1)] = domino.getTile()[0][1];
                }
                // |...| ..| ..... | ..
                // | X | X | CASTLE| ..
                // |...|...| ..... | ..
                else if(isCastleHere(x,y+1) && isPlaceAvailable(x,y) && isPlaceAvailable(x,y-1)){
                    _arrayTiles[x][y] = domino.getTile()[0][0];
                    _arrayTiles[x][Math.max(y - 1 , 0)] = domino.getTile()[0][1];
                }
                //     | ? |   X   | ?
                // |.. | ..| CASTLE| ..
                // |...|...| ..... | ..
                else if(isCastleHere(x+1,y) && isPlaceAvailable(x,y) && isPlaceAvailable(x,y-1) || isPlaceAvailable(x,y+1)){
                    _arrayTiles[x][y] = domino.getTile()[0][0];
                    _arrayTiles[x][Math.min(y+1, _arrayTiles.length-1)] = domino.getTile()[0][1];
                }
                //     | ..|  ...  |
                // |.. | ..| CASTLE| ..
                // |...| ? |    X  | ?
                else if(isCastleHere(x-1,y) && isPlaceAvailable(x,y) && isPlaceAvailable(x,y-1) || isPlaceAvailable(x,y+1)){
                    _arrayTiles[x][y] = domino.getTile()[0][0];
                    _arrayTiles[x][Math.min(y+1, _arrayTiles.length-1)] = domino.getTile()[0][1];
                }
            }

            //If the domino is like that :
            // O X
            // O X
            else if(domino.isXY()){
                if(isCastleHere(x-1,y) && isPlaceAvailable(x, y) && isPlaceAvailable(x+1, y)){
                    _arrayTiles[x][y] = domino.getTile()[0][0];
                    _arrayTiles[Math.min(x+1, _arrayTiles.length-1)][y] = domino.getTile()[1][0];
                }

                else if(isCastleHere(x+1,y) && isPlaceAvailable(x,y) && isPlaceAvailable(x-1,y)){
                    _arrayTiles[x][y] = domino.getTile()[0][0];
                    _arrayTiles[Math.max(x - 1 , 0)][y] = domino.getTile()[1][0];
                }

                else if(isCastleHere(x,y+1) && isPlaceAvailable(x,y) && isPlaceAvailable(x+1,y) || isPlaceAvailable(x-1,y)){
                    _arrayTiles[x][y] = domino.getTile()[0][0];
                    _arrayTiles[Math.min(x+1, _arrayTiles.length-1)][y] = domino.getTile()[1][0];
                }

                else if(isCastleHere(x,y-1) && isPlaceAvailable(x,y) && isPlaceAvailable(x+1,y) || isPlaceAvailable(x-1,y)){
                    _arrayTiles[x][y] = domino.getTile()[0][0];
                    _arrayTiles[Math.min(x+1, _arrayTiles.length-1)][y] = domino.getTile()[1][0];
                }
            }
        }

        //Check if a compatible Tile is available near pos x y
        //TODO : a upddate dans le left right sera up
        else if(isSameTile(_arrayTiles[Math.min(x+1, _arrayTiles.length-1)][y], domino.getTile()[0][0]) ||  isSameTile(_arrayTiles[Math.max(x-1, 0)][y], domino.getTile()[0][0]) ||
                isSameTile(_arrayTiles[x][Math.min(y+1, _arrayTiles.length-1)], domino.getTile()[0][0]) || isSameTile(_arrayTiles[x][Math.max(y-1, 0)], domino.getTile()[0][0])){

            if(domino.isXX()){
                if(isPlaceAvailable(x,y) && isPlaceAvailable(x, Math.min(y+1, _arrayTiles.length-1))){
                    _arrayTiles[x][y] = domino.getTile()[0][0];
                    _arrayTiles[x][Math.min(y + 1 , _arrayTiles.length-1)] = domino.getTile()[0][1];
                }
                else if(isPlaceAvailable(x,y) && isPlaceAvailable(x, Math.max(y-1, 0))){
                    _arrayTiles[x][y] = domino.getTile()[0][0];
                    _arrayTiles[x][Math.max(y - 1 , 0)] = domino.getTile()[0][1];
                }
            }

            else if(domino.isXY()){
                if(isPlaceAvailable(x,y) && isPlaceAvailable(Math.max(x-1, 0), y) && x-1 >=0 ){
                    _arrayTiles[x][y] = domino.getTile()[0][0];
                    _arrayTiles[Math.max(x - 1 , 0)][y] = domino.getTile()[1][0];
                }
                else if(isPlaceAvailable(x,y) && isPlaceAvailable(Math.min(x+1, _arrayTiles.length-1), y)){
                    _arrayTiles[x][y] = domino.getTile()[0][0];
                    _arrayTiles[Math.min(x+1, _arrayTiles.length-1)][y] = domino.getTile()[1][0];
                }
            }
        }

        else{
            _errorMessage = "Not near a castle or a same tile !";
            //throw new NoSuchElementException("cant place it because not near a castle or a same tile !");
        }

        for(int i = 0; i < 5; i++){
            for(int j = 0; j<5; j++){
                if(!this.isPlaceAvailable(i,j)){
                    System.out.print(" "+ this.getTiles()[i][j].getColor().substring(this.getTiles()[i][j].getColor().length() - 1) + " ");
                }
                else{
                    System.out.print(" - ");
                }
            }
            System.out.println("");
        }
    }

    public boolean isSameTile(Tile tile1, Tile tile2){
        if(tile1 != null){
            return tile1.getColor().equals(tile2.getColor());
        }
        return false;
    }

    public boolean isPlaceAvailable(int x, int y){
        int xTemp = x;
        int yTemp = y;
        if(xTemp < 0){
            xTemp = 0;
        }
        else if(yTemp < 0){
            yTemp = 0;
        }
        else if(xTemp > _arrayTiles.length - 1){
            xTemp = _arrayTiles.length - 1;
        }
        else if(yTemp > _arrayTiles.length - 1){
            yTemp = _arrayTiles.length - 1;
        }
        return _arrayTiles[xTemp][yTemp] == null;
    }

    public void setCastle(int x, int y, Castle castle){
        this._arrayTiles[x][y] = castle;
    }

    public boolean isCastleHere(int x, int y){
        int xTemp = x;
        int yTemp = y;
        if(xTemp < 0){
            xTemp = 0;
        }
        else if(yTemp < 0){
            yTemp = 0;
        }
        else if(xTemp > _arrayTiles.length - 1){
            xTemp = _arrayTiles.length - 1;
        }
        else if(yTemp > _arrayTiles.length - 1){
            yTemp = _arrayTiles.length - 1;
        }
        return _arrayTiles[xTemp][yTemp] instanceof Castle;
    }

    public String get_errorMessage() {
        return _errorMessage;
    }

    public Tile[][] getTiles(){
        return this._arrayTiles;
    }

}
