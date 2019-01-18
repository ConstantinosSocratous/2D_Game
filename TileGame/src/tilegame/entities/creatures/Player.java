package tilegame.entities.creatures;

import java.awt.*;
import java.awt.image.BufferedImage;

import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.entities.statics.Door;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;
import tilegame.tiles.Tile;


public class Player extends Creature {
	
	//Animations
	//private Animation animDown, animUp, animLeft, animRight;
	private Animation standar,animRight,animLeft, animUpRight, animUpLeft;
	private boolean winLevel=false;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT+16);
		bounds.x = 21;
		bounds.y = 0;
		bounds.width = 64-21-21;
		bounds.height = 64+16-1;
		/*super(handler, x, y, 80, 80);
		bounds.x = 21+15;
		bounds.y = 0;
		bounds.width = 80-21-21-17;
		bounds.height =80-1;*/

		//bounds = new Rectangle(0, 0, width, height);
		
		//Animatonsa
		animLeft = new Animation(250, Assets.player_left);
		animRight = new Animation(250, Assets.player_right);
		animUpRight = new Animation(250, Assets.playerUpRight);
		animUpLeft = new Animation(250, Assets.playerUpLeft);
		standar = new Animation(250,Assets.player_static) ;
	}

	@Override
	public void tick() {
		//Animations
		standar.tick();
		animRight.tick();
		animLeft.tick();
		animUpLeft.tick();
		animUpRight.tick();
		//Movement


		getInput();
		move();
		checkForDamageWithTiles();

		Entity e = getEntityWithCollision(xMove, 0f);
		if(e != null){
			InteractWithCreatureOnX(e);
		}else {
			Entity e1 = getEntityWithCollision(0f, yMove);
			if (e1 != null) {
				canJump = true;
				InteractWithCreatureOnY(e1);
				//DamageWithCreature(e1);
			}
		}


		handler.getGameCamera().centerOnEntity(this);
	}

	private void InteractWithCreatureOnY(Entity e){
		if(e instanceof Mushroom)  handler.getWorld().getEntityManager().deleteEntity(e);

		//if(e instanceof JumpingWall ){
		//	winLevel = true;
		//}
	}

	private void InteractWithCreatureOnX(Entity e){
		if(e.isDoingDamage()) {
			if (e instanceof Mushroom) decreaseHealth(12);
		}

		if(e instanceof Door ){
			winLevel = true;
		}
	}

	private void checkForDamageWithTiles(){
			if(xMove >= 0){//Moving right
				int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

				if(collisionWithDamageTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) ||
						collisionWithDamageTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                    decreaseHealth();
                    //canJump = true;
                    return;
				}else{

				}

			}if(xMove < 0){//Moving left
				int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

				if(collisionWithDamageTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) ||
						collisionWithDamageTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                    decreaseHealth();
					//canJump = true;
                    return;
				}else{
				}
			}
			if(yMove < 0){//Up
				int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

				if(collisionWithDamageTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) ||
						collisionWithDamageTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
					//canJump = true;
                    decreaseHealth();
                    return;
				}else{
				}
			}
			if(yMove > 0){//Down
				int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
				if(ty > handler.getGame().getHeight())	return;

				if(collisionWithDamageTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) ||
						collisionWithDamageTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
					//falling = false;
					//canJump = true;
					decreaseHealth();
                    return;
				}else {

				}

			}

	}

	private void getInput(){
		if(isDead() || hasWon()){
			xMove = 0;
			yMove = 0;
			return;
		}
		xMove = 0;


		if(handler.getKeyManager().up){
			jump(20f);
			handler.getKeyManager().stopJump();
		}
		if(handler.getKeyManager().left){

			xMove = -speed ;//* (float)handler.getGame().lastFPS/1000000000/(float)handler.getGame().delta;
		}
		if(handler.getKeyManager().right){

			xMove = speed ;//* (float)handler.getGame().lastFPS/1000000000/(float) handler.getGame().delta;
		}
		fall();

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);


	}
	
	private BufferedImage getCurrentAnimationFrame(){

		if(falling && xMove > 0){//Jump Right
			return animUpRight.getCurrentFrame();
		}else if(falling && xMove < 0){//Jump Right
			return animUpLeft.getCurrentFrame();
		}else if(xMove < 0){
			return animLeft.getCurrentFrame();
		}else if(xMove > 0){
			return animRight.getCurrentFrame();
		}
		 else return standar.getCurrentFrame();/*else if(yMove < 0){


			return animUp.getCurrentFrame();
		}else{
			return animDown.getCurrentFrame();
		}*/
	}

	public boolean hasWon(){return winLevel;}
	public void interact(){;}

}
