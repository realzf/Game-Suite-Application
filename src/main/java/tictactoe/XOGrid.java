/**
 * Description: This class is responsible for determining classical TTT game result
 */
package tictactoe;

public class XOGrid extends GameGrid{

    /**
     * Arg constructor
     * @param wide Width of grid
     * @param tall Height of grid
     */
    public XOGrid(int wide, int tall) {
        super(wide, tall);
    }

    /**
     * Checks if a player has won horizontally
     * @return Boolean true if winner found, false otherwise
     */
    public boolean horizontalWin(){
        for(int i = 1; i <= getHeight(); i++){
            if(rowCheck(i)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a player has won vertically
     * @return Boolean true if winner found, false otherwise
     */
    public boolean verticalWin(){
        for(int i = 1; i <= getWidth(); i++){
            if(columnCheck(i)){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a player has won diagonally
     * @return Boolean true if winner found, false otherwise
     */
    public boolean diagonalWin(){
        if(ascendingDiagonalCheck() || descendingDiagonalCheck()){
            return true;
        }
        return false;
    }

    /* Checks if user chose valid location
     * @return Boolean true if valid location, false otherwise
     */
    public boolean validateLocation(int across, int down){
        if(getValue(across,down).equals(" ")){
            return true;
        }
        return false; 
    }

    private boolean rowCheck(int row){
        boolean match = false;

        if(!getValue(1,row).equals(" ")){
            match = getValue(1,row).equals(getValue(2,row));
            if(match){
                match = getValue(1,row).equals(getValue(3,row));
            }
        }
        return match;
    }

    private boolean columnCheck(int col){
        boolean match = false;

        if(!getValue(col,1).equals(" ")){
            match = getValue(col,1).equals(getValue(col,2));
            if(match){
                match = getValue(col,1).equals(getValue(col,3));
            }
        }
        return match;
    }

    private boolean descendingDiagonalCheck() {
        boolean match = false;

        if (!getValue(1, 1).equals(" ")) {
            match = getValue(1, 1).equals(getValue(2, 2));
            if (match) {
                match = getValue(1, 1).equals(getValue(3, 3));
            }
        }
        return match;
    }

    private boolean ascendingDiagonalCheck() {
        boolean match = false;

        if(!getValue(3,1).equals(" ")){
            match = getValue(3,1).equals(getValue(2,2));
            if(match){
                match = getValue(3,1).equals(getValue(1,3));
            }
        }
        return match;
    }

    
    /* toString method
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
                if(j < 3) {
                    str.append(",");
                }
            }
            str.append("\n");
        }
        return str.toString();
    }
}
