package launcher;

import controller.Controller;
import model.Game;
import view.KingDominoStart;
import view.Window;

import java.io.IOException;

public class LauncherKingdominoRendu1 {
    public static void main(String[] args) throws IOException {
        Game game = Game.getInstance();
        Controller controller = new Controller(game);
        game.addObservers(Window.getInstance(game, controller));
        game.addObservers(new KingDominoStart());
    }
}
