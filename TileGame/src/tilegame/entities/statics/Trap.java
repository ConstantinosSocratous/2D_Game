package tilegame.entities.statics;

import tilegame.Handler;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;

import java.awt.*;

public class Trap extends StaticEntity {

    private Animation anim;
    private boolean isDoingDamage;

    public Trap(Handler handler, float x, float y, int width, int height, int AnimSpeed) {
        super(handler, x, y, width, height);

        bounds.x =  0;
        bounds.width = width;

        bounds.y = height/32 * 8;
        bounds.height = height - bounds.y ;

        anim = new Animation(AnimSpeed, Assets.trap);
    }

    public void tick(){
        anim.tick();
        if(anim.getIndex() == 0){
            bounds.x = 0;
            bounds.y = 24;
            bounds.width = width;
            bounds.height = 40;
            setIsDoingDamage(true);
        }else if(anim.getIndex() == 1){
            bounds.x = 0;
            bounds.y = 48;
            bounds.width = width;
            bounds.height = 16;
            setIsDoingDamage(false);
        }
    }

    public void render(Graphics g){
        g.drawImage(anim.getCurrentFrame(),(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),width,height,null);
    }

    public boolean isDoingDamage(){
        return isDoingDamage;
    }
    public void setIsDoingDamage(boolean isDoingDamage){
        this.isDoingDamage = isDoingDamage;
    }

}
