package tilegame.states;

import tilegame.Handler;
import tilegame.gfx.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {

    private BufferedImage bg1,bg2,bg3,bg4,bg5;
    private Handler handler;

    public Background(Handler h){
        bg1 = ImageLoader.loadImage("/textures/Background/bg1.png");
        bg2 = ImageLoader.loadImage("/textures/Background/bg2.png");
        bg3 = ImageLoader.loadImage("/textures/Background/bg3.png");
        bg4 = ImageLoader.loadImage("/textures/Background/bg4.png");
        bg5 = ImageLoader.loadImage("/textures/Background/bg5.png");
        this.handler = h;
    }

    public void render(Graphics g){
        g.drawImage(bg1, 0, 0, handler.getWidth(), handler.getHeight(), null);
        g.drawImage(bg2, 0, 0, handler.getWidth(), handler.getHeight(), null);
        g.drawImage(bg3, 0, 0, handler.getWidth(), handler.getHeight(), null);
        g.drawImage(bg4, 0, 0, handler.getWidth(), handler.getHeight(), null);
        g.drawImage(bg5, 0, 0, handler.getWidth(), handler.getHeight(), null);
    }
}
