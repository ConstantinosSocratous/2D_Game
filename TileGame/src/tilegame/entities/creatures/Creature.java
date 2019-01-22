package tilegame.entities.creatures;

import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.gfx.SoundManager;
import tilegame.tiles.Tile;

import java.awt.*;

public abstract class Creature extends Entity {
	
	public static final int DEFAULT_HEALTH = 100;
	public static final float DEFAULT_SPEED = 3.8f;
	public static final int DEFAULT_CREATURE_WIDTH = 64,
							DEFAULT_CREATURE_HEIGHT = 64;
	
	protected int health;
	protected float speed;
	protected float xMove, yMove;

	protected float maxDY = 7f;
	protected float gravity = 0.5f;
	protected boolean falling = true;
	protected boolean canJump = false;


	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move(){
		if(!checkEntityCollisions(xMove, 0f)){
			moveX();
		}
		if(!checkEntityCollisions(xMove, 0f)) {
			moveY();
		}

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
				falling = false;
				canJump = true;
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
			}
			
		}else if(xMove < 0){//Moving left
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
					!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
				canJump = false;
				falling = true;
				x += xMove;
			}else{
				falling = false;
				canJump = true;
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
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
				//System.out.println(x + " " + y + " " + "JUMP---33333");

			}else{
				//y += yMove;
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
				yMove = y;
				falling = true;
				canJump = false;
				//System.out.println(x + " " + y + " " + "JUMP-----222");
			}

		}else if(yMove > 0){//Down
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			if(ty > handler.getGame().getHeight())	return;

			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
					!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
				canJump = false;
				falling = true;
				y += yMove;
				//System.out.println(x + " " + y + " " + "JUMP-111");
			}else {
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
				falling = false;
				//yMove=0;
				canJump = true;
				//System.out.println(x + " " + y + " " + "JUMP");
			}



		}

		fall();
	}

	public float getXMove(){return xMove;}
	public float getYMove(){return yMove;}

	public void fall(){
		if (!falling) {
			canJump = true;
		} else {
			yMove += gravity;
			if (yMove > maxDY) yMove = maxDY;
			canJump = false;
		}
	}

	protected void jump(float jumpHeight){
		if(canJump){
			yMove=-jumpHeight;
			canJump = false;
			falling = true;
		}else{
			//yMove = 0 ;
			falling = true;
		}
	}
	
	protected boolean collisionWithTile(int x, int y){
		return handler.getWorld().getTile(x, y).isSolid();
	}

	protected boolean collisionWithDamageTile(int x, int y){
		return handler.getWorld().getTile(x, y).isDoingDamage();
	}

	//GETTERS SETTERS

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getDefaultHealth() {
		return DEFAULT_HEALTH;
	}

	public int getHealth() {
		return health;
	}

	public void decreaseHealth(){health-=2;}
	public void decreaseHealth(int num){health-=num;}

	public boolean isDead(){
		if(health < 0) return true;
		else return false;
	}

	public void resetHealth() {
		this.health = DEFAULT_HEALTH;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
