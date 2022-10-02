package src;
public class CellOutOfBoundsException extends Exception {    
    public CellOutOfBoundsException(int row, int col){
        super("The cell at row " + row + " and column " + col + " does not exist.");
    }    
}
