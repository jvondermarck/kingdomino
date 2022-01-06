package model.entities;

public class Domino {
    private final int id;
    private final Tile[][] _arrayTile;
    private boolean rightSide = true;
    private boolean leftSide = false;
    private boolean upSide = false;
    private boolean downSide = false;

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

    public void setRightSide(){
        if(isXX()){
            rightSide = true;
            leftSide = false;
            upSide = false;
            downSide = false;
        }
    }

    public void setLeftSide(){
        if(isXX()){
            upSide = false;
            downSide = false;
            rightSide = false;
            leftSide = true;
        }
    }

    public void setUpSide(){
        if(isXY()){
            upSide = true;
            downSide = false;
            rightSide = false;
            leftSide = false;
        }
    }

    public void setDownSide(){
        if(isXY()){
            upSide = false;
            downSide = true;
            rightSide = false;
            leftSide = false;
        }
    }

    public boolean isRightSide(){
        return this.rightSide;
    }

    public boolean isLeftSide(){
        return this.leftSide;
    }

    public boolean isUpSide(){
        return this.upSide;
    }

    public boolean isDownSide(){
        return this.downSide;
    }

    public String getColor(int x, int y)
    {
        return _arrayTile[x][y].getColor();
    }
}
