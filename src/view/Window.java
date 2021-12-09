package view;

import controller.Controller;
import model.Game;
import model.Observer;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Observer {
    private Game _game;
    private Controller _controller;
    private JFrame frame;

    public Window(Game game, Controller controller){
        this._game = game;
        this._controller = controller;
        setWindow();
    }

    public void setWindow(){
        this.frame = new JFrame("Kingdomino");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }


    @Override
    public void update(Game game) {

    }
}
