package view;

import model.Game;
import model.Observer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.*;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class KingDominoGame implements Observer {

    private final Window _window; // our window that we get thanks to his static method and singleton variable

    private final JPanel _panelMain; // Our main panel which will contains two panels inside : _panelMainInfo &
    private final JPanel _panelMainInfo; // it will contain 3 panels (_panelMainInfoTop, LEFT, RIGHT) to show the titles, the 4 dominoes, a button to show it, and a system to rotate a domino. It will be showed at the left of the window
    private final JPanel _panelMainInfoTop; // Contains labels
    private final JPanel _panelMainInfoLeft; // Contains the OverLayout to put OVER of each domino the player who selected the domino AND contains the : _panelLeftDominoes
    private final JPanel _panelLeftDominoes; // It will contain the _panelGridDominoes AND _panelLabelKing
    private final JPanel _panelLabelKing; // contains the 4 labels to put picture of the king inside
    private JPanel _panelGridDominoes; // a panel to put the 4 dominoes (will be put inside the _panelMainInfoLeft panel)
    private final JPanel _panelMainInfoRight; // Contains a mini graph 2*2 (contains _subPanelRotation) to rotate a domino + a button to inverse and rotate it
    private final JPanel _subPanelRotation; // Contains a mini graph 2*2 to rotate a domino (it will be in the _panelMainInfoRight panel)
    private final JPanel _panelMainGraph; // it will contain 2,3 or 4 graphs, depending on the amount of players, panel will be shown at the right of the window
    private JPanel[] _panelUnhideDominoes; // array of panel to show the two tiles for each domino (unhidden domino)
    private JPanel[] _panelHideDominoes; // array of panels which will contain one button for each domino (hidden domino)
    private JPanel[] _panelAllGraphText; // a panel to show a label about the name of the player AND the graph of 25 buttons (_panelGraph)
    private JPanel[] _panelGraph; // a panel to put 25 buttons in it (will be in the _panelAllGraphText panel)

    private final JLabel _labelRound; // the main title
    private final JLabel _textInformationToDo; // a title to put some information about what to do in the game
    private JLabel[] _lblKingPicture; // We will show at the top of each domino the player who selected the domino, to see better who selected what

    private JButton[] _btnHideDominoes; // array of button which wil contain one button for each hidden domino (it will be in the _panelMainInfoLeft panel)
    private JButton[][] _btnTiles; // array of button for each domino which will contains 2 tiles (one domino = 2 tiles) (it will be in the _panelMainInfoLeft panel)
    private final JButton _btnShowDomino; // a button to return the 4 dominoes (it will be in the _panelMainInfoLeft panel)
    private final JButton[] _rotateDomino; // an array of button, the index 0 = button to reverse and index = 1 to inverse the domino (it will be in the _panelMainInfoRight panel)
    private final JButton[][] _dominoGraphRotation; // a graph of 2*2 to put a domino inside and when the player will rotate it or inverser, it will be showed in this graph (it will be in the _panelMainInfoRight panel)
    private JButton[][] _btnOnGraph; // We will have 5*5 lines and columns that we will instantiate for each player

    private CardLayout[] cardLayout; // cardlayout to show first the hidden dominoes and after the two tiles of each domino when we return them
    private Container[] _container; // We add in the container (our stack) at the first place the hidden dominoes, and at the 2nd place the unhidden dominoes

    private JTextField[] _textNamePlayer; // A textfield to change the name of the players as we want
    private final GridBagConstraints constraints;
    private int _indexDominoClicked; // the index that we want to store to access to rotate our domino to call it in the controller --> model
    private final String _unicodeCrown; // the unicode crown to show all the crowns of a Tile
    private boolean[] _castleIsSet; // an array for each player that need to put their castle on the graph
    private Map<Integer, JButton[][]> _mapGraphPlayer; // The key will be the number of the player, and the value will be the player's graph (array of button [5][5]
    private List<Integer> _orderPlayerPrevious; // for each game we will have a list of the players that have to start the first and the lastest to put his domino
    private int[] _orderPlayerActual; // an array which will contain at the index of the array, the number of the player who selected the domino
    private boolean _firstGame = true; // condition to check if it's the beginning of the game (because rules are different for the first round, like we shuffle under the table which player will start first)
    private boolean _dominoesAreChoosen;
    private boolean[] _allDominoesAreSet;
    private int _roundNumber = 0;

    public KingDominoGame() throws IOException {
        _window = Window.instance; // we get our main window to access to its variables
        _window._controller.addObserver(this);
        _window.frame.setTitle("Game Kingdomino");
        _window.frame.getContentPane().removeAll();
        _window.frame.repaint();
        _window.frame.setSize(1100,600);
        _window._controller.instanciateDeck(_window.numberPlayer); // We instantiate our deck just one time
        _window.frame.setLocationRelativeTo(null);
        _unicodeCrown = "\uD83D\uDC51";
        _indexDominoClicked = -1; // we initialize this to -1 to avoid some problems if we click too early on the rotation button

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("MainScreen.png")).readAllBytes());

        // Constraints
        constraints = new GridBagConstraints();
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

        // PANEL MAIN INFO --> _panelMain = it will be at the left of the main panel to show the 4 dominoes and rotation of one domino + title and title of information
        _panelMainInfo.setLayout( new BorderLayout());
        _panelMainInfo.setPreferredSize(new Dimension(430, 600));

        // PANEL MAIN INFO TOP -->  _panelMainInfo = we will show the main title + a label about the game to know what to do
        _panelMainInfoTop = new JPanel(new BorderLayout());
        _panelMainInfoTop.setPreferredSize(new Dimension(430, 110));

        // LABEL TITLE WELCOME --> _panelMainInfoTop
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(20,0,0,0);
        _labelRound = new JLabel("ROUND " + _roundNumber);
        _labelRound.setFont(_window._fontGermania.deriveFont(Font.PLAIN, 48));
        _labelRound.setHorizontalAlignment(JLabel.CENTER);
        _panelMainInfoTop.add(_labelRound, BorderLayout.CENTER);

        // LABEL SUB TITLE ABOUT WHAT TO DO --> _panelMainInfoTop
        constraints.gridy = 1;
        constraints.insets = new Insets(10,0,0,0);
        _textInformationToDo = new JLabel("Players... put your castle !");
        _textInformationToDo.setHorizontalAlignment(JLabel.CENTER);
        _textInformationToDo.setFont(_window._fontGermania.deriveFont(Font.PLAIN, 20));
        _panelMainInfoTop.add(_textInformationToDo, BorderLayout.SOUTH);

        // PANEL MAIN INFO LEFT --> _panelMainInfoLeft = We create the four dominoes
        createDominoes();
        _window._controller.putDominoOnTable(); // When we start we put the 4 dominoes
        constraints.insets = new Insets(10,0,0,0);
        _panelMainInfoLeft = new JPanel() {
            public boolean isOptimizedDrawingEnabled() {
                return false;
            }
        };
        LayoutManager overlay = new OverlayLayout(_panelMainInfoLeft);
        _panelMainInfoLeft.setLayout(overlay);
        _panelMainInfoLeft.setPreferredSize(new Dimension(215, 490));

        // _panelLabelKing --> PANEL TO PUT LABEL IN IT
        _panelLabelKing = new JPanel(new GridBagLayout());
        _panelLabelKing.setOpaque(false);
        labelKingONDominoes();

        // _panelLeftDominoes : panel where we put only the 4 dominoes
        _panelLeftDominoes = new JPanel(new GridBagLayout());
        constraints.gridy = 1;
        constraints.insets = new Insets(10,0,0,0);
        _panelLeftDominoes.add(_panelGridDominoes, constraints); // _panelGridDominoes was created in the method createDominoes()
        _panelLeftDominoes.setOpaque(false);

        // _panelMainInfoLeft : we add the 4 labels and the 4 dominoes
        _panelMainInfoLeft.add(_panelLabelKing);
        _panelMainInfoLeft.add(_panelLeftDominoes);

        // PANEL MAIN INFO LEFT --> _panelMainInfoLeft = We create the button to show all the dominoes
        constraints.gridy = 2;
        constraints.insets = new Insets(10,0,0,0);
        _btnShowDomino = new JButton("Show dominoes");
        _btnShowDomino.setFocusable(false);
        _btnShowDomino.setFont(_window._fontTimeless.deriveFont(Font.PLAIN, 20));
        _btnShowDomino.setForeground(Color.WHITE);
        //_btnShowDomino.setBackground(Color.decode("#DDAB40"));
        _btnShowDomino.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        _btnShowDomino.setOpaque(false);
        _btnShowDomino.setContentAreaFilled(false);
        _btnShowDomino.setBorderPainted(false);
        _panelLeftDominoes.add(_btnShowDomino, constraints);
        _panelMainInfoLeft.add(_panelLeftDominoes, constraints);

        // PANEL MAIN INFO RIGHT --> _panelMainInfoRight = we create the graph of 2*2 to rotate a domino
        _panelMainInfoRight = new JPanel( new GridBagLayout());
        _panelMainInfoRight.setPreferredSize(new Dimension(215, 490));
        _rotateDomino = new JButton[2];
        _subPanelRotation = new JPanel(new GridLayout(2,2));
        _subPanelRotation.setOpaque(false);
        _dominoGraphRotation = new JButton[2][2]; // 2 lines and 2 columns
        for(int i=0; i<2; i++)
        {
            for(int j=0; j<2; j++)
            {
                _dominoGraphRotation[i][j] = new JButton();
                _dominoGraphRotation[i][j].setPreferredSize(new Dimension(85, 85));
                _dominoGraphRotation[i][j].setFocusPainted(false);
                _dominoGraphRotation[i][j].setContentAreaFilled(false);
                _dominoGraphRotation[i][j].setBorderPainted(false);
                _dominoGraphRotation[i][j].setFont(new Font(_dominoGraphRotation[i][j].getFont().getName(), Font.PLAIN, 20));
                _dominoGraphRotation[i][j].setForeground(Color.WHITE);
                _subPanelRotation.add(_dominoGraphRotation[i][j]);
            }
        }

        // PANEL MAIN INFO RIGHT --> _panelMainInfoRight = we create a bouton to rotate the domino
        _rotateDomino[0] = new JButton();
        _rotateDomino[0].setPreferredSize(new Dimension(60, 60));
        _rotateDomino[0].setIcon(new ImageIcon(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("rotate.png")).readAllBytes()));
        _rotateDomino[0].setBorderPainted(false);
        _rotateDomino[0].setContentAreaFilled(false);
        _rotateDomino[0].setFocusPainted(false);
        _rotateDomino[0].setOpaque(false);
        _rotateDomino[0].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // PANEL MAIN INFO RIGHT --> _panelMainInfoRight = we create a bouton to inverse the domino
        _rotateDomino[1] = new JButton();
        _rotateDomino[1].setPreferredSize(new Dimension(60, 60));
        _rotateDomino[1].setIcon(new ImageIcon(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("inverse.png")).readAllBytes()));
        _rotateDomino[1].setBorderPainted(false);
        _rotateDomino[1].setContentAreaFilled(false);
        _rotateDomino[1].setFocusPainted(false);
        _rotateDomino[1].setOpaque(false);
        _rotateDomino[1].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        // PANEL MAIN INFO RIGHT --> _panelMainInfoRight : we display correctly all the elements in this panel
        constraints.gridy = 0;
        constraints.gridx = 0;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(0,0,0,0);
        _panelMainInfoRight.add(_subPanelRotation, constraints);
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        constraints.gridx = 0;
        constraints.insets = new Insets(20,30,0,0);
        _panelMainInfoRight.add(_rotateDomino[0], constraints);
        constraints.insets = new Insets(20,10,0,30);
        constraints.gridy = 1;
        constraints.gridx = 2;
        _panelMainInfoRight.add(_rotateDomino[1], constraints);

        // We add the 3 subPanels (_panelMainInfo TOP, LEFT, RIGHT) to the _panelMainInfo
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        _panelMainInfoTop.setOpaque(false);
        _panelMainInfo.add(_panelMainInfoTop, BorderLayout.NORTH);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        _panelMainInfoLeft.setOpaque(false);
        _panelMainInfo.add(_panelMainInfoLeft, BorderLayout.WEST);
        constraints.gridx = 1;
        _panelMainInfoRight.setOpaque(false);
        _panelMainInfo.add(_panelMainInfoRight, BorderLayout.EAST);

        // GRAPH PANEL : panels which will contains 2,3 or 4 panels depending on how many players will play
        _panelMainGraph = new JPanel(new GridLayout(2,2)) // display in this panel a background image to get a beautiful game
        {
            final Image img = new ImageIcon(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("backGraph.png")).readAllBytes()).getImage();
            // instance initializer
            {setOpaque(false);}
            public void paintComponent(Graphics graphics)
            {
                graphics.drawImage(img, 0, 0, this);
                super.paintComponent(graphics);
            }
        };

        _panelMainGraph.setPreferredSize(new Dimension(670, 600));
        _panelMainGraph.setBackground(Color.WHITE);
        createGraph(); // We're going to create the graph
        allCastleSet(); // we disable the button if we didn't put the castle

        // MAIN PANEL : We put element in the main Panel
        _panelMain.add(_panelMainInfo, BorderLayout.WEST);
        _panelMain.add(_panelMainGraph, BorderLayout.EAST);

        // We show the _panelMain on our window
        _window.frame.setContentPane( _panelMain);
        _window.frame.setVisible( true );

        // SHOW DOMONOES BUTTON LISTENER : we unhidde the four buttons
        _btnShowDomino.addActionListener(actionEvent -> {
            for(int i=0; i<cardLayout.length; i++)
            {
                cardLayout[i].next(_container[i]); // when we click oon the button the four domino will show the two tiles of each domino
            }
            if(_firstGame)
            {
                // TODO : firstLaunchGame();
                firstLaunchGame();
            } else {
                otherLaunchGame();
            }
        });

        // ROTATE A DOMINO
        _rotateDomino[0].addActionListener(actionEvent -> {
            if(_indexDominoClicked!=-1)
                _window._controller.callRotationDomino(_indexDominoClicked);
        });
        _rotateDomino[1].addActionListener(actionEvent -> {
            if(_indexDominoClicked!=-1)
                _window._controller.callReverseDomino(_indexDominoClicked);
        });
    }

    @Override
    public void update(Game game) {
        int numberDominoes = 4;
        if(_window.numberPlayer == 3)
            numberDominoes = 3;

        for(int i = 0; i < numberDominoes; i++){
            _btnHideDominoes[i].setText(game.getActualDominoes().get(i).getId().toString());
            System.out.println("Domino ??" + game.getActualDominoes().get(i).getId().toString());
            for(int j=0; j<2; j++)
            {
                _btnTiles[i][j].setBackground(Color.decode(game.getActualDominoes().get(i).getTile()[0][j].getColor()));
                _btnTiles[i][j].setText(""); // we put the counter at 0 to remove all the previous crowns
                // We get all the crowns of the title
                for(int k=0; k<_window._game.getActualDominoes().get(i).getTile()[0][j].getCrowns(); k++)
                {
                    _btnTiles[i][j].setText(_btnTiles[i][j].getText() + _unicodeCrown); // it's a unicode string to access to a crown
                }
            }
        }
    }

    @Override
    public void rotationDomino(Game game) {
        putDominoRotate();
    }

    public void setCrownRotation(int x, int y)
    {
        int numberCrown = _window._game.getActualDominoes().get(_indexDominoClicked).getTile()[x][y].getCrowns();
        if(numberCrown==0)
        {
            _dominoGraphRotation[x][y].setText("");
        } else {
            for(int k=0; k<numberCrown; k++)
            {
                _dominoGraphRotation[x][y].setText( _dominoGraphRotation[x][y].getText() + _unicodeCrown);
            }
        }
    }

    public void labelKingONDominoes() throws IOException {
        int _numberDomino = 4; // we will have 4 dominoes
        if(_window.numberPlayer == 3)
            _numberDomino = 3;
        _lblKingPicture = new JLabel[_numberDomino];
        int _gridY = 0;
        constraints.gridx = 0;

        for(int i=0; i<_numberDomino; i++)
        {
            _lblKingPicture[i] = new JLabel("     ");
            //_lblKingPicture[i].setIcon(new ImageIcon(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("kingtry.png")).readAllBytes()));
            constraints.gridy = _gridY;
            _lblKingPicture[i].setOpaque(false);
            _lblKingPicture[i].setBackground(Color.BLUE);
            _lblKingPicture[i].setMinimumSize(new Dimension(25,25));
            _lblKingPicture[i].setMaximumSize(new Dimension(25,25));

            if(i==0)
            {
                constraints.insets = new Insets(-123,0,60,0);
            } else if(i==1) {
                constraints.insets = new Insets(-5,0,0,0);
            } else if (i==2)
            {
                constraints.insets = new Insets(80,0,0,0);
            }
            else if(i==3)
            {
                constraints.insets = new Insets(77,0,0,0);
            }

            _panelLabelKing.add(_lblKingPicture[i], constraints);

            _gridY++;
        }
    }

    public void changeLabelKing()
    {
        // TODO : CECI EST TEMPORAIRE JUSTE UN TEST, NOUS ALLONS METTRE DES IMAGES PAR LA SUITE (ca ne respecte pas MVC)
        switch (_window._game.getPlayer(_orderPlayerPrevious.get(0)).getKing()) {
            case PINK -> _lblKingPicture[_indexDominoClicked].setBackground(Color.PINK);
            case YELLOW -> _lblKingPicture[_indexDominoClicked].setBackground(Color.YELLOW);
            case GREEN -> _lblKingPicture[_indexDominoClicked].setBackground(Color.RED);
            case BLUE -> _lblKingPicture[_indexDominoClicked].setBackground(Color.BLUE);
            default -> {
            }
        }

        _lblKingPicture[_indexDominoClicked].setOpaque(true);
    }

    public void createDominoes() throws IOException {
        int _numberDomino = 4; // we will have 4 dominoes
        if(_window.numberPlayer == 3)
            _numberDomino = 3;
        cardLayout = new CardLayout[_numberDomino]; // our card layout
        _container = new Container[_numberDomino]; // our container

        // We create an instance of grid layout just to put a margin between each domino (vgap =  the vertical gap)
        GridLayout gridLayout = new GridLayout(4, 1, 0, 10);
        _panelGridDominoes = new JPanel(gridLayout); // we add the layout in our main panel which will contain our CardLayout panel
        _panelGridDominoes.setOpaque(false);

        _panelHideDominoes = new JPanel[_numberDomino]; // array of panels which will contain one button for each domino (hidden domino)
        _btnHideDominoes = new JButton[_numberDomino]; // array of button which wil contain one button for each hidden domino

        _btnTiles = new JButton[_numberDomino][2]; // array of button for each domino which will contains 2 tiles (one domino = 2 tiles)
        _panelUnhideDominoes = new JPanel[_numberDomino]; // array of panel to show the two tiles for each domino (unhidden domino)

        // LOOP which will create an array of panels, to put one single button inside (to show a hidden domino)
        for(int i=0; i<_numberDomino; i++)
        {
            // We create a panel and a button that we put in an array
            _panelHideDominoes[i] = new JPanel( new GridLayout(1,2));
            _btnHideDominoes[i] = new JButton(Integer.toString(i));

            _btnHideDominoes[i].setPreferredSize(new Dimension(170, 85));
            _btnHideDominoes[i].setIcon(new ImageIcon(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("domino1.png")).readAllBytes()));
            _btnHideDominoes[i].setForeground(Color.WHITE);
            _btnHideDominoes[i].setFont(_window._fontGermania.deriveFont(Font.PLAIN, 25));
            _btnHideDominoes[i].setHorizontalTextPosition(SwingConstants.CENTER);
            _btnHideDominoes[i].setFocusable(false);

            // We add a listenner which will show the unhide domino with the 2 tiles
            int finalI = i;
            _btnHideDominoes[i].addActionListener(clickevent -> {
                //cardLayout[finalI].next(_container[finalI]); // when we click of one of the four domino, it will call the next card layout
                System.out.println("Clicked on domino : " + finalI);
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
            _btnTiles[i][0] = new JButton();
            _btnTiles[i][0].setFont(new Font(_btnTiles[i][0].getFont().getName(), Font.PLAIN, 20));
            _btnTiles[i][0].setForeground(Color.WHITE);
            _btnTiles[i][1] = new JButton();
            _btnTiles[i][1].setFont(new Font(_btnTiles[i][1].getFont().getName(), Font.PLAIN, 20));
            _btnTiles[i][1].setForeground(Color.WHITE);

            _btnTiles[i][0].setFocusable(false);
            _btnTiles[i][1].setFocusable(false);

            _panelUnhideDominoes[i].add( _btnTiles[i][0]);
            _panelUnhideDominoes[i].add( _btnTiles[i][1]);

            // When we click of the left tile :
            int finalI = i;
            _btnTiles[i][0].addActionListener(clickevent -> {
                _indexDominoClicked = finalI;
                setBackgroudDominoGraph(0,0,true);
                putDominoRotate();
                System.out.println("Tile [" + finalI + "][0]");
                if(!_dominoesAreChoosen)
                {
                    addPlayerPlaceDomino();
                }
            });
            // When we click of the right tile :
            _btnTiles[i][1].addActionListener(clickevent -> {
                _indexDominoClicked = finalI;
                setBackgroudDominoGraph(0,0,true);
                putDominoRotate();
                System.out.println("Tile [" + finalI + "][1]");
                if(!_dominoesAreChoosen)
                {
                    addPlayerPlaceDomino();
                }
            });
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

        _panelAllGraphText = new JPanel[numberPlayers]; // a panel to show a label about the name of the player AND the graph of 25 buttons (_panelGraph)
        _panelGraph = new JPanel[numberPlayers]; // a panel to put 25 buttons in it (will be in the _panelAllGraphText panel)
        _textNamePlayer = new JTextField[numberPlayers]; // We can change the name of the players
        _castleIsSet = new boolean[_window.numberPlayer]; // ARRAY OF BOOLEAN to check who put his castle
        _mapGraphPlayer = new HashMap<>(); // The key will be the number of the player, and the value will be the player's graph (array of button [5][5]

        // Constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        int width = 335;
        int height = 300;

        for(int i=0; i<numberPlayers; i++)
        {
            _btnOnGraph = new JButton[5][5]; // We will have 5*5 lines and columns that we will instantiate for each player
            _mapGraphPlayer.put(i, _btnOnGraph); // we put in the key the number of the player and his graph of 5*5
            // NAME OF EACH PLAYER
            _panelAllGraphText[i] = new JPanel( new GridBagLayout());
            _panelAllGraphText[i].setBackground(Color.WHITE);
            _panelAllGraphText[i].setOpaque(false);

            _textNamePlayer[i] = new JTextField("Kingdom " + (i+1));
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
                        _textNamePlayer[finalI].setText("Kingdom" + (finalI+1));
                    }
                    _textNamePlayer[finalI].setEditable(false);
                    _textNamePlayer[finalI].getCaret().setVisible(false);
                }
            });

            // THE 25 BUTTONS (our graph) FOR EACH PLAYER
            _panelGraph[i] = new JPanel(new GridLayout(5, 5));
            _panelAllGraphText[i].setPreferredSize(new Dimension(207, 207));

            for(int k=0; k<5; k++)
            {
                for(int l=0; l<5; l++)
                {
                    _btnOnGraph[k][l] = new JButton(Integer.toString(k+l));
                    _btnOnGraph[k][l].setFont(new Font("Serif", Font.PLAIN, 8));
                    _btnOnGraph[k][l].setPreferredSize(new Dimension(42, 42));
                    _btnOnGraph[k][l].setBackground(Color.decode("#CECECE"));
                    _btnOnGraph[k][l].setRolloverEnabled(false);
                    _panelGraph[i].add(_btnOnGraph[k][l]);

                    int finalK = k;
                    int finalL = l;
                    _mapGraphPlayer.get(i)[k][l].addActionListener(actionEvent -> {
                        System.out.println(finalI + " " + finalK + " " + finalL);
                        if(!_castleIsSet[finalI]) // we check if we never put a castle on the graph of the player
                        {
                            try {
                                _mapGraphPlayer.get(finalI)[finalK][finalL].setIcon(new ImageIcon(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResourceAsStream("castle1.png")).readAllBytes()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            _castleIsSet[finalI] = true;
                            _window._controller.setCastle(finalI, finalK, finalL);
                        }
                        allCastleSet();
                        if(_dominoesAreChoosen) // if castles are all set
                        {
                            //TODO : put the domino on the graph
                            _allDominoesAreSet[_indexDominoClicked] = true;
                            letPlayerSetDomino();
                        }
                    });
                }
            }

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

    // We remove or display a font to put or remove a color in our mini graph at one specifi coordinate
    public void setBackgroudDominoGraph(int x, int y, boolean condition)
    {
        _dominoGraphRotation[x][y].setContentAreaFilled(condition);
        _dominoGraphRotation[x][y].setBorderPainted(condition);
        if(!condition)
          _dominoGraphRotation[x][y].setText(""); // we remove the crown unicode (even if there wasn't any)
    }

    public void putDominoRotate()
    {
        _dominoGraphRotation[0][0].setBackground(Color.decode(_window._game.getColorTile(_indexDominoClicked, 0,0)));
        _dominoGraphRotation[0][0].setText(""); // We remove all the crowns at the [0][0] coord to avoid any problems
        _dominoGraphRotation[0][1].setText(""); // We remove all the crowns at the [0][0] coord to avoid any problems
        _dominoGraphRotation[1][0].setText(""); // We remove all the crowns at the [0][0] coord to avoid any problems
        setCrownRotation(0,0);

        if(_window._game.isXXDomino(_indexDominoClicked))
        {
            setBackgroudDominoGraph(0,1,true);
            setBackgroudDominoGraph(1,0,false);
            _dominoGraphRotation[0][1].setBackground(Color.decode(_window._game.getColorTile(_indexDominoClicked, 0,1)));
            setCrownRotation(0,1);
        } else if(_window._game.isXYDomino(_indexDominoClicked)){
            setBackgroudDominoGraph(1,0,true);
            setBackgroudDominoGraph(0,1,false);
            _dominoGraphRotation[1][0].setBackground(Color.decode(_window._game.getColorTile(_indexDominoClicked, 1,0)));
            setCrownRotation(1,0);
        }
    }

    public void setTextInformation(JTextField player, String text)
    {
        _textInformationToDo.setText(player.getText() + " " + text);
    }

    public void setTextInformation(String text)
    {
        _textInformationToDo.setText(text);
    }

    public void allCastleSet()
    {
        boolean _castleArentSet = false;
        for (boolean b : _castleIsSet) {
            if (!b) {
                _castleArentSet = true;
                break;
            }
        }

        if(!_castleArentSet)
        {
            _btnShowDomino.setEnabled(true);
            setTextInformation("Players, unhide the dominoes !");
        }

        for(int i=0; i<_castleIsSet.length; i++)
        {
            if (!_castleIsSet[i]) {
                _btnShowDomino.setEnabled(false);
                setTextInformation(_textNamePlayer[i], "put your castle !");
                break;
            }
        }
    }

    // For the first round, we just shuffle under the table who will start to choose the domino first
    public void firstLaunchGame()
    {
        int _numberDomino = 4;
        if(_window.numberPlayer == 3)
            _numberDomino = 3;

        _orderPlayerPrevious = new ArrayList<>();
        _orderPlayerActual = new int[_numberDomino];
        for(int i=0; i<_numberDomino; i++)
        {
            if(_window.numberPlayer == 2 && i < 2) // we add in the list the player one and two --> twice = size of 4 then in the list
            {
                _orderPlayerPrevious.add(i);
                _orderPlayerPrevious.add(i);
            } else if(_window.numberPlayer >= 3){
                _orderPlayerPrevious.add(i);
            }
        }
        Collections.shuffle(_orderPlayerPrevious); // we shuffle the list
        _firstGame = false;
        _btnShowDomino.setEnabled(false);
        Arrays.fill(_orderPlayerActual, -1); // we set the value to -1
        _allDominoesAreSet = new boolean[_numberDomino];
        nextPlayerToChooseDomino(); // we put at the screen game, the first player who will have to choose his domino for the game
    }

    // When we start a new round, we look at the last round the ascendant order (if player 2 choose the first domino, at this new round he will start first, etc.)
    public void otherLaunchGame()
    {
        int _numberDomino = 4;
        if(_window.numberPlayer == 3)
            _numberDomino = 3;

        _dominoesAreChoosen = false;
        _orderPlayerPrevious.clear();
        for(int i=0; i<_numberDomino; i++)
        {
            _orderPlayerPrevious.add(_orderPlayerActual[i]);
        }

        _orderPlayerActual = new int[_numberDomino];
        _allDominoesAreSet = new boolean[_numberDomino];
        Arrays.fill(_orderPlayerActual, -1); // we set the value to -1
        nextPlayerToChooseDomino();
        _btnShowDomino.setEnabled(false);
    }

    // We add a player in our array of the actual round
    public void addPlayerPlaceDomino()
    {
        if(_orderPlayerActual[_indexDominoClicked] != -1) // if the value not equal to -1, it means that someboby already choose the domino
        {
            setTextInformation(_textNamePlayer[_orderPlayerPrevious.get(0)], "choose another domino !");
        } else {
            _orderPlayerActual[_indexDominoClicked] = _orderPlayerPrevious.get(0);
            changeLabelKing();
            _orderPlayerPrevious.remove(0);
            nextPlayerToChooseDomino();
        }
    }

    // We tell to the next player that he has to start selecting his domino
    public void nextPlayerToChooseDomino()
    {
        if(_orderPlayerPrevious.isEmpty())
        {
            _dominoesAreChoosen = true;
            letPlayerSetDomino();
        } else {
            setTextInformation(_textNamePlayer[_orderPlayerPrevious.get(0)], "choose your domino !");
        }
    }

    public void letPlayerSetDomino()
    {
        int _numberDomino = 4;
        if(_window.numberPlayer == 3)
            _numberDomino = 3;

        for(int i=0; i<_numberDomino; i++)
        {
            _btnTiles[i][0].setEnabled(false);
            _btnTiles[i][1].setEnabled(false);
            //_lblKingPicture[i].setOpaque(false);
            graphEnabled(false, _orderPlayerActual[i]);
        }
        for(int i=0; i<_numberDomino; i++)
        {
            if(!_allDominoesAreSet[i])
            {
                _btnTiles[i][0].setEnabled(true);
                _btnTiles[i][1].setEnabled(true);
                //_lblKingPicture[i].setOpaque(true);
                graphEnabled(true, _orderPlayerActual[i]);
                setTextInformation(_textNamePlayer[_orderPlayerActual[i]], "place your domino !");
                _indexDominoClicked = i;
                putDominoRotate();
                break;
            }
        }

        boolean _dominoesAreSet = true;
        for(int i=0; i<_numberDomino; i++)
        {
             if(!_allDominoesAreSet[i])
             {
                 _dominoesAreSet = false;
                 break;
             }
        }

        if(_dominoesAreSet)
        {
            _window._controller.putDominoOnTable(); // we put again new dominoes;
            for(int i=0; i<_numberDomino; i++)
            {
                _lblKingPicture[i].setOpaque(false);
                _btnTiles[i][0].setEnabled(true);
                _btnTiles[i][1].setEnabled(true);
            }

            for(int i=0; i<2; i++)
            {
                for(int j=0; j<2; j++)
                {
                    setBackgroudDominoGraph(i,j,false);
                }
            }

            _btnShowDomino.setEnabled(true);
            _roundNumber += 1;
            _labelRound.setText("ROUND " + _roundNumber);

            for(int i=0; i<cardLayout.length; i++)
            {
                cardLayout[i].first(_container[i]); // when we click oon the button the four domino will show the two tiles of each domino
            }
        }
    }

    // Disable or enabled the 25 buttons on the player's graph depending on if it's the player'a turn to put his domino
    public void graphEnabled(boolean condition, int index)
    {
        for(int i=0; i<5; i++)
        {
            for(int j=0; j<5; j++)
            {
                _mapGraphPlayer.get(index)[i][j].setEnabled(condition);
            }
        }
    }
}
