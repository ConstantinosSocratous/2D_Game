package tilegame.entities.statics;

import tilegame.Handler;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;
import tilegame.gfx.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tree extends StaticEntity{

    private BufferedImage img;

    public Tree(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);

        img = ImageLoader.loadImage("/textures/Background/tree1.png");
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.drawImage(img,(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),width,height,null);
    }

}
