package view;

import model.Game;
import model.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

@SuppressWarnings("FieldCanBeLocal")
public class KingDominoGame implements Observer {

    private final Window _window;
    private final JPanel _panelMain;
    private final JPanel _panelMainInfo;
    private JPanel _subPanelTitle;
    private JPanel _subPanelDominoes;
    private JPanel _subPanelRotation;
    private final JPanel _panelMainGraph;
    private JPanel _panelGridDominoes;
    private final JLabel _labelRound;
    private JPanel[] _panelHideDominoes;
    private JButton[] _btnHideDominoes;
    private JPanel[] _panelUnhideDominoes;
    private CardLayout[] cardLayout;
    private Container[] _container;
    private JButton[][] _btnTiles;
    private final JButton _btnShowDomino;
    private JPanel[] _panelGraph;
    private JButton[][] _btnOnGraph;
    private JPanel[] _panelAllGraphText;
    private JTextField[] _textNamePlayer;

    public KingDominoGame()
    {
        _window = Window.instance; // we get our main window to access to its variables
        _window.frame.setTitle("Game Kingdomino");
        _window.frame.getContentPane().removeAll();
        _window.frame.repaint();
        _window.frame.setSize(1100,600);
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
            final Image img = icon.getImage();
            // instance initializer
            {setOpaque(false);}
            public void paintComponent(Graphics graphics)
            {
                graphics.drawImage(img, 0, 0, this);
                super.paintComponent(graphics);
            }
        };
        _panelMainInfo.setLayout( new GridBagLayout() );
        _panelMainInfo.setPreferredSize(new Dimension(430, 600));

        // LABEL TITLE WELCOME
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(20,0,0,0);
        _labelRound = new JLabel("ROUND");
        _labelRound.setFont(_window._fontGermania.deriveFont(Font.PLAIN, 48));
        _labelRound.setVisible(true);
        _panelMainInfo.add(_labelRound, constraints);

        // We create the four dominoes
        createDominoes();
        constraints.gridy = 1;
        constraints.insets = new Insets(10,0,0,0);
        _panelMainInfo.add(_panelGridDominoes, constraints);

        // We create the button to show all the dominoes
        constraints.gridy = 2;
        constraints.insets = new Insets(10,0,0,0);
        _btnShowDomino = new JButton("Show dominoes");
        _btnShowDomino.setFocusable(false);
        _btnShowDomino.setFont(_window._fontTimeless.deriveFont(Font.PLAIN, 20));
        _btnShowDomino.setForeground(Color.WHITE);
        _btnShowDomino.setBackground(Color.decode("#DDAB40"));
        _btnShowDomino.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        _panelMainInfo.add(_btnShowDomino, constraints);

        // GRAPH PANEL : panels which will contains 2,3 or 4 panels depending on how many players will play
        _panelMainGraph = new JPanel( new GridLayout(2,2));
        _panelMainGraph.setPreferredSize(new Dimension(670, 600));
        _panelMainGraph.setBackground(Color.WHITE);
        createGraph();

        // MAIN PANEL : We put element in the main Panel
        _panelMain.add(_panelMainInfo, BorderLayout.WEST);
        _panelMain.add(_panelMainGraph, BorderLayout.EAST);

        _window.frame.setContentPane( _panelMain);
        _window.frame.setVisible( true );

