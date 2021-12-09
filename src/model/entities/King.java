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
            index = RAND.nextInt(SIZE-1); // on choisis aleatoirement entre 0 et SIZE

        KINGCOLOR color = COLOR_USED.get(index); // On choisis dans la liste la couleur

        COLOR_USED.remove(index); // On enleve la couleur de la liste
        SIZE -= 1; // On réduit de -1 la taille de la liste vu qu'on enleve une couleur

        return color;
    }

    public String color()
    {
        return this._kingColor.toString();
    }
}
