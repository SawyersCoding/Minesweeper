public class Cell {
    
    // Keeps track of whether there is a mine in this Cell.
    private boolean hasMine;

    /**
     * Creates a Cell that does not contain a mine.
     */
    public Cell(){
        hasMine = false;
    }

    /**
     * Creates a Cell that has a mine if the given boolean is true or a Cell without a mine if 
     * false.
     * @param hasMine Whether this Cell has a mine or not.
     */
    public Cell(boolean hasMine){
        this.hasMine = hasMine;
    }

    /**
     * Gets whether this Cell has a mine or not.
     * 
     * @return True if this Cell contains a mine. False otherwise.
     */
    public boolean hasMine(){
        return hasMine;
    }

}
