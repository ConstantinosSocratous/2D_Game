package tilegame.entities.creatures;

import tilegame.Handler;
import tilegame.entities.Bullet;
import tilegame.entities.Entity;
import tilegame.entities.statics.CheckPoint;
import tilegame.entities.statics.Trap;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;
import tilegame.states.GameState;
import tilegame.tiles.Tile;

import java.awt.*;

public class Enemy extends Creature {

    private final int SHOOT_COOLDOWN = 60;
    private Animation animStatic;
    private int helperTicksCooldown = 60;

    public Enemy(Handler handler, float x, float y){
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT+16);

        bounds.x = 21;
        bounds.y = 0;
        bounds.width = 64-21-21;
        bounds.height = 64+16-1;

        animStatic = new Animation(500, Assets.playerEnemyStatic);
    }

    @Override
    public void tick() {
        //Animations
        animStatic.tick();

        //Movement
        move();


        helperTicksCooldown++;
        if(playerFoundLeft() && helperTicksCooldown >= SHOOT_COOLDOWN){
            shoot(true,this);
            helperTicksCooldown = 0;
        }

        Entity e3 = getEntityWithCollision(0f, 0f);
        if (e3 != null) {
          if(e3 instanceof  Bullet){
                //System.out.println(x + "  " + e3.getX());
              if( !(((Bullet) e3).getFrom() instanceof  Enemy))
                  decreaseHealth(100);
          }
        }

        if(isDead()) handler.getWorld().getEntityManager().deleteEntity(this);
        fall();
    }

    private boolean playerFoundLeft(){
        int tempX = (int)(bounds.x+x)/64;
        int tempY = (int)(bounds.y+y)/64;

        while(!getTile(tempX,tempY).isSolid()){
            //System.out.println(getTile(tempX,tempY).getId());
            Rectangle rThis = new Rectangle((int)(tempX*64-bounds.x),(int)getY(), (int)this.bounds.width,(int)this.bounds.height);

            Player tempPl = handler.getWorld().getEntityManager().getPlayer();
            int plX = (int) (tempPl.getRectangle().getBounds().getX()+tempPl.getX());
            int plY = (int) (tempPl.getRectangle().getBounds().getY()+tempPl.getY());
            int plW = (int) (tempPl.getRectangle().getBounds().getWidth());
            int plH = (int) (tempPl.getRectangle().getBounds().getHeight());

            Rectangle rPl = new Rectangle(plX,plY,plW,plH);

            if(rThis.intersects(rPl)) {
                return true;
            }

            tempX -= 1;

            if(getTile(tempX,tempY).isSolid()) break;
        }
        return false;
    }

    public Tile getTile(int xIn, int yIn){
        return handler.getWorld().getTile(xIn,yIn);
    }

    @Override
    public void render(Graphics g){
        g.drawImage(animStatic.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }


}
