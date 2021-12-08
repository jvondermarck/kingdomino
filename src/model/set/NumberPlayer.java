package model.set;

public class NumberPlayer {
    private int numberPlayers;
    private NumberPlayerStrategy _strategy;

    public void setStrategy(NumberPlayerStrategy strategy)
    {
        this._strategy = strategy;
    }

    public void executeStrategy()
    {
        this._strategy.setNumberPlayers(this);
    }

    public void setNumberPlayers(int players)
    {
        this.numberPlayers = players;
    }


}
