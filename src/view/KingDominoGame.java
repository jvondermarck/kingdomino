package view;

import model.Game;
import model.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KingDominoGame implements Observer {

    private Window _window;
    private JPanel _panelMain;
    private JPanel _panelMainInfo;
    private JPanel _panelMainGraph;
    private JLabel _labelRound;
    private JPanel[] _panelCardDominoes;
    private JPanel[] _panelHideDominoes;
    private JButton[] _btnUnhideDominoes;
    private JPanel[] _panelUnhideDominoes;
    CardLayout card[] = new CardLayout[4];
    Container c[] = new Container[4];

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

        // MAIN PANEL : Panel where we'll find the two main panels which is the information about the game, and a panel which contains the graphs
        _panelMain = new JPanel( new BorderLayout());

        // INFORMATION PANEL : panel which will show the dominoes, how many round, display the dominoes, etc
        _panelMainInfo = new JPanel() // display in this panel a background image to get a beautiful game
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
        _panelMainInfo.setLayout( new GridBagLayout() );
        _panelMainInfo.setPreferredSize(new Dimension(434, 557));

        // LABEL TITLE WELCOME
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.weighty = 2;
        constraints.insets = new Insets(0,0,0,0);
        _labelRound = new JLabel("ROUND");
        _labelRound.setFont(_window._fontGermania.deriveFont(Font.PLAIN, 48));
        _labelRound.setVisible(true);
        _panelMainInfo.add(_labelRound, constraints);

        createDominoes();

        // GRAPH PANEL : panels which will contains 2,3 or 4 panels depending on how many players will play
        _panelMainGraph = new JPanel();
        _panelMainGraph.setPreferredSize(new Dimension(618, 557));

        // MAIN PANEL : We put element in the main Panel
        _panelMain.add(_panelMainInfo, BorderLayout.WEST);
        _panelMain.add(_panelMainGraph, BorderLayout.EAST);

        _window.frame.setContentPane( _panelMain);
        _window.frame.setVisible( true );
    }

    @Override
    public void update(Game game) {

    }

    public void createDominoes()
    {
        JButton _btnTiles[][] = new JButton[2][2];
        _panelHideDominoes = new JPanel[4];
        _btnUnhideDominoes = new JButton[4];
        for(int i=0; i<_panelHideDominoes.length; i++)
        {
            _panelHideDominoes[i] = new JPanel( new GridLayout(1,1));
            _btnUnhideDominoes[i] = new JButton(Integer.toString(i));
            _btnUnhideDominoes[i].setBackground(Color.BLACK);
            _btnUnhideDominoes[i].setForeground(Color.WHITE);
            int finalI = i;
            _btnUnhideDominoes[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent clickevent) {
                    card[finalI].next(c[finalI]);
                    System.out.println(Integer.toString(finalI));
                }
            });
            _panelHideDominoes[i].add(_btnUnhideDominoes[i]);
        }

        _panelUnhideDominoes = new JPanel[4];
        for(int i=0; i<_panelUnhideDominoes.length; i++)
        {
            _panelUnhideDominoes[i] = new JPanel( new GridLayout(1,2));
            _btnTiles[0][0] = new JButton(0 + "" + i);
            _btnTiles[0][1] = new JButton(1 + "" + i);
            _panelUnhideDominoes[i].add( _btnTiles[0][0]);
            _panelUnhideDominoes[i].add( _btnTiles[0][1]);
            int finalI = i;
            _btnTiles[0][0].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent clickevent) {
                    System.out.println("0" + finalI);
                }
            });
            _btnTiles[0][1].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent clickevent) {
                    System.out.println("1" + finalI);
                }
            });
        }

        _panelCardDominoes = new JPanel[4];
        for(int i=0; i<_panelCardDominoes.length; i++)
        {
            _panelCardDominoes[i] = new JPanel( new CardLayout());
            card[i] = new CardLayout();
            c[i] = new Container();
            c[i].setLayout(card[i]);
            c[i].add(_panelHideDominoes[i]);
            c[i].add(_panelUnhideDominoes[i]);
            _panelMainInfo.add(c[i]);
        }


    }
}
