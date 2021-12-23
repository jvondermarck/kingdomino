package model.set;
import model.Game;
import model.entities.Player;
import model.set.mode.*;

public abstract class GameMode {
    private boolean _isSevenSize;
    private int _numberBonus;

    public GameMode()
    {
        this._numberBonus = 0;
        this._isSevenSize = false;
    }

    public abstract boolean executeGameMode(Player player);

    public abstract int numberBonus();
    public abstract boolean sizeGraph();
}
