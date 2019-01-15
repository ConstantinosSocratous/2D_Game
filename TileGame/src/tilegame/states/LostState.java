package tilegame.states;

import tilegame.Handler;
import tilegame.gfx.Animation;
import tilegame.gfx.ImageLoader;
import tilegame.gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LostState {

    private BufferedImage[] lost;
    private Animation animLost;
    private Handler handler;

    public LostState(Handler handler){
        this.handler = handler;
        init();
    }

    public void init(){
        lost = new BufferedImage[3];
        lost[0] = ImageLoader.loadImage("/textures/lost/lost1.png");
        lost[1] = ImageLoader.loadImage("/textures/lost/lost2.png");
        lost[2] = ImageLoader.loadImage("/textures/lost/lost3.png");

        animLost = new Animation(500, lost);
    }

    public void tick(){
        animLost.tick();
    }

    public void render(Graphics g){
        BufferedImage temp = animLost.getCurrentFrame();
        g.drawImage(temp,handler.getWidth()/2- temp.getWidth()/2,handler.getHeight()/2-temp.getHeight()/2, temp.getWidth(), temp.getHeight(),null);
    }

}
