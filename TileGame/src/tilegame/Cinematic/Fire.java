package tilegame.Cinematic;

import tilegame.Handler;
import tilegame.entities.statics.StaticEntity;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;

import java.awt.*;

public class Fire extends StaticEntity {

    private Animation anim;

    public Fire(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        anim = new Animation(900, Assets.fire);
    }

    public void tick(){
        anim.tick();

    }

    public void render(Graphics g){
        g.drawImage(anim.getCurrentFrame(),(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),width,height,null);
    }


}
