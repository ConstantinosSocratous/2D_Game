package tilegame.tiles;

import tilegame.Handler;
import tilegame.gfx.ImageLoader;

import java.awt.image.BufferedImage;

public class UIObject {

    private int x;
    private int y;
    private int width,height;
    private BufferedImage image;
    private BufferedImage[] imageArr;
    private BufferedImage imageWhenOver;

    public UIObject(BufferedImage image, BufferedImage over, int x, int y){
        this.x = x;
        this.y = y;
        this.image = image;
        this.imageWhenOver = over;
        width = image.getWidth();
        height = image.getHeight();
    }

    public UIObject(BufferedImage[] image, int x, int y){
        this.x = x;
        this.y = y;
        this.imageArr = image;
        width = image[0].getWidth();
        height = image[0].getHeight();
    }

    public BufferedImage getImage(int num){
        if(num>=imageArr.length)
            return null;
        else return imageArr[num];
    }


    public boolean isMouseOver(Handler handler){
        int mouseX = handler.getMouseManager().getMouseX();
        int mouseY = handler.getMouseManager().getMouseY();
        if( mouseX >= this.getX() && mouseY >= this.getY()
                && mouseX<= this.getX() + this.getWidth()*2
                && mouseY<= this.getY() + this.getHeight()*2 )
            return true;
        else
            return false;

    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public int getX() {return x; }
    public int getY() {
        return y;
    }
    public BufferedImage getImageOver() {
        return imageWhenOver;
    }
    public BufferedImage getImage() {
        return image;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setImage(BufferedImage img) {
        this.image = img;
    }
    public void setImageOver(BufferedImage img) {
        this.imageWhenOver = img;
    }
}
