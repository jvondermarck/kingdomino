package model.entities;

import java.util.*;

public class King{

    private static final ArrayList<KINGCOLOR> _colorUsed = new ArrayList<>(Arrays.asList(KINGCOLOR.values())); // les couleurs déjà prises
    private static Random _rand = new Random();
    private static int _size = _colorUsed.size();
    private final KINGCOLOR _kingColor;

    public King()
    {
        Collections.shuffle(_colorUsed);
        _kingColor = randomChose();
    }

    private KINGCOLOR randomChose()
    {
        int index = 0;
        if(_size>1)
            index = _rand.nextInt(_size-1); // we chose randomly a number between 0 and SIZE

        KINGCOLOR color = _colorUsed.get(index); //We chose in the list the color

        _colorUsed.remove(index); // We remove the color in the list
        _size -= 1; // We decrease -1 the size because we removed a color in the list

        return color;
     }

    public KINGCOLOR getKingColor() {
        return _kingColor;
    }

    public String colorToString()
    {
        return this._kingColor.toString();
    }
}
