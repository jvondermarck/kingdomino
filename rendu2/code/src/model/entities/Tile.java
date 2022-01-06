package model.entities;

public class Tile {
    private final int _crowns;
    private String _color;

    public Tile(int crowns, String color){
        this._crowns = crowns;
        this._color = color;
    }

    //For Castle
    public Tile(){
        _crowns = 0;
        _color = "white";
    }


    public void setColorTile(String color){
        this._color = color;
    }

    public int getCrowns(){
        return this._crowns;
    }

    public String getColor(){
        return this._color;
    }

}
