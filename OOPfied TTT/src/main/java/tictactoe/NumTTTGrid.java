/**
 * Name: Zaeem 
 * Date: Nov 26 2022
 * Description: This class is responsible for determining numerical TTT game result
 */
package tictactoe;

public class NumTTTGrid extends GameGrid {

    /**
     * Arg constructor
     * @param wide Width of grid
     * @param tall Height of grid
     */
    public NumTTTGrid(int wide, int tall) {
        super(wide, tall);
    }

    /**
     *  Finds if player has won horizontally
     * @return Boolean true if winner found, false otherwise
     */
    @Override
    public boolean horizontalWin() {
        for(int i = 1; i <= getHeight(); i++){
            if(rowCheck(i)){
                return true;
            }
        }
        return false;
    }

    /**
     *  Finds if player has won vertically
     * @return Boolean true if winner found, false otherwise
     */
    @Override
    public boolean verticalWin() {
        for(int i = 1; i <= getWidth(); i++){
            if(columnCheck(i)){
                return true;
            }
        }
        return false;
    }

    /**
     *  Finds if player has won diagonally
     * @return Boolean true if winner found, false otherwise
     */
    @Override
    public boolean diagonalWin() {
        if(ascendingDiagonalCheck() || descendingDiagonalCheck()){
            return true;
        }
        return false;
    }

    private boolean rowCheck(int row){
        int[] numbers = new int[3];
        int sum = 0;

        for(int i = 0; i < 3; i++){
            if(!getValue(i+1,row).equals(" ")){
                numbers[i] = Integer.parseInt(getValue(i+1,row));
            }else{
                return false;
            }
        }

        for (int i = 0; i < 3; i++){
            sum+=numbers[i];
        }

        if(sum == 15){
            return true;
         }
        return false;
    }

    private boolean columnCheck(int col){
        int[] numbers = new int[3];
        int sum = 0;

        for(int i = 0; i < 3; i++) {
            if (!getValue(col, i+1).equals(" ")) {
                numbers[i] = Integer.parseInt(getValue(col,i+1));
            }else{
                return false;
            }
        }

        for (int i = 0; i < 3; i++){
            sum+=numbers[i];
        }

        if(sum == 15){
            return true;
        }
        return false;
    }

    private boolean descendingDiagonalCheck() {
        int[] numbers = new int[3];
        int sum = 0;

        for(int i = 0; i < 3; i++) {
            if (!getValue(i+1, i+1).equals(" ")) {
                numbers[i] = Integer.parseInt(getValue(i+1,i+1));
            }else{
                return false;
            }
        }

        for (int i = 0; i < 3; i++){
            sum+=numbers[i];
        }

        if(sum == 15){
            return true;
        }
        return false;
    }

    private boolean ascendingDiagonalCheck() {
        int[] numbers = new int[3];
        int sum = 0;
        int index = 3;

        for(int i = 0; i < 3; i++) {
            if (!getValue(index, i+1).equals(" ")) {
                numbers[i] = Integer.parseInt(getValue(index,i+1));
            }else{
                return false;
            }
            index--;
        }

        for (int i = 0; i < 3; i++){
            sum+=numbers[i];
        }

        if(sum == 15){
            return true;
        }
        return false;
    }


    /**
     *  Determines if given location is valid
     * @return Boolean true if location is valid, false otherwise
     */
    @Override
    public boolean validateLocation(int across, int down) {
        if(getValue(across,down).equals(" ")){
            return true;
        }
        return false;
    }

    /**
     *  toString method
     * @return String to return
     */
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();

        for(int i = 1; i <= 3; i++){
            for(int j = 1; j <= 3; j++) {
                if(!getValue(i,j).equals(" ")) {
                    str.append(getValue(i, j));
                }
                if(j != 3) {
                    str.append(",");
                }
            }
            str.append("\n");
        }
        return str.toString();
    }
}