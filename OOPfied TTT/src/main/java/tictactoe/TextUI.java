/**
 * Name: Zaeem 
 * Date: Nov 26 2022
 * Description: This class has the main method used to run console version of TTT
 */
package tictactoe;
import java.util.Scanner;

public class TextUI{
    private static TicTacToe game;
    private Scanner input = new Scanner(System.in);
    private int acrossVal;
    private int downVal;
    private String token;
    private boolean error;

    /**
     * No arg constructor
     */
    public TextUI(){
        //creates grid for game
        game = new TicTacToe(3, 3);
        game.setGrid(TicTacToe.newGrid(1,3,3));
    }

    /**
     * This method runs the specific game
     */
    public void play() {
        //do while for error trap
        do{
            System.out.println(game.getGameStateMessage() + " (" + getToken() + ")");
            getAcrossVal();
            getDownVal();

            //Updates grid with user input
            if(!game.takeTurn(acrossVal, downVal, getToken())){
                System.out.println("Not valid location, try again.\n");
            }
            System.out.println(game);

        }while(!game.isDone());

        System.out.println(game.getGameStateMessage());
    }

    private void getAcrossVal(){
        do {
            System.out.println("down? (0 to quit)");

            try {
                error = false;
                acrossVal = input.nextInt();
            }catch (Exception e){
                input.nextLine();
                System.err.println("Please enter a valid integer (1-3).\n");
                error = true;
            }

            if(acrossVal == 0){
                System.exit(0);
            }
            if(!error && (acrossVal < 1 || acrossVal > 3)){
                System.err.println("Enter a valid integer between 1 and 3, try again.\n");
                error = true;
            }
        }while(error);
    }

    private void getDownVal(){
        do {
            System.out.println("Across?");

            try {
                error = false;
                downVal = input.nextInt();

            }catch(Exception e){
                input.nextLine();
                System.err.println("Please enter a valid integer (1-3).\n");
                error = true;
            }
            
            if(downVal == 0){
                System.exit(0);
            }

            if(!error && (downVal < 1 || downVal > 3)){
                System.err.println("Enter a valid integer between 1 and 3, try again.\n");
                error = true;
            }
        }while(error);
    }

    private String getToken(){
        if(game.getCurrentPlayer() == 2){
            token = "O";
        }else{
            token = "X";
        }
        return token;
    }


    /**
     *  toString method
     * @return String to return
     */
    @Override
    public String toString(){
        return "Current Player: " + token + "\nCurrent Across Value: " + acrossVal
                +"\nCurrent Down Value: " + downVal;
    }

    public static void main(String[] args){
        TextUI text = new TextUI();
        text.play();
    }
}