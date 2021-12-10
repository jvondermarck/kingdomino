package model.set.mode;

import model.set.GameMode;

public class MightyDuel extends GameMode {
    @Override
    public int numberBonus() {
        return 0;
    }

    @Override
    public boolean sizeGraph() {
        return true;
    }

    @Override
    public String toString()
    {
        return "Migthy Duel";
    }
}
