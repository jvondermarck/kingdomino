package model.set;

public abstract class GameMode {
    private boolean _isSevenSize;
    private int _numberBonus;

    public GameMode()
    {
        this._numberBonus = 0;
        this._isSevenSize = false;
    }

    public void executeGameMode()
    {

    }

    public abstract int numberBonus();
    public abstract boolean sizeGraph();

}
