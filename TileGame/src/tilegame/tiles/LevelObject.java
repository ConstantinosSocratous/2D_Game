package tilegame.tiles;

import java.awt.image.BufferedImage;

public class LevelObject extends  UIObject {

    private String path;

    public LevelObject(BufferedImage[] image, int x, int y, String s){
        super(image,x,y);
        this.path = s;
    }

    public String getPath(){return path;}

}
