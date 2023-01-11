/**
 * Description: This class is responsible for the Game GUI
 */

package tictactoe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.nio.file.Files;


public class GameUI extends JFrame implements boardgame.Saveable{
    private TicTacToe game;
    private JPanel gameContainer;
    private JPanel mainButtonsPanel;
    private JTextField textBox;
    private JButton[] buttons;
    //private JButton save;
    private String token;
    private String oddOrEven;
    private int gameType;

    /**
     * Arg constructor 
     * @param mainTitle Title of JFrame
     */
    public GameUI(String mainTitle) {
        super();

        //JFrame default settings
        setTitle(mainTitle);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setMinimumSize(new Dimension(1100, 700));
        getContentPane().setBackground(Color.black);
        setLayout(new BorderLayout());
        createDefaultJFrame();
        pack();
        createMenuBar();
        setVisible(true);
    }

    private void createDefaultJFrame(){
        //title
        add(title(), BorderLayout.NORTH);
        //center component
        makeMainPanel();
        add(gameContainer, BorderLayout.CENTER);

        //west component
        makeButtonPanel();
        add(mainButtonsPanel, BorderLayout.WEST);
    }

    //creates title for GUI
    private JLabel title(){
        JLabel title = new JLabel();
        title.setText("TIC-TAC-TOE");
        title.setFont(new Font("MV Boli", Font.BOLD,50));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(Color.white);

        return title;
    }

    //message shown to user on hompage
    private JPanel startMessage(){
        JPanel temp = new JPanel();
        JLabel tempLabel = new JLabel("WELCOME");
        JLabel tempLabel2 = new JLabel("Pick a game to play from the left column");

        tempLabel.setFont(new Font("MV BOLI", Font.BOLD, 90));
        tempLabel2.setFont(new Font("Dialog", Font.BOLD, 25));

        tempLabel.setHorizontalAlignment(JLabel.CENTER);
        tempLabel2.setHorizontalAlignment(JLabel.CENTER);

        tempLabel.setForeground(Color.BLACK);
        tempLabel2.setForeground(Color.BLACK);

        temp.setLayout(new GridLayout(2,1));
        temp.add(tempLabel);
        temp.add(tempLabel2);
        temp.setBackground(new Color(35, 111, 168));
        return temp;

    }

    //panel in centre of jframe
    private void makeMainPanel(){
        gameContainer = new JPanel();
        gameContainer.setBackground(new Color(35, 111, 168));
        gameContainer.setLayout(new BorderLayout());
        gameContainer.add(startMessage(), BorderLayout.CENTER);
    }

    //button panel on the left of GUI
    private void makeButtonPanel(){
        mainButtonsPanel = new JPanel();
        mainButtonsPanel.setBackground(Color.black);
        mainButtonsPanel.setLayout(new GridLayout(2,1));

        mainButtonsPanel.add(classicTTTButton());
        mainButtonsPanel.add(numTTTButton());

    }

    //next two methods create buttons to start the specific games
    private JButton classicTTTButton(){
        JButton button = new JButton("Play Classic Tic-Tac-Toe");
        button.setForeground(Color.white);
        button.setBackground(Color.black);
        button.setFont(new Font("MV Boli", Font.BOLD,24));
        button.setFocusPainted(false);

        button.addActionListener(e->classicTTT());
        return button;
    }

    private JButton numTTTButton(){
        JButton button = new JButton("Play Numerical Tic-Tac-Toe");
        button.setForeground(Color.white);
        button.setBackground(Color.black);
        button.setFont(new Font("MV Boli", Font.BOLD,24));
        button.setFocusPainted(false);

        button.addActionListener(e->numTTT());
        return button;
    }

    //plays classical tictactoe
    private void classicTTT(){
        game = new TicTacToe(3, 3);
        gameType = 1;
        game.setGrid(TicTacToe.newGrid(gameType,3,3));
        gameContainer.removeAll();

        //makeSaveButton();
        JPanel board = makeBoardPanel();
        buttons = new JButton[9];

        textBox = new JTextField();
        textBox.setFont(new Font("SansSerif", Font.BOLD, 25));

        for(int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFocusable(false);
            buttons[i].setFont(new Font("MV BOLI", Font.BOLD,70));
            buttons[i].setBackground(new Color(35, 111, 168));

            int finalI = i;
            int finalI1 = i;
            buttons[i].addActionListener(e -> buttonClicked(buttons[finalI], finalI1));
            board.add(buttons[i]);
        }
        gameContainer.add(board, BorderLayout.CENTER);
        gameContainer.add(textBox, BorderLayout.NORTH);
        //gameContainer.add(save, BorderLayout.SOUTH);
        textBox.setText(game.getGameStateMessage() + " (" + getToken() + ")");
        pack();
    }

