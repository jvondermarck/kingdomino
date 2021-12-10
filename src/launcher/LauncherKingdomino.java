package launcher;

import controller.Controller;
import model.Game;
import view.KingDominoStart;
import view.Window;

public class LauncherKingdomino {
    public static void main(String[] args) {
        Game game = Game.getInstance();
        Controller controller = new Controller(game);
        game.addObservers(Window.getInstance(game, controller));
        game.addObservers(KingDominoStart.getInstance());
    }
}
