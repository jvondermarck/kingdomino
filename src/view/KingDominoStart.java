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
    private final JComboBox _cboStrategys;
    private JLabel _labelTitle;
    private JPanel _panelMain;
    private JPanel _panelTitle;
    private JPanel _panelSet;
    private static KingDominoStart instance;
    private Window _window;

    private KingDominoStart()
    {
        _window = Window.instance; // we get our main window to access to its variables
        _window.frame.setTitle("Initialisation of the KingDomino");

        // MAIN PANEL : Panel where we'll find all panels and elements
        _panelMain = new JPanel();
        _panelMain.setLayout( new BoxLayout(_panelMain, BoxLayout.Y_AXIS) );

        // TITLE AND INFO PANEL : Panel to display title and information about what to do
        _panelTitle = new JPanel();
        _panelTitle.setLayout( new BorderLayout() );
        _labelTitle =  new JLabel();
        _labelTitle.setText("Bienvenue sur KingDomino");
        _labelTitle.setVisible(true);
        _panelTitle.add(_labelTitle,  BorderLayout.NORTH);

        // SET PANEL : a panel to show all elements to start a game, numbers of players and game mode
        _panelSet = new JPanel();
        _panelSet.setLayout( new GridLayout(3,1) );
        strategyPlayers = new String[]{"Duo", "Trio", "Quatro"};
        _cboStrategys = new JComboBox(strategyPlayers);
        _panelSet.add(_cboStrategys);

        // MAIN PANEL : We put element in the main Panel
        _panelMain.add(_panelTitle);
        _panelMain.add(_panelSet);
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
