package model.set.mode;

import model.entities.Player;
import model.set.GameMode;

public class MightyDuel extends GameMode {
    @Override
    public boolean executeGameMode(Player player) {
        return false;
    }

    @Override
    public int numberBonus() {
        return 0;
    }

    @Override
    public String toString()
    {
        return "Migthy Duel";
    }
}
