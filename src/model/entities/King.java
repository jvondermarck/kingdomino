package model.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class King{

    private static final ArrayList<KINGCOLOR> COLOR_USED = new ArrayList<>(Arrays.asList(KINGCOLOR.values())); // les couleurs déjà prises
    private static Random RAND = new Random();
    private static int SIZE = COLOR_USED.size();
    private final KINGCOLOR _kingColor;

    public King()
    {
        _kingColor = randomChose();
    }

    public KINGCOLOR randomChose()
    {
        int index = 0;
        if(SIZE>1)
            index = RAND.nextInt(SIZE-1); // we chose randomly a number between 0 and SIZE

        KINGCOLOR color = COLOR_USED.get(index); //We chose in the list the color

        COLOR_USED.remove(index); // We remove the color in the list
        SIZE -= 1; // We decrease -1 the size because we removed a color in the list

        return color;
     }

    public String color()
    {
        return this._kingColor.toString();
    }
}
