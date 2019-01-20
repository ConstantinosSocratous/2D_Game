package tilegame.tiles;

import java.awt.image.BufferedImage;

public class LevelObject extends  UIObject {

    private String path,title;
    private boolean isEligableToJump = true;

    public LevelObject(BufferedImage[] image, int x, int y, String path, String title){
        super(image,x,y);
        this.path = path;
        this.title = title;
    }

    public String getPath(){return path;}
    public String getTitle(){return title;}

    public boolean isEligableToJump() {
        return isEligableToJump;
    }

    public void setEligableToJump(boolean eligableToJump) {
        isEligableToJump = eligableToJump;
    }
}