    private JPanel makeBoardPanel(){
        JPanel board = new JPanel();
        board.setLayout(new GridLayout(3,3));
        board.setBackground(new Color(35, 111, 168));

        return board;
    }

    //does neccassary functions for tictactoe gaem grid input
    private void buttonClicked(JButton button, int location){
        String currentToken;
        textBox.setText(game.getGameStateMessage() + " (" + getToken() + ")");
        currentToken = getToken();

        if (!game.takeTurn(getAcrossVal(location), getDownVal(location), getToken())) {
            textBox.setText("Not valid location, try again. "+game.getGameStateMessage()+" ("+getToken() + ")");
        } else {
            if(currentToken.equals("X")){
                button.setForeground(Color.black);
            }else{
                button.setForeground(Color.white);
            }
            button.setText(currentToken);
            textBox.setText(game.getGameStateMessage() + " (" + getToken() + ")");
        }
        if(game.isDone()){
            if(game.getGameStateMessage().equals("Game is a tie!")) {
                textBox.setText(game.getGameStateMessage());
                JOptionPane.showMessageDialog(null,game.getGameStateMessage()
                        + " Use left the column to play a new game.");
            }else{
                textBox.setText("(" + currentToken + ") " + game.getGameStateMessage());
                JOptionPane.showMessageDialog(null,"(" + currentToken + ") " + game.getGameStateMessage()
                        + " Use the left column to play a new game.");
            }
            disableButtons();
        }
    }

    //Starts numerical tictactoe game
    private void numTTT(){
        gameContainer.removeAll();
        gameContainer.repaint();
        game = new TicTacToe(3, 3);
        gameType = 2;
        game.setGrid(TicTacToe.newGrid(gameType,3,3));
        //makeSaveButton();

        JPanel board = makeBoardPanel();
        buttons = new JButton[9];
        textBox = new JTextField();
        textBox.setFont(new Font("SansSerif", Font.BOLD, 25));

        for(int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFocusable(false);
            buttons[i].setFont(new Font("MV BOLI", Font.BOLD,70));
            buttons[i].setBackground(new Color(35, 111, 168));

            int finalI = i;
            int finalI1 = i;
            buttons[i].addActionListener(e -> buttonClicked2(buttons[finalI], finalI1));
            board.add(buttons[i]);
        }
        gameContainer.add(board, BorderLayout.CENTER);
        gameContainer.add(textBox, BorderLayout.NORTH);
        //gameContainer.add(save, BorderLayout.SOUTH);
        textBox.setText(game.getGameStateMessage() + " " + getOddOrEven());
        pack();
    }

    //Does neccessary functions to update grid 
    private void buttonClicked2(JButton button, int location){
        textBox.setText(game.getGameStateMessage() + " " + getOddOrEven());
        var inputNum = JOptionPane.showInputDialog("Enter Value: ");

        if(!checkNum(inputNum)) {

            if (!game.takeTurn(getAcrossVal(location), getDownVal(location), inputNum)) {
                textBox.setText("Not valid location, try again. " + game.getGameStateMessage() + " " + getOddOrEven());
            } else {
                if (game.getCurrentPlayer() == 1) {
                    button.setForeground(Color.black);
                } else {
                    button.setForeground(Color.white);
                }
                button.setText(inputNum);
                textBox.setText(game.getGameStateMessage() + " " + getOddOrEven());
            }

            if(game.isDone()){
                textBox.setText(game.getGameStateMessage());
                JOptionPane.showMessageDialog(null,game.getGameStateMessage()
                        + " Use the left column to play a new game.");
                disableButtons();
            }
        }
    }

    //error traps inputted number for num TTT
    private boolean checkNum(String num){
        int number = 0;
        boolean error;

        try{
            error = false;
            number = Integer.parseInt(num);
        }catch (NumberFormatException e){
            error = true;
        }

        if(!error) {
            if (number > 9 || number < 0) {
                error = true;
            }else if(game.getCurrentPlayer() == 1){
                if(number % 2 == 0){
                    error = true;
                }
            }else{
                if(number % 2 != 0){
                    error = true;
                }
            }
        }
        if (error) {
            textBox.setText("Incorrect input, try again. " + game.getGameStateMessage() + " " + getOddOrEven());
        }
        return error;
    }

