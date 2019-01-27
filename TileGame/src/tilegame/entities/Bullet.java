package tilegame.entities;

import tilegame.Handler;
import tilegame.entities.creatures.Creature;
import tilegame.entities.creatures.Enemy;
import tilegame.entities.creatures.Mushroom;
import tilegame.entities.creatures.Player;
import tilegame.entities.statics.Coin;
import tilegame.gfx.Assets;
import tilegame.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends Creature {

    private float speed;
    private BufferedImage img;
    protected float maxDY = 1f;
    protected float gravity = 0.02f;
    private boolean collusion = false;
    private Creature c;

    public Bullet(Handler handler, float x, float y, int width, int height, boolean leftDirections, Creature from,float speed){
        super(handler,x,y,width,height);
        if(from instanceof Player)
            img = Assets.bullet;
        else img = Assets.enemyBullet;
        xMove = 0;
        this.c = from;
        this.speed = 12f;

        bounds.x = width/32 * 4;
        bounds.width = width/32* 26;
        bounds.y = height/32 * 13;
        bounds.height = height/32 * 5;


        if(xMove == 0){
            if(leftDirections) xMove = -speed;
            else xMove = speed;
        }
    }

    public Creature getFrom(){return c;}

    public void tick(){
        this.move();
    }

    public void move(){
        this.moveX();
        this.moveY();

        // COLLUSION WITH ENTITY
        Entity e1 = getEntityWithCollision(0f,0f);
        if(e1 != null){
            if(e1 instanceof Mushroom){
                /*((Mushroom) e1).deleteMe();
                handler.getWorld().getEntityManager().deleteEntity(e1);
                collusion = true;*/
            }
        }
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
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width;
                collusion = true;
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
                collusion = true;
            }

        }
    }

    public void moveY(){
       if(yMove > 0){//Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            if(ty > handler.getGame().getHeight())	return;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                falling = true;
                y += yMove;
                //System.out.println(x + " " + y + " " + "JUMP-111");
            }else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height;
                collusion = true;
            }
        }

        //fall();
    }
    public void fall(){
        yMove += gravity;
        if (yMove > maxDY) yMove = maxDY;
    }

    public boolean isCollusion(){return collusion;}
    public void setCollusion(Boolean bool){collusion = bool;}

    public void render(Graphics g){
        g.drawImage(img,(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),width,height,null);
    }

}
