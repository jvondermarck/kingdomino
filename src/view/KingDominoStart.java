package view;

import controller.Controller;
import model.Game;
import model.Observer;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

@SuppressWarnings("FieldCanBeLocal")
public class KingDominoStart implements Observer{

    private final JPanel _panelMain; // our main panel
    private final Window _window; // our window that we get thanks to his static method and singleton variable

    private final String[] _strategyPlayers; // array of strings which contains duo, trio, quatro about the amount of players
    private final JComboBox<String> _cboStrategys; // our combobox to select the strategy
    private final DefaultListCellRenderer listRenderer; // to make a horizontal alignement in our cbo

    private final JRadioButton _rdbGameModeHarmony;
    private final JRadioButton _rdbGameModeMiddle;
    private final JRadioButton _rdbGameNothing;

    private final JLabel _labelTitle;
    private final JLabel _labelTitlePlayer;
    private final JLabel _labelTitleMode;

    private Game _game;
    private Controller _controller;
    private JFrame _frame;
    private Font _fontGermania;
    private Font _fontTimeless;
    private Font _fontAugusta;

    public void getInstancesWindow()
    {
        _game = _window.getGame();
        _controller = _window.getController();
        _frame = _window.getFrame();
        _fontGermania = _window.getFontGermania();
        _fontTimeless = _window.getFontTimeless();
        _fontAugusta = _window.getFontAugusta();
    }

    private final JButton _btnValidate; // btn to validate all our the information to start the game

    public KingDominoStart() throws IOException {
        // SET UP THE WINDOW
        _window = Window._instance; // we get our main window to access to its variables
        getInstancesWindow();
        _frame.setTitle("Kingdomino");
        final ImageIcon icon = new ImageIcon(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("LaunchScreen.png")).readAllBytes());

        // Constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // MAIN PANEL : Panel where we'll find all panels and elements
        _panelMain = new JPanel() // display in this panel a background image to get a beautiful game
        {
            final Image img = icon.getImage();
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
        _labelTitle.setFont(_fontAugusta.deriveFont(Font.BOLD, 45));
        _labelTitle.setVisible(true);
        _labelTitle.setForeground(Color.decode("#FEBB13"));
        _panelMain.add(_labelTitle, constraints);

        // LABEL TITLE CHOOSE PLAYERS
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weighty = 0.1;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(0,100,10,50);
        _labelTitlePlayer = new JLabel("Choose the amount of players");
        _labelTitlePlayer.setFont(_fontGermania.deriveFont(Font.PLAIN, 27));
        _panelMain.add(_labelTitlePlayer, constraints);

        // COMBOBOX NUMBER OF PLAYERS
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weighty = 0.1;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(0,200,30,200);
        _strategyPlayers = new String[]{"Duo", "Trio", "Quatro"};
        _cboStrategys = new JComboBox<>(_strategyPlayers);
        _cboStrategys.setFont(_fontTimeless.deriveFont(Font.PLAIN, 15));
        _cboStrategys.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
        _labelTitleMode.setFont(_fontGermania.deriveFont(Font.PLAIN, 27));
        _panelMain.add(_labelTitleMode, constraints);

        // RADIO BUTTON NOTHING
        constraints.insets = new Insets(0,62,60,0);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        _rdbGameNothing = new JRadioButton("Nothing");
        _rdbGameNothing.setFont(_fontTimeless.deriveFont(Font.PLAIN, 17));
        _rdbGameNothing.setOpaque(false);
        _rdbGameNothing.setSelected(true);
        _rdbGameNothing.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        _panelMain.add(_rdbGameNothing, constraints);

        // RADIO BUTTON HARMONY
        constraints.insets = new Insets(0,10,60,0);
        constraints.gridx = 1;
        _rdbGameModeHarmony = new JRadioButton("Harmony");
        _rdbGameModeHarmony.setFont(_fontTimeless.deriveFont(Font.PLAIN, 17));
        _rdbGameModeHarmony.setOpaque(false);
        _rdbGameModeHarmony.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        _panelMain.add(_rdbGameModeHarmony, constraints);

        // RADIO BUTTON MIDDLE KINGDOM
        constraints.gridx = 2;
        constraints.insets = new Insets(0,10,60,0);
        _rdbGameModeMiddle = new JRadioButton("The middle Kingdom");
        _rdbGameModeMiddle.setFont(_fontTimeless.deriveFont(Font.PLAIN, 17));
        _rdbGameModeMiddle.setOpaque(false);
        _rdbGameModeMiddle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        _panelMain.add(_rdbGameModeMiddle, constraints);

        // BUTTON VALIDATE
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(0,165,8,150);
        _btnValidate = new JButton("Start");
        _btnValidate.setFocusable(false);
        _btnValidate.setFont(_fontGermania.deriveFont(Font.PLAIN, 30));
        _btnValidate.setOpaque(false);
        _btnValidate.setContentAreaFilled(false);
        _btnValidate.setBorderPainted(false);
        _btnValidate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        _panelMain.add(_btnValidate, constraints);

        // MAIN PANEL : We put all element in the main Panel
        _frame.setContentPane( _panelMain);
        _frame.setVisible( true );

        // PLAYERS COMBOBOX LISTENER : we put the strategy about the amount of players
        _btnValidate.addActionListener(actionEvent -> {
            if(_rdbGameModeHarmony.isSelected())
                _controller.callHarmony();

            if(_rdbGameModeMiddle.isSelected())
                _controller.callMiddleKingdom();

            /*
            if(_rdbGameNothing.isSelected() && !(_rdbGameModeMiddle.isSelected() || _rdbGameModeHarmony.isSelected()))
                System.out.println("No game mode");
             */

            if(_cboStrategys.getSelectedIndex() == 0) // If we selected in the combobox the "Duo"
                _controller.switchToDuo();
            else if(_cboStrategys.getSelectedIndex() == 1) // If we selected in the combobox the "Trio"
                _controller.switchToTrio();
            else // If we selected in the combobox the "Quatro"
                _controller.switchToQuatro();

            _window.setNumberPlayer(_game.getNumberPlayer());

            try {
                //new KingDominoGame(); // Because we got all we needed of the main player, we can start the game !
                _controller.instantiateKingdominoGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        _rdbGameNothing.addActionListener(actionEvent -> {
            if(_rdbGameNothing.isSelected()) // If the Nothing mode is selected we make impossible to select the other mods
            {
                _rdbGameModeHarmony.setEnabled(false);
                _rdbGameModeMiddle.setEnabled(false);
            } else {
                _rdbGameModeHarmony.setEnabled(true);
                _rdbGameModeMiddle.setEnabled(true);
            }
        });

        _rdbGameModeMiddle.addActionListener(actionEvent -> {
            _rdbGameNothing.setEnabled(!_rdbGameModeHarmony.isSelected() && !_rdbGameModeMiddle.isSelected());
            _rdbGameNothing.setSelected(!_rdbGameModeMiddle.isSelected() && !_rdbGameModeHarmony.isSelected());
        });

        _rdbGameModeHarmony.addActionListener(actionEvent -> {
            _rdbGameNothing.setEnabled(!_rdbGameModeHarmony.isSelected() && !_rdbGameModeMiddle.isSelected());
            _rdbGameNothing.setSelected(!_rdbGameModeMiddle.isSelected() && !_rdbGameModeHarmony.isSelected());
        });

        _window.setFrame(_frame);
    }

    @Override
    public void updateDominoesOnTable(Game game) {

    }

    @Override
    public void updateDominoPreview(Game game) {

    }

    @Override
    public void updatePlayerKingdom(Game game) {

    }
}
