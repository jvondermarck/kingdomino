package view;

import model.Game;
import model.Observer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings("FieldCanBeLocal")
public class KingDominoGame implements Observer {

    private final Window _window; // our window that we get thanks to his static method and singleton variable

    private final JPanel _panelMain; // Our main panel which will contains two panels inside : _panelMainInfo &
    private final JPanel _panelMainInfo; // it will contain 3 panels (_panelMainInfoTop, LEFT, RIGHT) to show the titles, the 4 dominoes, a button to show it, and a system to rotate a domino. It will be showed at the left of the window
    private final JPanel _panelMainInfoTop; // Contains labels
    private final JPanel _panelMainInfoLeft; // Contains 4 dominoes and a button to show them (it will contain the _panelGridDominoes in it)
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

    private JButton[] _btnHideDominoes; // array of button which wil contain one button for each hidden domino (it will be in the _panelMainInfoLeft panel)
    private JButton[][] _btnTiles; // array of button for each domino which will contains 2 tiles (one domino = 2 tiles) (it will be in the _panelMainInfoLeft panel)
    private final JButton _btnShowDomino; // a button to return the 4 dominoes (it will be in the _panelMainInfoLeft panel)
    private final JButton[] _rotateDomino; // an array of button, the index 0 = button to reverse and index = 1 to inverse the domino (it will be in the _panelMainInfoRight panel)
    private final JButton[][] _dominoGraphRotation; // a graph of 2*2 to put a domino inside and when the player will rotate it or inverser, it will be showed in this graph (it will be in the _panelMainInfoRight panel)
    private JButton[][] _btnOnGraph; // We will have "numberPlayers" lines AND 25 columns because it's a 5*5 graph

    private CardLayout[] cardLayout; // cardlayout to show first the hidden dominoes and after the two tiles of each domino when we return them
    private Container[] _container; // We add in the container (our stack) at the first place the hidden dominoes, and at the 2nd place the unhidden dominoes

    private JTextField[] _textNamePlayer; // A textfield to change the name of the players as we want
    private int indexDominoClicked; // the index that we want to store to access to rotate our domino to call it in the controller --> model
    private final String _unicodeCrown; // the unicode crown to show all the crowns of a Tile
    private boolean[] _castleIsSet; // an array for each player that need to put their castle on the graph

