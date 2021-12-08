package model.entities;

public class Player {

    private King[] _king;

    public Player(int numberKing)
    {
        if(numberKing==2)
            _king = new King[2];
        else
            _king = new King[1];

        for(int i=0; i<numberKing; i++)
        {
            _king[i] = new King();
        }
    }

}
