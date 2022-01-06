package model.set.number;
import model.set.NumberPlayer;
import model.set.NumberPlayerStrategy;

public class Trio implements NumberPlayerStrategy {
    @Override
    public void setNumberPlayers(NumberPlayer player)
    {
        player.setNumber(3);
    }
}
