package tilegame.entities;

import tilegame.Handler;
import tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;


public class MovingWall extends Entity{

    private BufferedImage img;
    private float xMove,yMove;
    private float speed;
    private int distance;
    private int ticks = 0;

    public MovingWall(Handler handler,float x, float y, int width, int height, int distance, boolean leftright,boolean updown, float speed){
        super(handler,x,y,width,height);
        this.distance = distance;
        this.speed = speed;

        bounds.x = 0;
        bounds.width =width;
        bounds.y = 0;
        bounds.height = height/32 *13;

        img = Assets.rock;

        if(!updown) yMove = 0;
        else yMove = speed;

        if(!leftright) xMove = 0;
        else xMove = speed;
    }

    public void tick(){

        move();
    }

    private void move(){
        if(ticks > distance || ticks < -distance){
            if(xMove > 0) xMove = -speed;
            else if(xMove < 0) xMove = speed;

            if(yMove > 0) yMove = -speed;
            else if(yMove < 0)yMove = speed;

            ticks = 0;
        }
        ticks+=speed;
        x+=xMove;
        y+=yMove;
    }

    public void render(Graphics g){
        g.drawImage(img,(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),width,height,null);
    }

    public float getxMove(){return xMove;}
    public float getyMove(){return yMove;}
}
