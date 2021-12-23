package model.entities;

import model.Game;
import model.set.GameMode;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private King _king;
    private Graph _graph;
    private Castle _castle;
    private Integer totalScore;
    private List<List<Integer>> yellowTilesScore = new ArrayList<>();
    private List<List<Integer>> darkGreenTilesScore = new ArrayList<>();
    private List<List<Integer>> blueTilesScore = new ArrayList<>();
    private List<List<Integer>> blackTilesScore = new ArrayList<>();
    private List<List<Integer>> brownTilesScore = new ArrayList<>();
    private List<List<Integer>> lightGreenTilesScore = new ArrayList<>();
    private final List<List<List<Integer>>> totalTilesScore = new ArrayList<>();
    private final List<GameMode> _bonus = new ArrayList<>();

    public Player()
    {
        this._king = new King();
        initialiseCastle();
        initialiseGraph();
        totalScore = 0;
        totalTilesScore.add(yellowTilesScore);
        totalTilesScore.add(darkGreenTilesScore);
        totalTilesScore.add(blueTilesScore);
        totalTilesScore.add(blackTilesScore);
        totalTilesScore.add(brownTilesScore);
        totalTilesScore.add(lightGreenTilesScore);
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

    public Integer getTotalScore(){
        return this.totalScore;
    }

    public void calculateTotalScore(){
        totalScore = 0;

        for(int i = 0; i < totalTilesScore.size(); i++){
            for(int j = 0; j < totalTilesScore.get(i).size(); j++){
                totalScore += totalTilesScore.get(i).get(j).get(0) *  totalTilesScore.get(i).get(j).get(1);
            }
        }

        for(GameMode g : _bonus){
            totalScore += g.numberBonus();
        }
    }

    public void setYellowTilesScore(List<List<Integer>> yellowTilesScore){
        this.yellowTilesScore = yellowTilesScore;
    }

    public void setDarkGreenTilesScore(List<List<Integer>> darkGreenTilesScore){
        this.darkGreenTilesScore = darkGreenTilesScore;
    }

    public void setBlueTilesScore(List<List<Integer>> blueTilesScore){
        this.blueTilesScore = blueTilesScore;
    }

    public void setBlackTilesScore(List<List<Integer>> blackTilesScore){
        this.blackTilesScore = blackTilesScore;
    }

    public void setBrownTilesScore(List<List<Integer>> brownTilesScore){
        this.brownTilesScore = brownTilesScore;
    }

    public void setLightGreenTilesScore(List<List<Integer>> lightGreenTilesScore){
        this.lightGreenTilesScore = lightGreenTilesScore;
    }

    public List<List<Integer>> getDarkGreenTilesScore(){
        return this.darkGreenTilesScore;
    }

    public List<List<Integer>> getBlueTilesScore(){
        return this.blueTilesScore;
    }

    public List<List<Integer>> getBlackTilesScore(){
        return this.blackTilesScore;
    }

    public List<List<Integer>> getBrownTilesScore(){
        return this.brownTilesScore;
    }

    public List<List<Integer>> getLightGreenTilesScore(){
        return this.lightGreenTilesScore;
    }

    public void addBonus(GameMode gameMode){
        this._bonus.add(gameMode);
    }

    public List<GameMode> getBonus(){
        return this._bonus;
    }

}
