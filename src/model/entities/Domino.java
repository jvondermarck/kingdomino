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

    public void rotate(){

    }
}
