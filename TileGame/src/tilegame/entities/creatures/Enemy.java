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

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Creature {

    private final int SHOOT_COOLDOWN = 60;
    private int JUMP_COOLDOWN;

    private Animation animStatic;
    private int helperTicksJumpCooldown;
    private int helperTicksCooldown = 60;
    private boolean shouldJump = false;

    public Enemy(Handler handler, float x, float y, int ticksPerJump){
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT+16);

        bounds.x = 21;
        bounds.y = 0;
        bounds.width = 64-21-21;
        bounds.height = 64+16-1;
        JUMP_COOLDOWN = ticksPerJump;
        helperTicksJumpCooldown = ticksPerJump;
        animStatic = new Animation(500, Assets.playerEnemyStatic);
    }

    @Override
    public void tick() {
        //Animations
        animStatic.tick();

        //Movement
        move();


        helperTicksCooldown++;
        helperTicksJumpCooldown++;
        if(bulletFound() && helperTicksJumpCooldown >= JUMP_COOLDOWN){
            jump(20);
            helperTicksJumpCooldown = 0;
        }
        if(playerFoundLeft() && helperTicksCooldown >= SHOOT_COOLDOWN){
            shoot(true,this,10);
            helperTicksCooldown = 0;
        }
        if(playerFoundRight() && helperTicksCooldown >= SHOOT_COOLDOWN){
            shoot(false,this,10);
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
        //fall();
    }

    private boolean bulletFound(){

        ArrayList<Entity> bullets = handler.getWorld().getEntityManager().getBullets();

        Rectangle rThis = new Rectangle((int)(getX()+bounds.x),(int)getY(),64,96);

        for(Entity e: bullets){
            Bullet b = ((Bullet)e);
            if(b.getFrom() instanceof Enemy) continue;
            int plX = (int) (b.getRectangle().getBounds().getX()+b.getX()-100);
            int plY = (int) (b.getRectangle().getBounds().getY()+b.getY());
            int plW = (int) (b.getRectangle().getBounds().getWidth()+200);
            int plH = (int) (b.getRectangle().getBounds().getHeight());

            Rectangle rBullet = new Rectangle(plX,plY,plW,plH);
            if(rThis.intersects(rBullet)) {
                return true;
            }else{
               // System.out.println(rBullet);
                //System.out.println(rThis);
                //System.out.println();
            }
        }

        return false;
    }

    /**
     * Check if there is a player in the left side
     * @return boolean
     */
    private boolean playerFoundLeft(){
        int tempX = (int)(bounds.x+x)/64;
        int tempY = (int)(bounds.y+y)/64;

        while(!getTile(tempX,tempY).isSolid()){
            Rectangle rThis = new Rectangle((int)(tempX*64),(int)getY(),64,96);// (int)this.bounds.width,(int)this.bounds.height);

            Player tempPl = handler.getWorld().getEntityManager().getPlayer();
            int plX = (int) (tempPl.getRectangle().getBounds().getX()+tempPl.getX());
            int plY = (int) (tempPl.getRectangle().getBounds().getY()+tempPl.getY());
            int plW = (int) (tempPl.getRectangle().getBounds().getWidth());
            int plH = (int) (tempPl.getRectangle().getBounds().getHeight());

            Rectangle rPl = new Rectangle(plX,plY,plW,plH);

            if(rThis.intersects(rPl)) return true;

            tempX -= 1;
        }
        return false;
    }

    /**
     * Check if there is a player in the right side
     * @return boolean
     */
    private boolean playerFoundRight(){
        int tempX = (int)(bounds.x+x)/64;
        int tempY = (int)(bounds.y+y)/64;

        while(!getTile(tempX,tempY).isSolid()){
            Rectangle rThis = new Rectangle((int)(tempX*64),(int)getY(), 64,96);//(int)this.bounds.width,(int)this.bounds.height);

            Player tempPl = handler.getWorld().getEntityManager().getPlayer();
            int plX = (int) (tempPl.getRectangle().getBounds().getX()+tempPl.getX());
            int plY = (int) (tempPl.getRectangle().getBounds().getY()+tempPl.getY());
            int plW = (int) (tempPl.getRectangle().getBounds().getWidth());
            int plH = (int) (tempPl.getRectangle().getBounds().getHeight());

            Rectangle rPl = new Rectangle(plX,plY,plW,plH);

            if(rThis.intersects(rPl)) return true;

            tempX += 1;
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
