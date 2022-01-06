package model.set.mode;

import model.entities.Player;
import model.set.GameMode;

public class Harmony extends GameMode {

    @Override
    public boolean executeGameMode(Player player) {
        return player.getKingdom().isCompleted();
    }

    @Override
    public int numberBonus() {
        return 5;
    }

    @Override
    public String toString()
    {
        return "Harmony";
    }
}
