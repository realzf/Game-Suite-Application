/**
 * Description: This class is responsible for calling necessary classes to run sepcific game
 */
package tictactoe;

public class TicTacToe extends boardgame.BoardGame{
    private String gameStateMessage;
    private boolean done;
    private int currentPlayer;
    private int countTurns;

    /**
     * Arg constructor
     * @param wide Width of grid
     * @param tall height of grid
     */
    public TicTacToe(int wide, int tall){
        super(wide, tall);
        done = false;
        currentPlayer = 1;
        countTurns = wide * tall;
        setGameStateMessage(nextPlayerMessage());
    }

    /**
     * Returns current player
     * @return Current player
     */
    public int getCurrentPlayer(){
        return currentPlayer;
    }

    /**
     * Sets done according to game state
     * @param state State of game (done or not)
     */
    public void setGameOver(boolean state){
        done = state;
    }

    /**
     * Sets the grid and validates input
     * @return Boolean true if turn is valid input, false otherwise
     */
    @Override
    public  boolean takeTurn(int across, int down, String input){
        if(grid().validateLocation(across,down)) {
            countTurns--;
            setValue(across, down, input);
            switchPlayer();
            setGameStateMessage(nextPlayerMessage());
            return true;
        }
        return false;
    }

    /**
     * Stubbed out method
     * @return Boolean is false
     */
    @Override
    public  boolean takeTurn(int across, int down, int input){
        return false;
    }

    /**
     *  Sets the grid according to specifc game
     * @see boardgame.BoardGame#setGrid(boardgame.Grid)
     */
    @Override
    public void setGrid(boardgame.Grid grid){
        super.setGrid(grid);
        setGameOver(false);
    }

    /**
     *  Checks if their is a winner and if the game is finished
     * @returns Boolean true if winner found or game is tied, false other wise
     */
    @Override
    public boolean isDone(){
        if(!done){
            done = grid().horizontalWin();
        }

        if(!done){
            done = grid().diagonalWin();
        }

        if(!done){
            done = grid().verticalWin();
        }

        if(done){
            switchPlayer();
            setGameStateMessage("Player " + getWinner() + " has won!");
        }else if(countTurns == 0){
            done = true;
            setGameStateMessage("Game is a tie!"); 
        }
        return done;
    }

    /**
     * Returns the winning player of current game
     * @return Returns winner
     */
    @Override
    public  int getWinner(){
        return currentPlayer;
    }

    /**
     * Returns current game state message
     * @return Current game state message
     */
    @Override
    public String getGameStateMessage(){
        return gameStateMessage;
    }


    private void switchPlayer(){
        if(getCurrentPlayer() == 1){
            currentPlayer = 2;
        }else{
            currentPlayer = 1;
        }
    }


    private GameGrid grid(){
        return (GameGrid) getGrid();
    }

    /* The gameStateMessage can be used by both the GUI
    and the TextUI to easily communicate the current state
    to the user.  Private methods to compose the desired message promote
    encapsulation */

    private void setGameStateMessage(String msg){
        gameStateMessage = msg;
    }

    private String nextPlayerMessage(){
        String player = "Player 1";
        if(currentPlayer == 2){
            player = "Player 2";
        }

        return("Turn: " + player);
    }

    /**
     * Static method to facilitate changing the grid type without adding coupling.
     * @param gameType Specific game type
     * @param wide Width of grid
     * @param tall Height of grid
     * @return The grid
     */
    public static GameGrid newGrid(int gameType, int wide, int tall){
        if(gameType == 1){
            return new XOGrid(wide,tall);
        }else{
            return new NumTTTGrid(wide,tall);
        }
    }

    /**
     * Gets the string format of the grid to save to file
     * @return String format of grid
     */
    public String getStringForSaving(){
        return grid().toString();
    }
}

