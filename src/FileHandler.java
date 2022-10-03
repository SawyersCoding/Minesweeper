package src;

import java.io.File;

import javafx.scene.image.Image;

public class FileHandler {

    private static final String graphicPath = "graphics" + File.separator;
    
    private FileHandler() {}

    public static Image getCellGraphic(char id){
        String path = graphicPath + id + ".png";
        return new Image(path);
    }

}
