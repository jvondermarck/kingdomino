package view;

import controller.Controller;
import model.Game;
import utilities.FontUtilities;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Window extends JFrame {
    private final Game _game;
    private final Controller _controller;
    private JFrame _frame;
    public static Window _instance;
    private Font _fontGermania;
    private Font _fontTimeless;
    private Font _fontAugusta;
    private int _numberPlayer = 0;

    public Window(Game game, Controller controller) throws IOException {
        _game = game;
        _controller = controller;
        this.setWindow();
        _instance = this;
    }

    public void setWindow() throws IOException {
        _frame = new JFrame("Kingdomino");
        _frame.setSize(500, 500);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setLocationRelativeTo(null); // put window in the center of the screen
        ImageIcon img = new ImageIcon(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("ico.png")).readAllBytes());
        _frame.setIconImage(img.getImage()); // icon of the application
        _frame.setResizable(false); // make impossible to resize the window to avoid error or whatever
        _fontGermania = FontUtilities.setFont("Germania.otf"); // We use this for the user to access to our custom font
        _fontTimeless = FontUtilities.setFont("Timeless.ttf");
        _fontAugusta = FontUtilities.setFont("Augusta.ttf");
    }

    public Game getGame() {
        return _game;
    }

    public Controller getController() {
        return _controller;
    }

    public JFrame getFrame() {
        return _frame;
    }

    public static Window getInstance() {
        return _instance;
    }

    public Font getFontGermania() {
        return _fontGermania;
    }

    public Font getFontTimeless() {
        return _fontTimeless;
    }

    public Font getFontAugusta() {
        return _fontAugusta;
    }

    public int getNumberPlayer() {
        return _numberPlayer;
    }

    public void setNumberPlayer(int numberPlayer) {
        this._numberPlayer = numberPlayer;
    }

    public void setFrame(JFrame frame) {
        this._frame = frame;
    }
}
