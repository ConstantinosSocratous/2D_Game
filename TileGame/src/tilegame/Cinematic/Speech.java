package tilegame.Cinematic;

import java.awt.image.BufferedImage;

public class Speech {

    private int x;
    private int y;
    BufferedImage image;

    public Speech(BufferedImage image, int x, int y){
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public BufferedImage getImage(){
        return image;
    }

    public int getX(){return x;}

    public int getY(){return y;}
}
