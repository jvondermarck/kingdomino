package model.set;

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
    }

    public void executeNumberPlayers()
    {
        this._strategy.setNumberPlayers(this);
    }

    public void setNumberPlayers(int players)
    {
        this._numberPlayers = players;
    }

}
