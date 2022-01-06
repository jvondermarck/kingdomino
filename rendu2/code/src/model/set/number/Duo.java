package model.set.number;

import model.set.NumberPlayer;
import model.set.NumberPlayerStrategy;

public class Duo implements NumberPlayerStrategy {
    @Override
    public void setNumberPlayers(NumberPlayer player)
    {
        player.setNumber(2);
    }

    @Override
    public String toString()
    {
        return "Duo";
    }
}
