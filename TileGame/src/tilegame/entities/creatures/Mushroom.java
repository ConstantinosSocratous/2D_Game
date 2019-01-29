package tilegame.entities.creatures;

import tilegame.Handler;
import tilegame.entities.Bullet;
import tilegame.entities.Entity;
import tilegame.entities.statics.Coin;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;
import tilegame.tiles.Tile;

import java.awt.*;

public class Mushroom extends Creature {

        private Animation anim;
        private boolean left;

    public Mushroom(Handler handler, float x, float y, boolean left, int speed) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH*2, Creature.DEFAULT_CREATURE_HEIGHT*2);
        this.speed = speed;
        this.left = left;
        bounds.x = 15;
        bounds.y = 25;//28
        bounds.width = 64-27;
        bounds.height = 64-30;//-35

        setIsDoingDamage(true);
        //Animation
        anim = new Animation(250, Assets.enemyMoving);
        if(xMove == 0){
            if(left) xMove = -speed;
            else xMove = speed;
        }
    }

    public void tick(){

        anim.tick();


        move();

        collisionWithBullet();

    }

    private void collisionWithBullet(){
        Entity e3 = getEntityWithCollision(0f, 0f);
        if (e3 != null) {
            if(e3 instanceof Bullet){
                if( !(((Bullet) e3).getFrom() instanceof  Enemy)) {
                    ((Bullet) e3).setCollusion(true);
                    handler.getWorld().getEntityManager().deleteEntity(this);
                    this.deleteMe();
                }
            }
        }
    }

    public void move(){
        //if(!checkEntityCollisions(xMove, 0f))
            moveX();
        //if(!checkEntityCollisions(0f, yMove))
            moveY();
    }

    public void moveX(){
        if(xMove > 0){//Moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                //falling = true;
                x += xMove;
            }else{
                //falling = false;
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
                xMove =-speed;
            }

        }else if(xMove < 0){//Moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                //falling = true;
                x += xMove;
            }else{
                //falling = false;
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
                xMove =speed;
            }

        }
    }

    public void moveY(){
        if(yMove < 0){//Up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                //falling = true;
                y += yMove;

            }else{
                //y += yMove;
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
                yMove = y;
                //falling = true;
            }

        }else if(yMove > 0){//Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            if(ty > handler.getGame().getHeight())	return;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) ){
                xMove = +speed;
            }
            if(!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                //falling = true;
                xMove = -speed;
                //y += yMove;
            }else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
                falling = false;

                //xMove = -speed;
                //yMove=0;
            }

        }
        fall();
    }

    public void deleteMe(){
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,(int)(getX()+20),getY()-95,true));
    }

    public void fall(){
        yMove += gravity;
        if (yMove > maxDY) yMove = maxDY;

    }

    public void render(Graphics g){
        g.drawImage(anim.getCurrentFrame(),(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),anim.getCurrentFrame().getWidth(),anim.getCurrentFrame().getHeight(),null);

    }



}
