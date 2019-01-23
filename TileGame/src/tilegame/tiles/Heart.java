package tilegame.tiles;

import tilegame.Handler;
import tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Heart {

    private final int NUMBER_OF_HEARTS = 3;

    private BufferedImage imgs;
    private int x,y;
    private Handler handler;
    private int currentNum;


    public Heart(Handler h,int x, int y){
        this.imgs = Assets.heart;
        this.x = x;
        this.y = y;
        this.handler=h;
        currentNum = NUMBER_OF_HEARTS;
    }

    public void tick(Graphics g){
        render(g);
    }

    public void render(Graphics g){
        if(currentNum==0)return;
        int tempX=0;
        for(int i=1;i<=currentNum;i++) {
            g.drawImage(imgs,x+tempX,y, imgs.getWidth()*2, imgs.getHeight() * 2, null);
            tempX-=60;
        }
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }
    public int getCurrentNum() {
        return this.currentNum;
    }
}
