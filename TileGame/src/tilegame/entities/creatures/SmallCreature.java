package tilegame.entities.creatures;

import tilegame.Handler;

import tilegame.gfx.Animation;
import tilegame.gfx.Assets;
import tilegame.tiles.Tile;

import java.awt.*;

public class SmallCreature extends Creature{

    private Animation anim;
    private boolean left;
    private int ticks=0;

    public SmallCreature(Handler handler, float x, float y, boolean left, int speed) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH*2, Creature.DEFAULT_CREATURE_HEIGHT*2);
        this.speed = speed;
        this.left = left;
        bounds.x = 15;
        bounds.y = 25;//28
        bounds.width = 64-27;
        bounds.height = 64-30;//-35

        setIsDoingDamage(true);
        //Animation
        anim = new Animation(250, Assets.smallCreature);

        canJump = true;

        jump(17);
        if(xMove == 0){
            if(this.left) xMove = -speed;
            else xMove = speed;
        }
    }

    public void tick(){

        anim.tick();

        if(isDead()){
            ticks++;
            if(ticks > 60) {
                handler.getWorld().getEntityManager().removeEntityEntirely(this);
                return;
            }
        }else {
            move();
            checkForDamageWithTiles();
        }


    }
    public void move(){
        moveX();
        moveY();

    }

    public void moveX(){
        if(xMove > 0){//Moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                canJump = false;
                falling = true;
                x += xMove;
            }else{
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
                xMove = -speed;
            }

        }else if(xMove < 0){//Moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                canJump = false;
                falling = true;
                x += xMove;
            }else{
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
                xMove = speed;
            }

        }
    }

    public void render(Graphics g){
        g.drawImage(anim.getCurrentFrame(),(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),anim.getCurrentFrame().getWidth(),anim.getCurrentFrame().getHeight(),null);
    }

}
