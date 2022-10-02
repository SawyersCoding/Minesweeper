package src;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MinesweeperGUI extends Application {

    private Game game;
    private Button gameStateButton;
    private Label gameResultLabel;
    private Button[][] buttons;
    private VBox grid;

    @Override
    public void start(Stage stage) throws Exception {
        gameStateButton = new Button("Reset");
        gameStateButton.setAlignment(Pos.TOP_RIGHT);
        // gameStateButton.setMinHeight(30);
        // gameStateButton.setMaxHeight(30);
        // gameStateButton.setMinWidth(30);
        // gameStateButton.setMaxWidth(30);
        gameStateButton.setPadding(new Insets(20));
        gameStateButton.setOnAction(this::reset);

        gameResultLabel = new Label();

        grid = new VBox();
        reset(new ActionEvent());

        VBox rightSide = new VBox();
        rightSide.setAlignment(Pos.CENTER);
        rightSide.getChildren().addAll(gameStateButton, gameResultLabel);

        //Overall Pane
        HBox pane = new HBox(10);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(grid, rightSide);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Minesweeper");
        stage.show();        
    }
    
    private void reset(ActionEvent e){
        game = new Game();
        grid.getChildren().clear();
        gameResultLabel.setText("");
        fillGrid();
    }

    private void fillGrid(){
        int buttonSize = 30;
        double fontSize = 16;
        buttons = new Button[game.getBoard().getHeight()][game.getBoard().getWidth()];
        for(int row = 0; row < game.getBoard().getHeight(); row++){
            HBox curRow = new HBox();
            for(int col = 0; col < game.getBoard().getWidth(); col++){
                Button cellButton = new Button();
                cellButton.setMaxHeight(buttonSize);
                cellButton.setMinHeight(buttonSize);
                cellButton.setMaxWidth(buttonSize);
                cellButton.setMinWidth(buttonSize);
                cellButton.setFont(Font.font(fontSize));
                cellButton.setTextAlignment(TextAlignment.CENTER);
                int buttonRow = row;
                int buttonCol = col;
                cellButton.setOnAction(e -> onClickCell(buttonRow, buttonCol));
                buttons[row][col] = cellButton;
                curRow.getChildren().add(cellButton);
            }
            grid.getChildren().add(curRow);
        }
    }

    private void onClickCell(int row, int col){
        try{
            game.selectCell(row, col);
        } catch (CellOutOfBoundsException e){
            e.printStackTrace();
        }
        for(int i = 0; i < game.getBoard().getHeight(); i++){
            for(int j = 0; j < game.getBoard().getWidth(); j++){
                if(game.isRevealed(i, j)) {
                    buttons[i][j].setDisable(true);
                    buttons[i][j].setText(String.valueOf(game.getCellTag(i, j)));
                }
            }
        }
        //Check game over / game win
        if(game.isGameOver() || game.isWon()){
            for(Button[] arr : buttons){
                for(Button b : arr){
                    b.setDisable(true);
                }
            }
        }

        if(game.isGameOver()){
            gameResultLabel.setText("You lose!");
            System.out.println("You lose!");
        }
        if(game.isWon()){
            gameResultLabel.setText("You win!");
            System.out.println("You win!");
        }
    }
}
