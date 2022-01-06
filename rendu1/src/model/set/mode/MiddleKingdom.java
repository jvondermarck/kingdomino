package model.set.mode;
import model.set.GameMode;

public class MiddleKingdom extends GameMode {
    @Override
    public int numberBonus() {
        return 10;
    }

    @Override
    public boolean sizeGraph() {
        return false;
    }

    @Override
    public String toString()
    {
        return "Middle Kingdom";
    }
}
