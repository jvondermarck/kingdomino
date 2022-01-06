package model.set;

import model.set.mode.Harmony;
import model.set.mode.MiddleKingdom;
import model.set.mode.MightyDuel;

public class GameModeFactory {
    public GameMode createMiddleKingdom(){ return new MiddleKingdom();}
    public GameMode createHarmony(){ return new Harmony(); }
    public GameMode createMightyDuel() { return new MightyDuel(); }
}
