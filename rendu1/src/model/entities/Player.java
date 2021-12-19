package model.entities;

public class Player {

    private King _king;
    private Graph _graph;
    private Castle _castle;

    public Player()
    {
        this._king = new King();
        initialiseCastle();
        initialiseGraph();
    }

    // Le player est sensé faire ça ou ça serai de l'over engineering de passer par une classe intermédiaire ?
    private void initialiseGraph(){
        this._graph = new Graph();
    }
    // Le player est sensé faire ça ou ça serai de l'over engineering de passer par une classe intermédiaire ?
    private void initialiseCastle(){
        this._castle = new Castle(this._king.color());
    }

    public void setCastle(int x, int y){
        this._graph.setCastle(x, y, this._castle);
    }

    public Graph getGraph(){
        return this._graph;
    }

    public KINGCOLOR getKing() {
        return _king.getKingColor();
    }
}
