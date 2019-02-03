package tilegame.entities.creatures;

import tilegame.Handler;
import tilegame.entities.Bullet;
import tilegame.entities.Entity;
import tilegame.entities.statics.Coin;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;
import tilegame.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Creature {

    private int SHOOT_COOLDOWN = 35;
    private final int MAX_CLEVERNESS = 10;

    protected float speed;
    private Animation animStatic,animStaticLeft;
    private int helperTicksCooldown = 60;
    private int poss;
    private boolean lookingRight = true;

    public Enemy(Handler handler, float x, float y, int possibility, float sp, String move, int cooldown){
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT+16);

        bounds.x = 21;
        bounds.y = 0;
        bounds.width = 64-21-21;
        bounds.height = 64+16-1;
        poss = possibility;
        this.speed = sp;
        this.SHOOT_COOLDOWN = cooldown;
        animStatic = new Animation(500, Assets.playerEnemyStatic);
        animStaticLeft = new Animation(500, Assets.playerEnemyStaticLeft);

        decideInitMoving(move);
    }

    private void decideInitMoving(String m) {
        if (m.equals("LEFT"))
            xMove = -this.speed;
        else if (m.equals("RIGHT"))
            xMove = this.speed;
        else xMove = 0;
    }

    @Override
    public void tick() {
        //Animations
        animStatic.tick();
        animStaticLeft.tick();
        //Movement
        move();

        helperTicksCooldown++;
        if(bulletFoundLeft() && getIfJump() && xMove<=0) {
            lookingRight = false;
            jump(20);
        }
        if(bulletFoundRight() && getIfJump() && xMove>=0){
            lookingRight = true;
            jump(20);
        }

        if(playerFoundLeft() && helperTicksCooldown >= SHOOT_COOLDOWN && xMove<=0) {
            shoot(true, this, 10);
            lookingRight = false;
            helperTicksCooldown = 0;
        }
        if(playerFoundRight() && helperTicksCooldown >= SHOOT_COOLDOWN && xMove>=0){
            shoot(false,this,10);
            lookingRight = true;
            helperTicksCooldown = 0;
        }

        collisionWithBullet();


        fall();
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
                x += xMove;
            }else{
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
                xMove =-speed;
            }

        }else if(xMove < 0){//Moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
            }else{
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
                canJump = false;
                falling = true;
                y += yMove;
            }else{
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
                yMove = y;
                falling = true;
                canJump = false;
            }

        }else if(yMove > 0){//Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            if(ty > handler.getGame().getHeight())	return;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
                canJump = false;
                falling = true;
                y += yMove;
            }else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
                falling = false;
                canJump = true;
            }
        }
        fall();
    }

    private void collisionWithBullet(){
        Entity e3 = getEntityWithCollision(0f, 0f);
        if (e3 != null) {
            if(e3 instanceof  Bullet){
                if( !(((Bullet) e3).getFrom() instanceof  Enemy)) {
                    handler.getWorld().getEntityManager().deleteEntity(this);
                    ((Bullet) e3).setCollusion(true);
                    handler.getWorld().getEntityManager().addEntity(new Coin(handler,(int)(getX()+20),getY()+20,true));
                }
            }
        }
    }

    /**
     * Decide if the enemy can jump based on given probability
     * @return Boolean
     */
    private boolean getIfJump(){
        Random rand = new Random();
        int n = rand.nextInt(MAX_CLEVERNESS) + 1;
        if(n <= poss) return true;
        else return false;
    }

    private boolean bulletFoundLeft() {
        ArrayList<Entity> bullets = handler.getWorld().getEntityManager().getBullets();
        Rectangle rThis = new Rectangle((int) (getX() + bounds.x), (int) getY(), 64, 96);

        for (Entity e : bullets) {
            Bullet b = ((Bullet) e);
            if (b.getFrom() instanceof Enemy) continue;
            int plX = (int) (b.getRectangle().getBounds().getX() + b.getX()-100);
            int plY = (int) (b.getRectangle().getBounds().getY() + b.getY());
            int plW = (int) (b.getRectangle().getBounds().getWidth() + 200);
            int plH = (int) (b.getRectangle().getBounds().getHeight());

            Rectangle rBullet = new Rectangle(plX, plY, plW, plH);
            if (rThis.intersects(rBullet) && b.getxMove()>0) {
                return true;
            } else {
            }
        }
        return false;
    }

    private boolean bulletFoundRight(){
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
            if(rThis.intersects(rBullet) && b.getxMove()<0) {
                return true;
            }else{}
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
        g.drawImage(getCurrentImage(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    private BufferedImage getCurrentImage(){
        if(xMove > 0) return animStatic.getCurrentFrame();
        else if(xMove < 0) return animStaticLeft.getCurrentFrame();
        else{
            if(lookingRight) return animStatic.getCurrentFrame();
            else return animStaticLeft.getCurrentFrame();
        }


    }


}
