import java.util.ArrayList;
import java.util.Collections;

public class Board {
    
    private int width;
    private int height;
    private int percentMines;
    private Cell[][] cells;

    /**
     * Creates a board. Boards contain all of the Cells in a game of Minesweeper.
     * 
     * @param width The width of the Board.
     * @param height The height of the Board.
     * @param percentMines A number between [1, 99] dictating what percentage of 
     * Cells will have mines.
     */
    public Board(int width, int height, int percentMines){
        this.width = width;
        this.height = height;
        this.percentMines = percentMines;
        cells = new Cell[height][width];

        ArrayList<Cell> cellShuffler = new ArrayList<Cell>(width*height);
        int size = width*height;
        int numberOfMines = (size * percentMines)/100;
        for(int i = 0; i < size; i++){
            if(i < numberOfMines) cellShuffler.add(new Cell(true));
            else cellShuffler.add(new Cell(false));
        }
        Collections.shuffle(cellShuffler);
        for(int row = 0; row < height; row++){
            for(int col = 0; col < width; col++){
                cells[row][col] = cellShuffler.remove(0);
            }
        }
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getPercentMines() { return percentMines; }
    public Cell getCell(int row, int col) { 
        return cells[row][col]; 
    }
}
