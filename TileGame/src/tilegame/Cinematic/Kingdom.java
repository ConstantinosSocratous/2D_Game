package tilegame.Cinematic;

import tilegame.Handler;
import tilegame.entities.statics.StaticEntity;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;

import java.awt.*;
public class Kingdom extends StaticEntity {

    private Animation anim;

    public Kingdom(Handler handler, float x, float y, int width, int height){
        super(handler, x, y, 192*5, 96*4);



        anim = new Animation(250, Assets.kingdom);
    }

    public void tick(){
        anim.tick();
    }

    public void render(Graphics g){
        g.drawImage(anim.getCurrentFrame(),(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),width,height,null);
    }

}