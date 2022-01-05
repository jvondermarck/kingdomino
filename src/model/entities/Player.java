package model.entities;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private final King _king;
    private Kingdom _kingdom;
    private Castle _castle;
    private Integer _totalScore;
    private List<List<Integer>> _yellowTilesScoreList = new ArrayList<>();
    private List<List<Integer>> _darkGreenTilesScoreList = new ArrayList<>();
    private List<List<Integer>> _blueTilesScoreList = new ArrayList<>();
    private List<List<Integer>> _blackTilesScoreList = new ArrayList<>();
    private List<List<Integer>> _brownTilesScoreList = new ArrayList<>();
    private List<List<Integer>> _lightGreenTilesScoreList = new ArrayList<>();
    private final List<List<List<Integer>>> _totalTilesScoreList = new ArrayList<>();

    private int _yellowTilesScore = 0;
    private int _darkGreenTilesScore = 0;
    private int _blueTilesScore = 0;
    private int _blackTilesScore = 0;
    private int _brownTilesScore = 0;
    private int _lightGreenTilesScore = 0;

    private int _bonus = 0;

    public Player()
    {
        this._king = new King();
        this._kingdom = new Kingdom();
        this._castle = new Castle(this._king.colorToString());

        _totalScore = 0;
        _totalTilesScoreList.add(_yellowTilesScoreList);
        _totalTilesScoreList.add(_darkGreenTilesScoreList);
        _totalTilesScoreList.add(_blueTilesScoreList);
        _totalTilesScoreList.add(_blackTilesScoreList);
        _totalTilesScoreList.add(_brownTilesScoreList);
        _totalTilesScoreList.add(_lightGreenTilesScoreList);
    }

    public void setCastle(int x, int y){
        this._kingdom.setCastle(x, y, this._castle);
    }

    public Kingdom getKingdom(){
        return this._kingdom;
    }

    public KINGCOLOR getKing() {
        return _king.getKingColor();
    }

    public Integer getTotalScore(){
        return this._totalScore;
    }

    public void calculateTotalScore(){
        this._totalScore = 0;

        this._yellowTilesScore = calculateTotalScoreDomain(this._yellowTilesScoreList);
        this._darkGreenTilesScore = calculateTotalScoreDomain(this._darkGreenTilesScoreList);
        this._blueTilesScore = calculateTotalScoreDomain(this._blueTilesScoreList);
        this._blackTilesScore = calculateTotalScoreDomain(this._blackTilesScoreList);
        this._brownTilesScore = calculateTotalScoreDomain(this._brownTilesScoreList);
        this._lightGreenTilesScore = calculateTotalScoreDomain(this._lightGreenTilesScoreList);

        this._totalScore = this._yellowTilesScore + this._darkGreenTilesScore + this._blueTilesScore + this._blackTilesScore + this._brownTilesScore + this._lightGreenTilesScore;

        /*
        for(GameMode g : _bonus){
            _totalScore += g.numberBonus();
        }
         */
        this._totalScore += _bonus;

    }

    private int calculateTotalScoreDomain(List<List<Integer>> list){
        int score = 0;
        for (List<Integer> integers : list) {
            score += integers.get(0) * integers.get(1);
        }
        return score;
    }

    public void setYellowTilesScoreList(List<List<Integer>> _yellowTilesScoreList){
        this._yellowTilesScoreList = _yellowTilesScoreList;
    }

    public void setDarkGreenTilesScoreList(List<List<Integer>> _darkGreenTilesScoreList){
        this._darkGreenTilesScoreList = _darkGreenTilesScoreList;
    }

    public void setBlueTilesScoreList(List<List<Integer>> _blueTilesScoreList){
        this._blueTilesScoreList = _blueTilesScoreList;
    }

    public void setBlackTilesScoreList(List<List<Integer>> _blackTilesScoreList){
        this._blackTilesScoreList = _blackTilesScoreList;
    }

    public void setBrownTilesScoreList(List<List<Integer>> _brownTilesScoreList){
        this._brownTilesScoreList = _brownTilesScoreList;
    }

    public void setLightGreenTilesScoreList(List<List<Integer>> _lightGreenTilesScoreList){
        this._lightGreenTilesScoreList = _lightGreenTilesScoreList;
    }

    public int getYellowTilesScore() {
        return _yellowTilesScore;
    }

    public int getDarkGreenTilesScore() {
        return _darkGreenTilesScore;
    }

    public int getBlueTilesScore() {
        return _blueTilesScore;
    }

    public int getBlackTilesScore() {
        return _blackTilesScore;
    }

    public int getBrownTilesScore() {
        return _brownTilesScore;
    }

    public int getLightGreenTilesScore() {
        return _lightGreenTilesScore;
    }

    public void addBonus(int b){
        _bonus += b;
    }

}
