package model.set;

import model.set.number.Duo;
import model.set.number.Quatro;
import model.set.number.Trio;

public class NumberPlayer {
    private int _numberPlayers;
    private NumberPlayerStrategy _strategy;

    public NumberPlayer()
    {
        this._numberPlayers = 0;
        _strategy = null;
    }

    public void setStrategy(NumberPlayerStrategy strategy)
    {
        this._strategy = strategy;
        this._strategy.setNumberPlayers(this);
    }

    public void setNumber(int players)
    {
        this._numberPlayers = players;
    }

    public int getNumberPlayers()
    {
        return _numberPlayers;
    }

    public Duo getTwoPlayers()
    {
        return new Duo();
    }

    public Trio getThreePlayers()
    {
        return new Trio();
    }

    public Quatro getQuatroPlayers()
    {
        return new Quatro();
    }

}