        // SHOW DOMONOES BUTTON LISTENER : we unhidde the four buttons
        _btnShowDomino.addActionListener(actionEvent -> {
            for(int i=0; i<cardLayout.length; i++)
            {
                cardLayout[i].next(_container[i]); // when we click oon the button the four domino will show the two tiles of each domino
                System.out.println(i);
            }
        });
    }

    @Override
    public void update(Game game) {

    }

    public void createDominoes()
    {
        int _numberDomino = 4; // we will have 4 dominoes
        cardLayout = new CardLayout[4]; // our card layout
        _container = new Container[4]; // our container

        // We create an instance of grid layout just to put a margin between each domino (vgap =  the vertical gap)
        GridLayout gridLayout = new GridLayout(4, 1, 0, 10);
        _panelGridDominoes = new JPanel(gridLayout); // we add the layout in our main panel which will contain our CardLayout panel
        _panelGridDominoes.setOpaque(false);

        _panelHideDominoes = new JPanel[4]; // array of panels which will contain one button for each domino (hidden domino)
        _btnHideDominoes = new JButton[4]; // array of button which wil contain one button for each hidden domino

        _btnTiles = new JButton[4][2]; // array of button for each domino which will contains 2 tiles (one domino = 2 tiles)
        _panelUnhideDominoes = new JPanel[4]; // array of panel to show the two tiles for each domino (unhidden domino)

        // LOOP which will create an array of panels, to put one single button inside (to show a hidden domino)
        for(int i=0; i<_numberDomino; i++)
        {
            // We create a panel and a button that we put in an array
            _panelHideDominoes[i] = new JPanel( new GridLayout(1,2));
            _btnHideDominoes[i] = new JButton(Integer.toString(i));

            _btnHideDominoes[i].setPreferredSize(new Dimension(170, 85));
            _btnHideDominoes[i].setBackground(Color.decode("#3C3C3C"));
            _btnHideDominoes[i].setForeground(Color.WHITE);
            _btnHideDominoes[i].setFocusable(false);

            // We add a listenner which will show the unhide domino with the 2 tiles
            int finalI = i;
            _btnHideDominoes[i].addActionListener(clickevent -> {
                cardLayout[finalI].next(_container[finalI]); // when we click of one of the four domino, it will call the next card layout
                System.out.println(finalI);
            });

            // We add the button[i] on the array of the hidden dominoes[i]
            _panelHideDominoes[i].add(_btnHideDominoes[i]);
        }

        // LOOP : which will create an array o panels, the unhidden domino with its 2 tiles
        for(int i=0; i<_numberDomino; i++)
        {
            // We create the panel for each domino (cols = 2 because we have two tiles)
            _panelUnhideDominoes[i] = new JPanel( new GridLayout(1,2));

            // We add two buttons in our panel[i]
            _btnTiles[i][0] = new JButton(0 + "" + i);
            _btnTiles[i][1] = new JButton(1 + "" + i);

            _btnTiles[i][0].setFocusable(false);
            _btnTiles[i][1].setFocusable(false);

            _panelUnhideDominoes[i].add( _btnTiles[i][0]);
            _panelUnhideDominoes[i].add( _btnTiles[i][1]);

            // When we click of the left tile :
            int finalI = i;
            _btnTiles[i][0].addActionListener(clickevent -> System.out.println("0" + finalI));
            // When we click of the right tile :
            _btnTiles[i][1].addActionListener(clickevent -> System.out.println("1" + finalI));
        }

        for(int i=0; i<_numberDomino; i++)
        {
            cardLayout[i] = new CardLayout();
            _container[i] = new Container();
            _container[i].setLayout(cardLayout[i]);

            // We add in the container (our stack) at the first place the hidden dominoes, and at the 2nd place the unhidden dominoes
            _container[i].add(_panelHideDominoes[i]);
            _container[i].add(_panelUnhideDominoes[i]);

            // We add in our main panel the container
            _panelGridDominoes.add(_container[i]);
        }
    }

    public void createGraph()
    {
        int numberPlayers = _window.numberPlayer;

        _panelAllGraphText = new JPanel[numberPlayers];
        _panelGraph = new JPanel[numberPlayers];
        _btnOnGraph = new JButton[numberPlayers][25];
        _textNamePlayer = new JTextField[numberPlayers];

        // Constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        int width = 335;
        int height = 300;
        int numGraph = 0;

        for(int i=0; i<numberPlayers; i++)
        {
            // NAME OF EACH PLAYER
            _panelAllGraphText[i] = new JPanel( new GridBagLayout());
            _panelAllGraphText[i].setBackground(Color.WHITE);

            _textNamePlayer[i] = new JTextField("Kingdom of : " + (i+1));
            _textNamePlayer[i].setFont(_window._fontGermania.deriveFont(Font.PLAIN, 20));
            _textNamePlayer[i].setOpaque(false);
            _textNamePlayer[i].setBorder(null);
            _textNamePlayer[i].setHorizontalAlignment(JTextField.CENTER);
            int finalI = i;
            _textNamePlayer[i].addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    _textNamePlayer[finalI].setText("");
                    _textNamePlayer[finalI].setEditable(true);
                    _textNamePlayer[finalI].getCaret().setVisible(true);
                }
             });
            _textNamePlayer[i].addMouseListener(new MouseAdapter(){
                @Override
                public void mouseExited(MouseEvent e){
                    if(Objects.equals(_textNamePlayer[finalI].getText(), ""))
                    {
                        _textNamePlayer[finalI].setText("Kingdom of : " + (finalI+1));
                    }
                    _textNamePlayer[finalI].setEditable(false);
                    _textNamePlayer[finalI].getCaret().setVisible(false);
                }
            });

            // THE 25 BUTTONS (our graph) FOR EACH PLAYER
            _panelGraph[i] = new JPanel(new GridLayout(5, 5));
            _panelAllGraphText[i].setPreferredSize(new Dimension(207, 207));

            numGraph++;
            for(int j=0; j<25; j++)
            {
                _btnOnGraph[i][j] = new JButton(Integer.toString(j));
                _btnOnGraph[i][j].setFont(new Font("Serif", Font.PLAIN, 8));
                _btnOnGraph[i][j].setPreferredSize(new Dimension(42, 42));
                _btnOnGraph[i][j].setBackground(Color.decode("#CECECE"));
                _btnOnGraph[i][j].setRolloverEnabled(false);
                _panelGraph[i].add(_btnOnGraph[i][j]);

                int finalNumGraph = numGraph;
                int finalJ = j;
                _btnOnGraph[i][j].addActionListener(actionEvent -> {
                        System.out.println(finalNumGraph + " " + finalJ);
                });
            }

            _btnOnGraph[i][12].setIcon(new ImageIcon("img/castle1.png"));

            if(i==1){
                _panelAllGraphText[i].setPreferredSize(new Dimension(width*2, height));
            } else if(i==2) {
                _panelAllGraphText[i].setPreferredSize(new Dimension(width, height*2));
            } else if(i==3){
                _panelAllGraphText[i].setPreferredSize(new Dimension(width*2, height*2));
            } else {
                _panelAllGraphText[i].setPreferredSize(new Dimension(width, height));
            }

            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.insets = new Insets(10,0,0,0);
            _panelAllGraphText[i].add(_textNamePlayer[i], constraints);

            constraints.gridy = 1;
            constraints.gridwidth = 1;
            constraints.insets = new Insets(10,0,10,0);
            _panelAllGraphText[i].add(_panelGraph[i], constraints);

            _panelMainGraph.add(_panelAllGraphText[i]);
        }
    }
}
