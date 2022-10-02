

public class Game{

    private Board board;
    private boolean[][] visited;
    private char[][] cellTags;

    public Game(){
        setBoard(10, 10, 25);
    }

    @Override
    public String toString(){
        return "";
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

    private void makeNewVisited(){
        visited = new boolean[board.getHeight()][board.getWidth()];
    }

    private void setCellTags(){
        cellTags = new char[board.getHeight()][board.getWidth()];
        int[][] lookDirs = new int[][]{
            {1, 0},
            {0 , 1},
            {-1, 0},
            {0, -1},
            {1, 1},
            {1 , -1},
            {-1, 1},
            {-1, -1}
        };
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