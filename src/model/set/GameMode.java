package model.set;
import model.entities.Player;

public abstract class GameMode {

    public GameMode() {}

    public abstract boolean executeGameMode(Player player);

    public abstract int numberBonus();
}
