package tilegame.entities.statics;

import tilegame.Handler;
import tilegame.entities.creatures.SmallCreature;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;

import java.awt.*;

public class EnemyGreen extends StaticEntity{

    private Animation anim;
    private int ticks = 0;
    private int maxTicks;

    public EnemyGreen(Handler handler, float x, float y, int cooldown){
        super(handler, x, y, 128, 128);
        maxTicks = cooldown;
        init();
    }

    private void init(){
        bounds.x = 15; //12;
        bounds.y = 55;
        bounds.width = 128-15;//65;
        bounds.height = 128-55;
        anim = new Animation(200, Assets.enemyZele);
    }

    public void tick(){
        anim.tick();
        ticks++;

        if(ticks > maxTicks){
            handler.getWorld().getEntityManager().addEntity(new SmallCreature(handler,this.x,this.y,true,3));
            handler.getWorld().getEntityManager().addEntity(new SmallCreature(handler,this.x+bounds.width,this.y,false,3));
            ticks=0;
        }


    }

    public void render(Graphics g){
        g.drawImage(anim.getCurrentFrame(),(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),width,height,null);

    }
}
