package view;

import controller.Controller;
import model.Game;
import model.Observer;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Observer {
    protected Game _game;
    protected Controller _controller;
    protected JFrame frame;
    protected static Window instance;

    private Window(Game game, Controller controller){
        _game = game;
        _controller = controller;
        this.setWindow();
    }

    protected void setWindow(){
        frame = new JFrame("Kingdomino");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public static Window getInstance(Game game, Controller controller)
    {
        if(instance == null){
            instance = new Window(game, controller);
        }
        return instance;
    }

    @Override
    public void update(Game game) {

    }
}
