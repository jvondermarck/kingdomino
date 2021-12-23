package model.entities;

import model.set.GameMode;
import model.set.mode.Harmony;
import model.set.mode.MiddleKingdom;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final King _king;
    private Graph _graph;
    private Castle _castle;
    private Integer totalScore;
    private List<List<Integer>> yellowTilesScoreList = new ArrayList<>();
    private List<List<Integer>> darkGreenTilesScoreList = new ArrayList<>();
    private List<List<Integer>> blueTilesScoreList = new ArrayList<>();
    private List<List<Integer>> blackTilesScoreList = new ArrayList<>();
    private List<List<Integer>> brownTilesScoreList = new ArrayList<>();
    private List<List<Integer>> lightGreenTilesScoreList = new ArrayList<>();
    private final List<List<List<Integer>>> totalTilesScoreList = new ArrayList<>();

    private int yellowTilesScore = 0;
    private int darkGreenTilesScore = 0;
    private int blueTilesScore = 0;
    private int blackTilesScore = 0;
    private int brownTilesScore = 0;
    private int lightGreenTilesScore = 0;


    private final List<GameMode> _bonus = new ArrayList<>();

    public Player()
    {
        this._king = new King();
        initialiseCastle();
        initialiseGraph();
        totalScore = 0;
        totalTilesScoreList.add(yellowTilesScoreList);
        totalTilesScoreList.add(darkGreenTilesScoreList);
        totalTilesScoreList.add(blueTilesScoreList);
        totalTilesScoreList.add(blackTilesScoreList);
        totalTilesScoreList.add(brownTilesScoreList);
        totalTilesScoreList.add(lightGreenTilesScoreList);
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
        this.totalScore = 0;

        this.yellowTilesScore = calculateTotalScoreDomain(this.yellowTilesScoreList);
        this.darkGreenTilesScore = calculateTotalScoreDomain(this.darkGreenTilesScoreList);
        this.blueTilesScore = calculateTotalScoreDomain(this.blueTilesScoreList);
        this.blackTilesScore = calculateTotalScoreDomain(this.blackTilesScoreList);
        this.brownTilesScore = calculateTotalScoreDomain(this.brownTilesScoreList);
        this.lightGreenTilesScore = calculateTotalScoreDomain(this.lightGreenTilesScoreList);

        this.totalScore = this.yellowTilesScore + this.darkGreenTilesScore + this.blueTilesScore + this.blackTilesScore + this.brownTilesScore + this.lightGreenTilesScore;

        for(GameMode g : _bonus){
            totalScore += g.numberBonus();
        }

    }

    private int calculateTotalScoreDomain(List<List<Integer>> list){
        int score = 0;
        for (List<Integer> integers : list) {
            score += integers.get(0) * integers.get(1);
        }
        return score;
    }

    public void setYellowTilesScoreList(List<List<Integer>> yellowTilesScoreList){
        this.yellowTilesScoreList = yellowTilesScoreList;
    }

    public void setDarkGreenTilesScoreList(List<List<Integer>> darkGreenTilesScoreList){
        this.darkGreenTilesScoreList = darkGreenTilesScoreList;
    }

    public void setBlueTilesScoreList(List<List<Integer>> blueTilesScoreList){
        this.blueTilesScoreList = blueTilesScoreList;
    }

    public void setBlackTilesScoreList(List<List<Integer>> blackTilesScoreList){
        this.blackTilesScoreList = blackTilesScoreList;
    }

    public void setBrownTilesScoreList(List<List<Integer>> brownTilesScoreList){
        this.brownTilesScoreList = brownTilesScoreList;
    }

    public void setLightGreenTilesScoreList(List<List<Integer>> lightGreenTilesScoreList){
        this.lightGreenTilesScoreList = lightGreenTilesScoreList;
    }

    public int getYellowTilesScore() {
        return yellowTilesScore;
    }

    public int getDarkGreenTilesScore() {
        return darkGreenTilesScore;
    }

    public int getBlueTilesScore() {
        return blueTilesScore;
    }

    public int getBlackTilesScore() {
        return blackTilesScore;
    }

    public int getBrownTilesScore() {
        return brownTilesScore;
    }

    public int getLightGreenTilesScore() {
        return lightGreenTilesScore;
    }

    public void addBonus(GameMode gameMode){
        this._bonus.add(gameMode);
    }

    public int getHarmonyBonus()
    {
        int bonus = 0;
        for (GameMode gameMode : _bonus) {
            if (gameMode instanceof Harmony) {
                bonus = gameMode.numberBonus();
            }
        }
        return bonus;
    }

    public int getMiddleKingdom()
    {
        int bonus = 0;
        for (GameMode gameMode : _bonus) {
            if (gameMode instanceof MiddleKingdom) {
                bonus = gameMode.numberBonus();
            }
        }
        return bonus;
    }

}
