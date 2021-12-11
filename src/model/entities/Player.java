package model.entities;

public class Player {

    private King _king;
    private Graph _graph;
    private Castle _castle;

    public Player()
    {
        this._king = new King();
    }

    public void initialiseGraph(){
        this._graph = new Graph();
    }

    public void initialiseCastle(){
        this._castle = new Castle(this._king.color());
    }

    public void setCastle(int x, int y){
        this._graph.setCastle(x, y, this._castle);
    }
}
