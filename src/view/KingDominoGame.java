package view;

import model.Game;
import model.Observer;

public class KingDominoGame implements Observer {

    private Window _window;

    public KingDominoGame()
    {
        _window = Window.instance; // we get our main window to access to its variables
        _window.frame.setTitle("Game screen");
        _window.frame.getContentPane().removeAll();
        _window.frame.repaint();
        _window.frame.setSize(1000,800);
        _window.frame.setLocationRelativeTo(null);


    }

    @Override
    public void update(Game game) {

    }
}
