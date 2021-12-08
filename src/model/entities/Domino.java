package model.entities;

public class Domino {
    private int id;
    private Tile _arrayTile[][];

    public Domino(int id, Tile tile1, Tile tile2){
        this.id = id;
        _arrayTile = new Tile[2][2];
        setTile(tile1, tile2);
    }

    public void setTile(Tile tile1, Tile tile2){
        //coordonnée arbitraire
        // O X
        // O X
        _arrayTile[0][0] = tile1;
        _arrayTile[0][1] = tile2;
    }

    public boolean isXX(){
        return this.getTile()[1][0] != null;
    }

    public boolean isXY(){
        return this.getTile()[0][1] != null;
    }

    public Tile[][] getTile(){
        return _arrayTile;
    }

    public void rotate(){
        if(this.isXX()){
            _arrayTile[0][1] = _arrayTile[1][0];
            _arrayTile[1][0] = null;
        }
        else{ // si this.XY == true
            _arrayTile[1][0] = _arrayTile[0][1];
            _arrayTile[0][1] = null;
        }
    }
}
