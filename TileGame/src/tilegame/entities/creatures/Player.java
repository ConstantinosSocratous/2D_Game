package tilegame.entities.creatures;

import java.awt.*;
import java.awt.image.BufferedImage;

import tilegame.Handler;
import tilegame.Sounds.Sound;
import tilegame.entities.Bullet;
import tilegame.entities.Entity;
import tilegame.entities.statics.CheckPoint;
import tilegame.entities.statics.Coin;
import tilegame.entities.statics.Door;
import tilegame.entities.statics.Trap;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;
import tilegame.gfx.SoundManager;
import tilegame.states.GameState;
import tilegame.states.LevelsState;
import tilegame.states.State;
import tilegame.tiles.Tile;


public class Player extends Creature {
	
	//Animations
	//private Animation animDown, animUp, animLeft, animRight;
	private Animation standar,animRight,animLeft, animUpRight, animUpLeft;
	private boolean winLevel=false, canMove= true;
	private int score = 0;

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

		//Animations
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

		if(canMove)getInput();
		move();
		checkForDamageWithTiles();

		Entity e = getEntityWithCollision(xMove, 0f);
		if(e != null){
			InteractWithCreatureOnX(e);
		}else if(e == null) {
			Entity e1 = getEntityWithCollision(0f, yMove);
			if (e1 != null) {
				canJump = true;
				InteractWithCreatureOnY(e1);
				//DamageWithCreature(e1);
			}
		}

		Entity e3 = getEntityWithCollision(0f, 0f);
		if (e3 != null) {
			if (e3 instanceof Trap) {
				if (e3.isDoingDamage()) {
					decreaseHealth(100);
				}
			}else if(e3 instanceof CheckPoint){
				((GameState)(handler.getGame().getGameState())).setCurrentCheckPoint((CheckPoint) e3);

				handler.getWorld().getEntityManager().deleteEntity(e3);
			}

			fall();
		}

		handler.getGameCamera().centerOnEntity(this);
	}

	public void move(){
		//if(!checkEntityCollisions(xMove, 0f)){
			moveX();
		//}
		//if(!checkEntityCollisions(xMove, 0f)) {
			moveY();
		//}

	}
	private void InteractWithCreatureOnY(Entity e){
		if(e instanceof Mushroom) {	 //INTERACT WITH MUSHROOM
		    if(!handler.getWorld().getTile((int)(e.getX()+75)/64,(int)e.getY()/64).isSolid())
                handler.getWorld().getEntityManager().addEntity(new Coin(handler,(int)(e.getX()+75),e.getY(),0,0));
            else if(!handler.getWorld().getTile((int)(e.getX()-75)/64,(int)e.getY()/64).isSolid())
                handler.getWorld().getEntityManager().addEntity(new Coin(handler,(int)(e.getX()-75),e.getY(),0,0));
            else
                handler.getWorld().getEntityManager().addEntity(new Coin(handler,(int)(e.getX()),e.getY()-120,0,0));
            handler.getWorld().getEntityManager().deleteEntity(e);
            //if(!isEligibleToJump()) fall();
        }
        else if(e instanceof Coin) {  //INTERACT WITH COIN
			handler.getWorld().getEntityManager().deleteEntity(e);
			score +=100;
			SoundManager.coin.play();
			//if(!isEligibleToJump()) fall();
		}else{fall();}

	}

	private void InteractWithCreatureOnX(Entity e){
		if(e.isDoingDamage()) { //INTERACT WITH MUSHROOM
			if (e instanceof Mushroom) decreaseHealth(100);
		}else if(e instanceof Door ){
			winLevel = true;
		}else if(e instanceof Coin) {	//INTERACT WITH COIN
			SoundManager.coin.play();
			handler.getWorld().getEntityManager().deleteEntity(e);
			score +=100;
		}
	}

	private boolean isEligibleToJump(){
		int currentLevelTemp = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
		return LevelsState.ALL_LEVELS[currentLevelTemp].isEligableToJump();
	}

	private void checkForDamageWithTiles(){
			if(xMove >= 0){//Moving right
				int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

				if(collisionWithDamageTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) ||
						collisionWithDamageTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                    decreaseHealth(100);
                    //canJump = true;
                    return;
				}else{

				}

			}if(xMove < 0){//Moving left
				int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

				if(collisionWithDamageTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) ||
						collisionWithDamageTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                    decreaseHealth(100);
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
                    decreaseHealth(100);
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
					decreaseHealth(100);
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
		if(handler.getKeyManager().isUp){
			jump(20);
			handler.getKeyManager().isUp = false;
		}
		if(handler.getKeyManager().left)xMove = -speed;
		if(handler.getKeyManager().right)xMove = speed;

		if(handler.getMouseManager().isEligableToShoot()){
			int mouseX = handler.getMouseManager().getMouseX() - (int)handler.getGameCamera().getyOffset();

			if(mouseX > x - handler.getGameCamera().getxOffset())
				handler.getWorld().getEntityManager().addEntity(new Bullet(handler,getX()+35,getY()+33,32,32,false));
			else
				handler.getWorld().getEntityManager().addEntity(new Bullet(handler,getX()+35,getY()+33,32,32,true));

			handler.getMouseManager().setCanShoot(false);
			handler.getKeyManager().isSpace = false;
		}
		fall();

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	private int getTempX(){
		return  (int)xMove+(int)x- (int)handler.getGameCamera().getxOffset();
	}
	private int getTempY(){
		return  (int)yMove+(int)y- (int)handler.getGameCamera().getyOffset();
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
		 else return standar.getCurrentFrame();
	}

	public void setCanMove(boolean bool){
		canMove = bool;
	}

	public int getScore(){return score;}
	public boolean hasWon(){return winLevel;}

	public void increasexMove(float x){xMove+=x; }
	public void increaseyMove(float y){yMove+=y; }
	public void clearxMove(){xMove = 0; }
	public void clearyMove(){yMove = 0; }

}
