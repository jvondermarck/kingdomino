package model.set.number;
import model.set.NumberPlayer;
import model.set.NumberPlayerStrategy;

public class Quatro implements NumberPlayerStrategy {
    @Override
    public void setNumberPlayers(NumberPlayer player)
    {
        player.setNumber(4);
    }
}