    //next two methods get values according to index of button
    private int getAcrossVal(int val){
        switch (val) {
            case 0:
            case 3:
            case 6:
                return 1;

            case 1:
            case 4:
            case 7:
                return  2;

            case 2:
            case 5:
            case 8:
                return 3;

            default:
                return 0;
        }
    }

    private int getDownVal(int val){
        switch (val) {
            case 0:
            case 1:
            case 2:
                return 1;

            case 3:
            case 4:
            case 5:
                return 2;

            case 6:
            case 7:
            case 8:
                return 3;

            default:
                return 0;
        }
    }

    private String getToken(){
        if(game.getCurrentPlayer() == 1){
            token = "X";
        }else{
            token = "O";
        }
        return token;
    }

    private String getOddOrEven() {
        if (game.getCurrentPlayer() == 1) {
            oddOrEven = "(Odd Numbers)";
        } else {
            oddOrEven = "(Even Numbers)";
        }
        return oddOrEven;
    }

    /**
    private void makeSaveButton(){
        save = new JButton("Save Game");
        save.setFocusable(false);
        save.setFont(new Font("Arial", Font.PLAIN, 30));
        save.addActionListener(e->saveToFile());
    }*/

    private void disableButtons(){
        for (int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
    }

    //creates menu bar with required JMenus
    private void createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenu instructions = new JMenu("Instructions");
        JMenuItem classicTTTmenu = new JMenuItem("Classic Tic-Tac-Toe");
        JMenuItem numTTTmenu = new JMenuItem("Numerical Tic-Tac-Toe");
        JMenuItem about = new JMenuItem("About");

        classicTTTmenu.addActionListener(e->displayCTTTInstructions());
        numTTTmenu.addActionListener(e->displayNTTTInstructions());
        about.addActionListener(e->displayAbout());

        instructions.add(classicTTTmenu);
        instructions.add(numTTTmenu);
        menu.add(instructions);
        menu.add(about);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void displayCTTTInstructions(){
        JOptionPane.showMessageDialog(null, "\n\nCLASSIC TIC-TAC-TOE INSTRUCTIONS:\n\n"
                + "1. In this game there are 2 players, Player 1 (X) and Player 2 (O).\n2. It is played on a 3x3 grid."
                + "\n3. Players take turns putting their marks on the board.\n4. First player to to get 3 of their "
                + "symbols in a row, is the winner.\n");

    }

    private void displayAbout(){
        JOptionPane.showMessageDialog(null, "Programmer: Zaeem\nProject: OOPfied"
                + " TicTacToe\nDate: November 26, 2022\n");
    }

    private void displayNTTTInstructions(){
        JOptionPane.showMessageDialog(null, "\n\nNUMERICAL TIC-TAC-TOE INSTRUCTIONS:\n\n"
                + "1. In this game there are 2 players, Player 1 and Player 2.\n2. It is played on a 3x3 grid with"
                +" integers 0 through 9.\n3. Player 1 uses odd numbers and Player 2 uses even numbers."
                + "\n4. Players take turns putting their marks on the board.\n5. The player who makes a line of numbers"
                +"that adds to 15, wins.\n");
    }

    /**
     * toString method
     * @return String to return
     */
    @Override
    public String toString(){
        return "This the is GameUI class.";
    }

    //methods saves current game to a file
    private void saveToFile(){
        boolean error = false;

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify where to save file");
        fileChooser.showSaveDialog(this);

        Path path = FileSystems.getDefault().getPath(fileChooser.getSelectedFile() + ".txt");

        try{
            Files.writeString(path, getStringToSave());
        } catch (IOException e) {
            error = true;
            JOptionPane.showMessageDialog(null,"File saved unsuccessfully");
        }
        if(!error){
            JOptionPane.showMessageDialog(null,"File saved successfully");
        }
    }


    /**
     *  Object returns a string in the format required for a text save file for that object
     * @return String to return
     */
    @Override
    public String getStringToSave(){
        StringBuilder str = new StringBuilder();

        if(gameType == 1){
            str.append(getToken() + "\n");
        }else{
            if(game.getCurrentPlayer() == 1){
                str.append("O\n");
            }else{
                str.append("E\n");
            }
        }
        str.append(game.getStringForSaving());
        return str.toString();
    }

    
    /**
     *  Object parses the string given as a parameter and restores its state based on the values in the string
     * @return void
     */
    @Override
    public void loadSavedString(String toLoad){
        return;
    }
    public static void main(String[] args){
        GameUI game = new GameUI("OOPfied TictacToe");
    }
}
