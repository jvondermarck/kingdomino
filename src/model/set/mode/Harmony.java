package model.set.mode;

import model.set.GameMode;

public class Harmony extends GameMode {

    @Override
    public int numberBonus() {
        return 5;
    }

    @Override
    public boolean sizeGraph() {
        return false;
    }
}
