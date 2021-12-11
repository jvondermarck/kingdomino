package view;

import model.Game;
import model.Observer;

import javax.swing.*;
import java.awt.*;

public class KingDominoGame implements Observer {

    private Window _window;
    private JPanel _panelMain;

    public KingDominoGame()
    {
        _window = Window.instance; // we get our main window to access to its variables
        _window.frame.setTitle("Game Kingdomino");
        _window.frame.getContentPane().removeAll();
        _window.frame.repaint();
        _window.frame.setSize(1051,557);
        _window.frame.setLocationRelativeTo(null);

        final ImageIcon icon = new ImageIcon("img/MainScreen.png");

        // Constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // MAIN PANEL : Panel where we'll find all panels and elements
        _panelMain = new JPanel() // display in this panel a background image to get a beautiful game
        {
            Image img = icon.getImage();
            // instance initializer
            {setOpaque(false);}
            public void paintComponent(Graphics graphics)
            {
                graphics.drawImage(img, 0, -35, this);
                super.paintComponent(graphics);
            }
        };
        _panelMain.setLayout( new GridBagLayout() );



        // MAIN PANEL : We put element in the main Panel
        _window.frame.setContentPane( _panelMain);
        _window.frame.setVisible( true );
    }

    @Override
    public void update(Game game) {

    }
}