    public KingDominoGame()
    {
        _window = Window.instance; // we get our main window to access to its variables
        _window._controller.addObserver(this);
        _window.frame.setTitle("Game Kingdomino");
        _window.frame.getContentPane().removeAll();
        _window.frame.repaint();
        _window.frame.setSize(1100,600);
        _window._controller.instanciateDeck(_window.numberPlayer); // We instantiate our deck just one time
        _window.frame.setLocationRelativeTo(null);
        _unicodeCrown = "\uD83D\uDC51";
        indexDominoClicked = -1; // we initialize this to -1 to avoid some problems if we click too early on the rotation button

        ImageIcon icon = new ImageIcon("img/MainScreen.png");

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
        _labelRound = new JLabel("ROUND");
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
        constraints.gridy = 1;
        constraints.insets = new Insets(10,0,0,0);
        _panelMainInfoLeft = new JPanel( new GridBagLayout());
        _panelMainInfoLeft.setPreferredSize(new Dimension(215, 490));
        _panelMainInfoLeft.add(_panelGridDominoes, constraints); // _panelGridDominoes was created in the method createDominoes()

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
        _panelMainInfoLeft.add(_btnShowDomino, constraints);

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
        _rotateDomino[0].setIcon(new ImageIcon("img/rotate.png"));
        _rotateDomino[0].setBorderPainted(false);
        _rotateDomino[0].setContentAreaFilled(false);
        _rotateDomino[0].setFocusPainted(false);
        _rotateDomino[0].setOpaque(false);
        _rotateDomino[0].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // PANEL MAIN INFO RIGHT --> _panelMainInfoRight = we create a bouton to inverse the domino
        _rotateDomino[1] = new JButton();
        _rotateDomino[1].setPreferredSize(new Dimension(60, 60));
        _rotateDomino[1].setIcon(new ImageIcon("img/inverse.png"));
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
            final Image img = new ImageIcon("img/backGraph.png").getImage();
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
        });

        // ROTATE A DOMINO
        _rotateDomino[0].addActionListener(actionEvent -> {
            if(indexDominoClicked!=-1)
                _window._controller.callRotationDomino(indexDominoClicked);
        });
        _rotateDomino[1].addActionListener(actionEvent -> {
            if(indexDominoClicked!=-1)
                _window._controller.callReverseDomino(indexDominoClicked);
        });
    }

    @Override
    public void update(Game game) {
        for(int i = 0; i < 4; i++){
            _btnHideDominoes[i].setText(game.getActualDominoes().get(i).getId().toString());
            System.out.println("Domino °" + game.getActualDominoes().get(i).getId().toString());
            for(int j=0; j<2; j++)
            {
                _btnTiles[i][j].setBackground(Color.decode(game.getActualDominoes().get(i).getTile()[0][j].getColor()));
                // We get all the crowns of the title
                for(int k=0; k<_window._game.getActualDominoes().get(i).getTile()[0][j].getCrowns(); k++)
                {
                    _btnTiles[i][j].setText( _btnTiles[i][j].getText() + _unicodeCrown); // it's a unicode string to access to a crown
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
        int numberCrown = _window._game.getActualDominoes().get(indexDominoClicked).getTile()[x][y].getCrowns();
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
            _btnHideDominoes[i].setIcon(new ImageIcon("img/domino1.png"));
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
                indexDominoClicked = finalI;
                setBackgroudDominoGraph(0,0,true);
                putDominoRotate();
                System.out.println("Tile [" + finalI + "][0]");
            });
            // When we click of the right tile :
            _btnTiles[i][1].addActionListener(clickevent -> {
                indexDominoClicked = finalI;
                setBackgroudDominoGraph(0,0,true);
                putDominoRotate();
                System.out.println("Tile [" + finalI + "][1]");
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
        _btnOnGraph = new JButton[numberPlayers][25]; // We will have "numberPlayers" lines AND 25 columns because it's a 5*5 graph
        _textNamePlayer = new JTextField[numberPlayers]; // We can change the name of the players
        _castleIsSet = new boolean[_window.numberPlayer]; // ARRAY OF BOOLEAN to check who put his castle

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
            _panelAllGraphText[i].setOpaque(false);

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
                    if(!_castleIsSet[finalI]) // we check if we never put a castle on the graph of the player
                    {
                        _btnOnGraph[finalI][finalJ].setIcon(new ImageIcon("img/castle1.png"));
                        _castleIsSet[finalI] = true;
                        // TODO : update coordinate of the castle to the model
                    }
                    allCastleSet();
                });
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
        _dominoGraphRotation[0][0].setBackground(Color.decode(_window._game.getColorTile(indexDominoClicked, 0,0)));
        _dominoGraphRotation[0][0].setText(""); // We remove all the crowns at the [0][0] coord to avoid any problems
        setCrownRotation(0,0);

        if(_window._game.isXXDomino(indexDominoClicked))
        {
            setBackgroudDominoGraph(0,1,true);
            setBackgroudDominoGraph(1,0,false);
            _dominoGraphRotation[0][1].setBackground(Color.decode(_window._game.getColorTile(indexDominoClicked, 0,1)));
            setCrownRotation(0,1);
        } else if(_window._game.isXYDomino(indexDominoClicked)){
            setBackgroudDominoGraph(1,0,true);
            setBackgroudDominoGraph(0,1,false);
            _dominoGraphRotation[1][0].setBackground(Color.decode(_window._game.getColorTile(indexDominoClicked, 1,0)));
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
        _btnShowDomino.setEnabled(true);
        setTextInformation("Players, unhide the dominoes !");
        for(int i=0; i<_castleIsSet.length; i++)
        {
            if (!_castleIsSet[i]) {
                _btnShowDomino.setEnabled(false);
                setTextInformation(_textNamePlayer[i], " put your castle !");
                break;
            }
        }
    }


}
