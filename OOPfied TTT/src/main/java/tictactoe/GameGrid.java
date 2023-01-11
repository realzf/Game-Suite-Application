/**
 * Description: This class is an abstract class responsible for determining game result
 */
package tictactoe;

import java.util.Iterator;

public abstract class GameGrid extends boardgame.Grid{

    /**
     * Arg constructor
     * @param across Across value of board
     * @param down   Down value of board
     */
    public GameGrid(int across, int down){
        super(across,down);
    }

    /**
     * Checks if board is full
     * @return Boolean if board is full or not
     */
    public boolean isFull(){
        Iterator<String> iter = iterator();
        int count = 0;
        while(iter.hasNext()){
            if(!iter.next().equals(" ")){
                count++;
            }
        }

        if(count == getWidth()*getHeight()){
            return true;
        }
        return false;
    }

    /**
     * Checks if a player has won horizontally
     * @return True if there is a winner, false otherwise
     */
    public abstract boolean horizontalWin();

    /**
     * Checks if a player has won vertically
     * @return True if there is a winner, false otherwise
     */
    public abstract boolean verticalWin();

    /**
    * Checks if a player has won diagonally
    * @return True if there is a winner, false otherwise
    */
    public abstract boolean diagonalWin();

    /**
     * Checks if location on board entered by user is valid
     * @param across Across value on board
     * @param down Down value on board
     * @return
     */
    public abstract boolean validateLocation(int across, int down);


    /**
     *  toString method
     * @return String to return
     */
    @Override
    public String toString(){
        return "This the is GameGrid class.";
    }

}
