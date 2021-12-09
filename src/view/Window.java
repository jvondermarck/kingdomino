package view;

import controller.Controller;
import model.Game;
import model.Observer;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Observer {
    protected static Game _game;
    protected static Controller _controller;
    protected static JFrame frame;

    public Window(Game game, Controller controller){
        _game = game;
        _controller = controller;
        setWindow();
        game.addObservers(KingDominoStart.getInstance());
    }

    protected void setWindow(){
        frame = new JFrame("Kingdomino");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        setLocationRelativeTo(null);
    }

    @Override
    public void update(Game game) {

    }
}
