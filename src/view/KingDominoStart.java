package view;

import model.Game;
import model.Observer;
import utilities.FontUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class KingDominoStart implements Observer{

    private final String[] strategyPlayers;
    private final JComboBox _cboStrategys;
    private final JRadioButton _rdbGameModeHarmony;
    private final JRadioButton _rdbGameModeMiddle;
    private JLabel _labelTitle;
    private JPanel _panelMain;
    private JButton _btnValidate;
    private static KingDominoStart instance;
    private Window _window;
    private Font _fontGermania;
    private Font _fontTimeless;

    private KingDominoStart() {
        // SET UP THE WINDOW
        _window = Window.instance; // we get our main window to access to its variables
        _window.frame.setTitle("Initialisation of the KingDomino");
        _window.frame.setResizable(false);
        final ImageIcon icon = new ImageIcon("img/kingdomino1.png");
        _fontGermania = FontUtilities.setFont("font/Germania.otf");
        _fontTimeless = FontUtilities.setFont("font/Timeless.ttf");

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
                graphics.drawImage(img, 0, 0, this);
                super.paintComponent(graphics);
            }
        };
        _panelMain.setLayout( new GridBagLayout() );

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.weighty = 2;
        constraints.insets = new Insets(0,0,0,30); //top padding
        _labelTitle =  new JLabel("Welcome to KingDomino", SwingConstants.CENTER);
        _labelTitle.setFont(_fontGermania.deriveFont(Font.PLAIN, 40));
        _labelTitle.setVisible(true);
        _panelMain.add(_labelTitle, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 0.1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0,50,0,200);
        strategyPlayers = new String[]{"Duo", "Trio", "Quatro"};
        _cboStrategys = new JComboBox(strategyPlayers);
        _cboStrategys.setFont(_fontTimeless.deriveFont(Font.PLAIN, 15));
        _cboStrategys.setMaximumSize(new Dimension(10,10));
        _panelMain.add(_cboStrategys, constraints);

        constraints.insets = new Insets(0,100,0,0);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        _rdbGameModeHarmony = new JRadioButton("Harmony");
        _panelMain.add(_rdbGameModeHarmony, constraints);

        constraints.gridx = 1;
        constraints.insets = new Insets(0,0,0,100);
        _rdbGameModeMiddle = new JRadioButton("The middle Kingdom");
        _panelMain.add(_rdbGameModeMiddle, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(100,150,10,150);
        _btnValidate = new JButton("Validate your choices");
        _panelMain.add(_btnValidate, constraints);

        // MAIN PANEL : We put element in the main Panel
        _window.frame.setContentPane( _panelMain);
        _window.frame.setVisible( true );

        // PLAYERS COMBOBOX LISTENER : we put the strategy about the amount of players
        _btnValidate.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                if(_rdbGameModeHarmony.isSelected())
                    _window._controller.callHarmony();
                if(_rdbGameModeMiddle.isSelected())
                    _window._controller.callMiddleKingdom();

                if(_cboStrategys.getSelectedIndex() == 0)
                    _window._controller.switchToDuo();
                else if(_cboStrategys.getSelectedIndex() == 1)
                    _window._controller.switchToTrio();
                else
                    _window._controller.switchToQuatro();

                _window._game.addObservers(new KingDominoGame());
            }
        });

    }

    public static KingDominoStart getInstance()
    {
        if(instance == null){
            instance = new KingDominoStart();
        }
        return instance;
    }

    @Override
    public void update(Game game) {

    }
}
