package model.entities;

//Peut être faire des méthodes abstraite car certaine fonction
//seront hérité à Castle alors qu'elle serve à rien

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
