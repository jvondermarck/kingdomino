package model.entities;

public class Domino {
    private final int id;
    private final Tile[][] _arrayTile;

    public Domino(int id, Tile tile1, Tile tile2){
        this.id = id;
        _arrayTile = new Tile[2][2];
        setTile(tile1, tile2);
    }

    private void setTile(Tile tile1, Tile tile2){
        //coordonnée arbitraire
        // O X
        // O X
        _arrayTile[0][0] = tile1;
        _arrayTile[0][1] = tile2;
    }

    public boolean isXX(){
        return this.getTile()[1][0] == null;
    }

    public boolean isXY(){
        return this.getTile()[0][1] == null;
    }

    public Tile[][] getTile(){
        return _arrayTile;
    }

    public Integer getId(){
        return this.id;
    }

    public void rotate(){
        if(this.isXX()){
            _arrayTile[1][0] = _arrayTile[0][1];
            _arrayTile[0][1] = null;
        }
        else if(this.isXY()){
            _arrayTile[0][1] = _arrayTile[1][0];
            _arrayTile[1][0] = null;
        }
    }

    public void reverse()
    {
        Tile temp = _arrayTile[0][0];
        if(this.isXX()){
            _arrayTile[0][0] = _arrayTile[0][1];
            _arrayTile[0][1] = temp;
        }
        else if(this.isXY()){
            _arrayTile[0][0] = _arrayTile[1][0];
            _arrayTile[1][0] = temp;
        }
    }

    public String getColor(int x, int y)
    {
        return _arrayTile[x][y].getColor();
    }
}
