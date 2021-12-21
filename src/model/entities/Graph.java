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
            return;
            //throw new NoSuchElementException("Placement occupied !");
        }

        else if(isPlaceAvailable(x,y) && !isPlaceAvailable(x-1, y) && !isPlaceAvailable(x+1, y) && !isPlaceAvailable(x,y+1) && !isPlaceAvailable(x,y-1)){
            _errorMessage = "Dead end ! Pass your turn.";
            return;
            //throw new NoSuchElementException("Placement occupied !");
        }

        else if(isCastleHere(x,y)){
            _errorMessage = "Castle is here !";
            return;
            //throw new NoSuchElementException("Placement occupied !");
        }

        else if((x == 0 && y == 0 && (domino.isUpSide() || domino.isLeftSide())) // Up left corner
                || (x == _arrayTiles.length - 1 && y == 0 && (domino.isDownSide() || domino.isLeftSide())) // Down left corner
                    || (y == _arrayTiles.length - 1 && x == 0 && (domino.isUpSide() || domino.isRightSide()))// Up right corner
                        || (y == _arrayTiles.length - 1 && x == _arrayTiles.length-1 && (domino.isDownSide() || domino.isRightSide()))){ // Down right corner

            System.out.println(x+"|"+y);
            _errorMessage = "Impossible, out of the board!";
            return;
        }

        else if((x == 0 && 0 < y && y < _arrayTiles.length && (domino.isUpSide() || domino.isDownSide() && isCastleHere(x+1,y)))
                || (x == _arrayTiles.length - 1 && 0 < y && y < _arrayTiles.length && (domino.isDownSide() || domino.isUpSide() && isCastleHere(x-1,y)))
                  || (y == 0 && 0 < x && x < _arrayTiles.length && (domino.isLeftSide() || domino.isRightSide() && isCastleHere(x,y+1)))
                     || (y == _arrayTiles.length - 1 && 0 < x && x < _arrayTiles.length && (domino.isRightSide() || domino.isLeftSide() && isCastleHere(x,y-1)))){

            _errorMessage = "Impossible, out of the board!";
            return;
        }

        //Check if it is near a castle
        else if(isCastleHere(x+1,y)|| isCastleHere(x-1,y) || isCastleHere(x, y+1)|| isCastleHere(x,y-1)){
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
                else if(isCastleHere(x+1,y) && isPlaceAvailable(x,y)){
                    if(domino.isLeftSide() && isPlaceAvailable(x,y-1)){
                        _arrayTiles[x][y] = domino.getTile()[0][0];
                        _arrayTiles[x][Math.min(y-1, _arrayTiles.length-1)] = domino.getTile()[0][1];
                    }
                    else if(domino.isRightSide() && isPlaceAvailable(x,y+1)){
                        _arrayTiles[x][y] = domino.getTile()[0][0];
                        _arrayTiles[x][Math.min(y+1, _arrayTiles.length-1)] = domino.getTile()[0][1];
                    }
                }
                //     | ..|  ...  |
                // |.. | ..| CASTLE| ..
                // |...| ? |    X  | ?
                else if(isCastleHere(x-1,y) && isPlaceAvailable(x,y)){
                    if(domino.isLeftSide() && isPlaceAvailable(x,y-1)){
                        _arrayTiles[x][y] = domino.getTile()[0][0];
                        _arrayTiles[x][Math.min(y-1, _arrayTiles.length-1)] = domino.getTile()[0][1];
                    }
                    else if(domino.isRightSide() && isPlaceAvailable(x,y+1)){
                        _arrayTiles[x][y] = domino.getTile()[0][0];
                        _arrayTiles[x][Math.min(y+1, _arrayTiles.length-1)] = domino.getTile()[0][1];
                    }
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

                else if(isCastleHere(x,y+1) && isPlaceAvailable(x,y)){
                    if(domino.isDownSide() && isPlaceAvailable(x+1,y)){
                        _arrayTiles[x][y] = domino.getTile()[0][0];
                        _arrayTiles[Math.min(x+1, _arrayTiles.length-1)][y] = domino.getTile()[1][0];
                    }
                    else if(domino.isUpSide() && isPlaceAvailable(x-1,y)){
                        _arrayTiles[x][y] = domino.getTile()[0][0];
                        _arrayTiles[Math.min(x-1, _arrayTiles.length-1)][y] = domino.getTile()[1][0];
                    }
                }

                else if(isCastleHere(x,y-1) && isPlaceAvailable(x,y)){
                    if(domino.isDownSide() && isPlaceAvailable(x+1,y)){
                        _arrayTiles[x][y] = domino.getTile()[0][0];
                        _arrayTiles[Math.min(x+1, _arrayTiles.length-1)][y] = domino.getTile()[1][0];
                    }
                    else if(domino.isUpSide() && isPlaceAvailable(x-1,y)){
                        _arrayTiles[x][y] = domino.getTile()[0][0];
                        _arrayTiles[Math.min(x-1, _arrayTiles.length-1)][y] = domino.getTile()[1][0];
                    }
                }
            }
        }

        //Check if a compatible Tile is available near pos x y
        else if(isSameTile(_arrayTiles[Math.max(x-1, 0)][y], domino.getTile()[0][0]) || isSameTile(_arrayTiles[Math.min(x+1, _arrayTiles.length-1)][y], domino.getTile()[0][0])
        || isSameTile(_arrayTiles[x][Math.max(y-1, 0)], domino.getTile()[0][0]) || isSameTile(_arrayTiles[x][Math.min(y+1, _arrayTiles.length-1)], domino.getTile()[0][0])){

            if(domino.isXX() && (isSameTile(_arrayTiles[x][Math.max(y-1, 0)], domino.getTile()[0][0])
                    || isSameTile(_arrayTiles[x][Math.min(y+1, _arrayTiles.length-1)], domino.getTile()[0][0])
                         || isSameTile(_arrayTiles[Math.min(x+1, _arrayTiles.length-1)][y], domino.getTile()[0][0])
                            || isSameTile(_arrayTiles[Math.max(x-1, 0)][y], domino.getTile()[0][0]))){

                if(domino.isRightSide()){
                    if(isPlaceAvailable(x,y+1)){
                        _arrayTiles[x][y] = domino.getTile()[0][0];
                        _arrayTiles[x][Math.min(y+1, _arrayTiles.length-1)] = domino.getTile()[0][1];
                    }
                    else{
                        _errorMessage = "Not near a castle or a same tile !";
                    }
                }
                else if(domino.isLeftSide()){
                    if(isPlaceAvailable(x,y-1)){
                        _arrayTiles[x][y] = domino.getTile()[0][0];
                        _arrayTiles[x][Math.min(y-1, _arrayTiles.length-1)] = domino.getTile()[0][1];
                    }
                    else{
                        _errorMessage = "Not near a castle or a same tile !";
                    }
                }
            }
            else if(domino.isXY() && (isSameTile(_arrayTiles[x][Math.max(y-1, 0)], domino.getTile()[0][0])
                    || isSameTile(_arrayTiles[x][Math.min(y+1, _arrayTiles.length-1)], domino.getTile()[0][0])
                         || isSameTile(_arrayTiles[Math.min(x+1, _arrayTiles.length-1)][y], domino.getTile()[0][0])
                             || isSameTile(_arrayTiles[Math.max(x-1, 0)][y], domino.getTile()[0][0]))){

                if(domino.isUpSide()){
                    if(isPlaceAvailable(x-1,y)){
                        _arrayTiles[x][y] = domino.getTile()[0][0];
                        _arrayTiles[x-1][y] = domino.getTile()[1][0];
                    }
                    else{
                        _errorMessage = "Not near a castle or a same tile !";
                    }
                }
                else if(domino.isDownSide()){
                    if(isPlaceAvailable(x+1,y)){
                        _arrayTiles[x][y] = domino.getTile()[0][0];
                        _arrayTiles[x+1][y] = domino.getTile()[1][0];
                    }
                    else{
                        _errorMessage = "Not near a castle or a same tile !";
                    }
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
