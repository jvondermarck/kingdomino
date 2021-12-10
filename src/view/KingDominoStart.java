package view;

import model.Game;
import model.Observer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.Window.*;

public class KingDominoStart implements Observer{

    private final String[] strategyPlayers;
    private final String[] gameMode;
    private final JComboBox _cboStrategys;
    private final JComboBox _cboGameMode;
    private JLabel _labelTitle;
    private JPanel _panelMain;
    private JPanel _panelComboBox;
    private JPanel _panelSet;
    private JButton _btnValidate;
    private static KingDominoStart instance;
    private Window _window;

    private KingDominoStart()
    {
        _window = Window.instance; // we get our main window to access to its variables
        _window.frame.setTitle("Initialisation of the KingDomino");

        // Constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // MAIN PANEL : Panel where we'll find all panels and elements
        _panelMain = new JPanel();
        _panelMain.setLayout( new GridBagLayout() );

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weightx = 2;
        constraints.weighty = 2;
        constraints.insets = new Insets(0,0,0,0); //top padding
        _labelTitle =  new JLabel("Bienvenue sur KingDomino", SwingConstants.CENTER);
        _labelTitle.setFont(new Font("Century Gothic", Font.PLAIN, 25));
        _labelTitle.setVisible(true);
        _panelMain.add(_labelTitle, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        strategyPlayers = new String[]{"Duo", "Trio", "Quatro"};
        _cboStrategys = new JComboBox(strategyPlayers);
        _cboStrategys.setMaximumSize(new Dimension(10,10));
        _panelMain.add(_cboStrategys, constraints);

        constraints.gridx = 1;
        gameMode = new String[]{"Harmony", "The middle Kingdom"};
        _cboGameMode = new JComboBox(gameMode);
        _panelMain.add(_cboGameMode, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(100,0,10,0); //top padding
        _btnValidate = new JButton("Validate your choices");
        _panelMain.add(_btnValidate, constraints);

        // MAIN PANEL : We put element in the main Panel
        _window.frame.setContentPane( _panelMain);
        _window.frame.setVisible( true );

        // PLAYERS COMBOBOX LISTENER : we put the strategy about the amount of players
        _cboStrategys.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {
                if(_cboStrategys.getSelectedIndex() == 0)
                    _window._controller.switchToDuo();
                else if(_cboStrategys.getSelectedIndex() == 1)
                    _window._controller.switchToTrio();
                else
                    _window._controller.switchToQuatro();
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
