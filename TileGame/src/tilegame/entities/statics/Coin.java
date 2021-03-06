package tilegame.entities.statics;

import tilegame.Handler;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;

import java.awt.*;

public class Coin extends StaticEntity {

    private Animation anim;
    private boolean isGenerated;

    public Coin(Handler handler, float x, float y, boolean isGenerated){
        super(handler, x, y, 32, 32);
        bounds.x = 6;
        bounds.y = 5;
        bounds.width = 32-8;
        bounds.height = 32-5;
        this.isGenerated = isGenerated;
        anim = new Animation(100, Assets.coin);
    }

    public void tick(){
        anim.tick();
    }

    public void render(Graphics g){
        g.drawImage(anim.getCurrentFrame(),(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),width,height,null);
    }

    public boolean isGenerated(){return isGenerated;}
    public void setGenerated(boolean bool){isGenerated = bool;}



}
