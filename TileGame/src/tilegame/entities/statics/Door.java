package tilegame.entities.statics;

import tilegame.Handler;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;

import java.awt.*;

public class Door extends StaticEntity {

    private Animation anim;

    public Door(Handler handler, float x, float y){
        super(handler, x, y, 32*3, 64*3);
        init();
    }

    private void init(){
        bounds.x = 32*3-35; //12;
        bounds.y = 0;
        bounds.width = 38;//65;
        bounds.height = 64*3;
        anim = new Animation(500, Assets.door);
    }

    public void tick(){
        anim.tick();
    }

    public void render(Graphics g){
        g.drawImage(anim.getCurrentFrame(),(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),width,height,null);

        //g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),width,height);

    }

}
