package model.set.mode;

import model.entities.Player;
import model.set.GameMode;

public class Harmony extends GameMode {

    @Override
    public boolean executeGameMode(Player player) {
        return player.getGraph().isCompleted();
    }

    @Override
    public int numberBonus() {
        return 5;
    }

    @Override
    public boolean sizeGraph() {
        return false;
    }

    @Override
    public String toString()
    {
        return "Harmony";
    }
}
