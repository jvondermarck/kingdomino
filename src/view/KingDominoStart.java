package view;

import model.Game;
import model.Observer;
import utilities.FontUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KingDominoStart implements Observer{

    private final String[] strategyPlayers;
    private final JComboBox _cboStrategys;
    private final JRadioButton _rdbGameModeHarmony;
    private final JRadioButton _rdbGameModeMiddle;
    private final JRadioButton _rdbGameNothing;
    private JLabel _labelTitle;
    private JLabel _labelTitlePlayer;
    private JLabel _labelTitleMode;
    private JPanel _panelMain;
    private JButton _btnValidate;
    private static KingDominoStart instance;
    private Window _window;
    private DefaultListCellRenderer listRenderer;

    private KingDominoStart() {
        // SET UP THE WINDOW
        _window = Window.instance; // we get our main window to access to its variables
        _window.frame.setTitle("Kingdomino");
        _window.frame.setResizable(false);
        final ImageIcon icon = new ImageIcon("img/LaunchScreen.png");

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

        // LABEL TITLE WELCOME
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.weighty = 3;
        constraints.insets = new Insets(0,0,10,0); //top padding
        _labelTitle =  new JLabel("Welcome to Kingdomino", SwingConstants.CENTER);
        _labelTitle.setFont(_window._fontGermania.deriveFont(Font.PLAIN, 40));
        _labelTitle.setVisible(true);
        _panelMain.add(_labelTitle, constraints);

        // LABEL TITLE CHOOSE PLAYERS
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 0.1;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(0,100,10,50);
        _labelTitlePlayer = new JLabel("Choose the amount of players");
        _labelTitlePlayer.setFont(_window._fontGermania.deriveFont(Font.PLAIN, 27));
        _panelMain.add(_labelTitlePlayer, constraints);

        // COMBOBOX NUMBER OF PLAYERS
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weighty = 0.1;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(0,200,30,200);
        strategyPlayers = new String[]{"Duo", "Trio", "Quatro"};
        _cboStrategys = new JComboBox(strategyPlayers);
        _cboStrategys.setFont(_window._fontTimeless.deriveFont(Font.PLAIN, 15));
        listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER); // center-aligned items
        _cboStrategys.setRenderer(listRenderer);
        _panelMain.add(_cboStrategys, constraints);

        // TITLE CHOOSE GAME MODE
        constraints.insets = new Insets(0,150,10,0);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.weighty = 0.1;
        constraints.gridwidth = 3;
        _labelTitleMode = new JLabel("Choose game mode");
        _labelTitleMode.setFont(_window._fontGermania.deriveFont(Font.PLAIN, 27));
        _panelMain.add(_labelTitleMode, constraints);

        // RADIO BUTTON NOTHING
        constraints.insets = new Insets(0,62,60,0);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        _rdbGameNothing = new JRadioButton("Nothing");
        _rdbGameNothing.setFont(_window._fontTimeless.deriveFont(Font.PLAIN, 17));
        _rdbGameNothing.setOpaque(false);
        _rdbGameNothing.setSelected(true);
        _panelMain.add(_rdbGameNothing, constraints);

        constraints.insets = new Insets(0,10,60,0);
        constraints.gridx = 1;
        _rdbGameModeHarmony = new JRadioButton("Harmony");
        _rdbGameModeHarmony.setFont(_window._fontTimeless.deriveFont(Font.PLAIN, 17));
        _rdbGameModeHarmony.setOpaque(false);
        _panelMain.add(_rdbGameModeHarmony, constraints);

        constraints.gridx = 2;
        constraints.insets = new Insets(0,10,60,0);
        _rdbGameModeMiddle = new JRadioButton("The middle Kingdom");
        _rdbGameModeMiddle.setFont(_window._fontTimeless.deriveFont(Font.PLAIN, 17));
        _rdbGameModeMiddle.setOpaque(false);
        _panelMain.add(_rdbGameModeMiddle, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(0,165,10,150);
        _btnValidate = new JButton("Start");
        _btnValidate.setFont(_window._fontGermania.deriveFont(Font.PLAIN, 30));
        _btnValidate.setOpaque(false);
        _btnValidate.setContentAreaFilled(false);
        _btnValidate.setBorderPainted(false);
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
                if(_rdbGameNothing.isSelected() && !(_rdbGameModeMiddle.isSelected() || _rdbGameModeHarmony.isSelected()))
                    System.out.println("No game mode");

                if(_cboStrategys.getSelectedIndex() == 0)
                    _window._controller.switchToDuo();
                else if(_cboStrategys.getSelectedIndex() == 1)
                    _window._controller.switchToTrio();
                else
                    _window._controller.switchToQuatro();

                _window._game.addObservers(new KingDominoGame());
            }
        });

        _rdbGameNothing.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                if(_rdbGameNothing.isSelected())
                {
                    _rdbGameModeHarmony.setEnabled(false);
                    _rdbGameModeMiddle.setEnabled(false);
                } else {
                    _rdbGameModeHarmony.setEnabled(true);
                    _rdbGameModeMiddle.setEnabled(true);
                }
            }
        });

        _rdbGameModeMiddle.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                _rdbGameNothing.setEnabled(!_rdbGameModeHarmony.isSelected() && !_rdbGameModeMiddle.isSelected());
            }
        });

        _rdbGameModeHarmony.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                _rdbGameNothing.setEnabled(!_rdbGameModeHarmony.isSelected() && !_rdbGameModeMiddle.isSelected());
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
