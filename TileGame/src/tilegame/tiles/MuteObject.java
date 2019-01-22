package tilegame.tiles;

import java.awt.image.BufferedImage;

public class MuteObject extends UIObject {

    int currentImage = 0;
    public MuteObject(BufferedImage[] image, int x, int y){
        super(image,x,y);
    }

    public int getCurrentImage(){return currentImage;}

    public void setCurrentImage(int n){ currentImage = n;}
}
