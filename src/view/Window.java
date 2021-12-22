package view;

import controller.Controller;
import model.Game;
import model.Observer;
import utilities.FontUtilities;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Window extends JFrame implements Observer {
    protected Game _game;
    protected Controller _controller;
    protected JFrame frame;
    protected static Window instance;
    protected ImageIcon img = new ImageIcon(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("ico.png")).readAllBytes());
    protected Font _fontGermania;
    protected Font _fontTimeless;
    protected Font _fontAugusta;
    protected int numberPlayer = 0;

    private Window(Game game, Controller controller) throws IOException {
        _game = game;
        _controller = controller;
        this.setWindow();
    }

    protected void setWindow(){
        frame = new JFrame("Kingdomino");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // put window in the center of the screen
        frame.setIconImage(img.getImage()); // icon of the application
        frame.setResizable(false); // make impossible to resize the window to avoid error or whatever
        _fontGermania = FontUtilities.setFont("Germania.otf"); // We use this for the user to access to our custom font
        _fontTimeless = FontUtilities.setFont("Timeless.ttf");
        _fontAugusta = FontUtilities.setFont("Augusta.ttf");
    }

    public static Window getInstance(Game game, Controller controller) throws IOException {
        if(instance == null){
            instance = new Window(game, controller);
        }
        return instance;
    }

    @Override
    public void update(Game game) {

    }
    @Override
    public void rotationDomino(Game game) {

    }

    @Override
    public void dominoGraph(Game game) {

    }
}
