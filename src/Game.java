package src;
import java.util.Queue;
import java.util.LinkedList;

public class Game{

    private Board board;
    private boolean[][] visited;
    private char[][] cellTags;
    private boolean gameOver;
    private boolean gameWon;
    private final int[][] lookDirs = new int[][]{
        {1, 0},
        {0 , 1},
        {-1, 0},
        {0, -1},
        {1, 1},
        {1 , -1},
        {-1, 1},
        {-1, -1}
    };

    public Game(){
        setBoard(10, 10, 25);
        gameOver = false;
        gameWon = false;
    }

    public void selectCell(int row, int col) throws CellOutOfBoundsException {
        if(!isValidCell(row, col)) throw new CellOutOfBoundsException(row, col);
        if(visited[row][col]) return;
        visited[row][col] = true;
        if(board.getCell(row, col).hasMine()){
            gameOver = true;
        }
        else if(cellTags[row][col] == '0'){
            revealCellsFrom(row, col);
        }
        if(!gameOver) checkWin();
    }

    public boolean isWon() { return gameWon; }

    public boolean isGameOver() { return gameOver; }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for(int col = 0; col < board.getWidth(); col++){
            sb.append(" ").append(col).append(" ");
        }
        sb.append("\n");
        for(int row = 0; row < board.getHeight(); row++){
            sb.append(row).append(" ");
            for(int col = 0; col < board.getWidth(); col++){
                char tag = visited[row][col] ? cellTags[row][col] : ' ';
                sb.append("[").append(tag).append("]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Board getBoard() { return board; }

    public void setBoard(int width, int height, int percentMines){
        board = new Board(width, height, percentMines);
        makeNewVisited();
        setCellTags();
    }

    public void setBoardDimensions(int width, int height){
        setBoard(width, height, board.getPercentMines());
    }

    public void setBoardPercentMines(int percentMines){
        setBoard(board.getWidth(), board.getHeight(), percentMines);
    }

    private void checkWin(){
        int numberOfMines = (board.getHeight() * board.getWidth() * board.getPercentMines())/100;
        int unvisitedCount = 0;
        for(int row = 0; row < board.getHeight(); row++){
            for(int col = 0; col < board.getWidth(); col++){
                if(!visited[row][col]) unvisitedCount++;
            }
        }
        gameWon = unvisitedCount == numberOfMines;
    }

    private void revealCellsFrom(int row, int col) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(row);
        q.add(col);
        while(!q.isEmpty()){
            int curRow = q.poll();
            int curCol = q.poll();
            for(int[] dir : lookDirs){
                int nextRow = curRow + dir[0];
                int nextCol = curCol + dir[1];
                if(isValidCell(nextRow, nextCol) && !visited[nextRow][nextCol] && cellTags[nextRow][nextCol] != 'X'){
                    visited[nextRow][nextCol] = true;
                    if(cellTags[nextRow][nextCol] == '0'){
                        q.add(nextRow);
                        q.add(nextCol);
                    }
                }
            }
        }
    }

    private void makeNewVisited(){
        visited = new boolean[board.getHeight()][board.getWidth()];
    }

    private void setCellTags(){
        cellTags = new char[board.getHeight()][board.getWidth()];
        for(int row = 0; row < board.getHeight(); row++){
            for(int col = 0; col < board.getWidth(); col++){
                if(board.getCell(row, col).hasMine()) cellTags[row][col] = 'X';
                else{
                    int count = 0;
                    for(int[] dir : lookDirs){
                        int rowToCheck = row + dir[0];
                        int colToCheck = col + dir[1];
                        if(isValidCell(rowToCheck, colToCheck) && board.getCell(rowToCheck, colToCheck).hasMine()) {
                            count++;
                        }
                    }
                    cellTags[row][col] = String.valueOf(count).charAt(0);
                }
            }
        }
    }

    private boolean isValidCell(int row, int col){
        return row >= 0 && row < board.getHeight() && col >= 0 && col < board.getWidth();
    }
}