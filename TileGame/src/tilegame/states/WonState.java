package tilegame.states;

import tilegame.Handler;
import tilegame.gfx.Animation;
import tilegame.gfx.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WonState {
    private BufferedImage[] won;
    private Animation animWon;
    private Handler handler;

    public WonState(Handler handler){
        this.handler = handler;
        init();
    }

    public void init(){
        won = new BufferedImage[3];
        won[0] = ImageLoader.loadImage("/textures/won/won1.png");
        won[1] = ImageLoader.loadImage("/textures/won/won2.png");
        won[2] = ImageLoader.loadImage("/textures/won/won3.png");

        animWon = new Animation(500, won);
    }

    public void tick(){
        animWon.tick();
    }

    public void render(Graphics g){
        BufferedImage temp = animWon.getCurrentFrame();
        g.drawImage(temp,handler.getWidth()/2- temp.getWidth()/2,handler.getHeight()/2-temp.getHeight()/2, temp.getWidth(), temp.getHeight(),null);
    }
}
