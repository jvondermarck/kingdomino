package model.entities;

public class Tile {
    private final int crowns;
    private String color;

    public Tile(int crowns, String color){
        this.crowns = crowns;
        this.color = color;
    }

    public Tile(){
        crowns = 0;
        color = "white";
    }

    public void setColorTile(String color){
        this.color = color;
    }

    public int getCrowns(){
        return this.crowns;
    }

    public String getColor(){
        return this.color;
    }

}
