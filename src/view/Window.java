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
    private final Game _game;
    private final Controller _controller;
    private JFrame frame;
    public static Window instance;
    private Font _fontGermania;
    private Font _fontTimeless;
    private Font _fontAugusta;
    private int numberPlayer = 0;

    private Window(Game game, Controller controller) throws IOException {
        _game = game;
        _controller = controller;
        this.setWindow();
    }

    protected void setWindow() throws IOException {
        frame = new JFrame("Kingdomino");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // put window in the center of the screen
        ImageIcon img = new ImageIcon(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("ico.png")).readAllBytes());
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

    public Game get_game() {
        return _game;
    }

    public Controller get_controller() {
        return _controller;
    }

    public JFrame getFrame() {
        return frame;
    }

    public static Window getInstance() {
        return instance;
    }

    public Font get_fontGermania() {
        return _fontGermania;
    }

    public Font get_fontTimeless() {
        return _fontTimeless;
    }

    public Font get_fontAugusta() {
        return _fontAugusta;
    }

    public int getNumberPlayer() {
        return numberPlayer;
    }

    public void setNumberPlayer(int numberPlayer) {
        this.numberPlayer = numberPlayer;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
