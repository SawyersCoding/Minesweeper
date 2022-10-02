import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ConsoleMinesweeper {
    public static void main(String[] args) {
        Game game = new Game();
        while(!game.isGameOver() && !game.isWon()){
            System.out.println(game.toString());
            int[] selected = getCoordinates();
            try{
                game.selectCell(selected[0], selected[1]);
            } catch (CellOutOfBoundsException e){
                System.out.println("The coordinates [" + selected[0] + "," + selected[1] + "] are invalid. Check the bounds of the board again.");
            }
        }
        System.out.println(game.toString());

        if(game.isWon()){
            System.out.println("Congrats! You win!");
        }
        else{
            System.out.println("Oh no! You lose!");
        }
    }

    private static int[] getCoordinates() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] coordinates = new int[2];
        boolean isValid = false;
        while(!isValid){
            System.out.println("Enter cell: ");
            isValid = true;
            try{
                String[] line = br.readLine().split(" ");
                try{
                    coordinates[0] = Integer.parseInt(line[0]);
                    coordinates[1] = Integer.parseInt(line[1]);
                    
                } catch (NumberFormatException e){
                    try{
                        System.out.println("Row " + line[0] + " column " + line[1] + " is an invalid coordinate. Both values must be integers.");
                    } catch (ArrayIndexOutOfBoundsException e2){
                        System.out.println("Need more information. Cannot parse " + line[0] + ".");
                    }
                    isValid = false;
                }
                br.close();
            } catch(IOException e){
                e.printStackTrace();
                isValid = false;
            }
        }
        return coordinates;
    }
}
