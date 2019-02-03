package tilegame.entities.creatures;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.YearMonth;

import tilegame.Handler;
import tilegame.Sounds.Sound;
import tilegame.entities.Bullet;
import tilegame.entities.Entity;
import tilegame.entities.MovingWall;
import tilegame.entities.statics.*;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;
import tilegame.gfx.SoundManager;
import tilegame.states.GameState;
import tilegame.states.LevelsState;
import tilegame.states.State;
import tilegame.tiles.Tile;


public class Player extends Creature {
	
	private final int SHOOT_COOLDOWN = 20;

	private Animation standarL,standar,animRight,animLeft, animUpRight, animUpLeft;
	private boolean winLevel=false, canMove= true;
	private int score = 0;
	private int helperTicksCooldown = 60;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT+16);
		bounds.x = 21;
		bounds.y = 0;
		bounds.width = 64-21-21;
		bounds.height = 64+16-1;

		//Animations
		animLeft = new Animation(250, Assets.player_left);
		animRight = new Animation(250, Assets.player_right);
		animUpRight = new Animation(250, Assets.playerUpRight);
		animUpLeft = new Animation(250, Assets.playerUpLeft);
		standar = new Animation(250,Assets.player_static);
		standarL = new Animation(250,Assets.player_static_left);
	}

	@Override
	public void tick() {
		//Animations
		standar.tick();
		standarL.tick();
		animRight.tick();
		animLeft.tick();
		animUpLeft.tick();
		animUpRight.tick();
		//Movement


		if(canMove)	{
			getInput();

		}
		move();
		checkForDamageWithTiles();

		Entity e = getEntityWithCollision(xMove, 0f);
		if(e != null){
			InteractWithCreatureOnX(e);
		}else if(e == null) {
			Entity e1 = getEntityWithCollision(0f, yMove);
			if (e1 != null) {
				canJump = true;
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
				SoundManager.checkpoint.play();
				((GameState)(handler.getGame().getGameState())).setCurrentCheckPoint((CheckPoint) e3);
				handler.getWorld().getEntityManager().deleteEntity(e3);

			}else if(e3 instanceof  Bullet){
				//System.out.println(x + "  " + e3.getX());
				if( !(((Bullet) e3).getFrom() instanceof  Player)) {
					decreaseHealth(100);
					((Bullet) e3).setCollusion(true);
				}
			}else if(e3 instanceof EnemyGreen || e3 instanceof SmallCreature){
					decreaseHealth(100);
			}else if(e instanceof Coin) {  //INTERACT WITH COIN
				handler.getWorld().getEntityManager().deleteEntity(e);
				score +=1;
				SoundManager.coin.play();
			}
			fall();
		}

		checkCollisionWithMovingWall();

		handler.getGameCamera().centerOnEntity(this);
	}

	public void move(){
		moveX();

		moveY();
	}

	private void checkCollisionWithMovingWall(){

		Rectangle r = new Rectangle((int)x+bounds.x, (int)y+bounds.y+bounds.height,bounds.width, 1);
		Entity e = getEntityWithCollision(0f, 0f);
		if(e instanceof MovingWall && yMove > 0){
			int eX = (int)(e.getX()+e.getRectangle().getBounds().getX());
			int eW = (int)(e.getRectangle().getBounds().getWidth());
			int eY = (int)(e.getY()+e.getRectangle().getBounds().getY());
			int eH = (int)(e.getRectangle().getBounds().getHeight());
			Rectangle rE = new Rectangle(eX,eY,eW,eH);

			if(r.intersects(rE)) {
				canJump = true;
				falling = false;
				y = e.getY()-bounds.height-2;

				if(yMove == 0){
					yMove = ((MovingWall) e).getyMove();
				}
				if(xMove == 0){
					//xMove = ((MovingWall) e).getxMove();
				}
			}
		}
	}

	private void InteractWithCreatureOnY(Entity e){
		if(e instanceof Mushroom) {	 //INTERACT WITH MUSHROOM
		    ((Mushroom) e).deleteMe();
            handler.getWorld().getEntityManager().deleteEntity(e);
        }else{fall();}

	}

	private void InteractWithCreatureOnX(Entity e){
		if(e.isDoingDamage()) { //INTERACT WITH MUSHROOM
			if (e instanceof Mushroom) decreaseHealth(100);
		}else if(e instanceof Door ){
			winLevel = true;
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
		if(handler.getKeyManager().left){
			lookingRight = false;
			xMove = -speed;
		}
		if(handler.getKeyManager().right){
			lookingRight = true;
			xMove = speed;
		}

		//ADD COOLDOWN TO SHOOTING
		helperTicksCooldown++;
		if(handler.getKeyManager().isShoot && helperTicksCooldown >= SHOOT_COOLDOWN){
			if(lookingRight)
				shoot(false,this,12);
			else shoot(true,this,12);

			helperTicksCooldown = 0;
			handler.getKeyManager().isShoot = false;
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
		 else{
		 	if(lookingRight)return standar.getCurrentFrame();
		 	else return standarL.getCurrentFrame();
		}
	}

	public void setCanMove(boolean bool){
		canMove = bool;
	}

	public int getScore(){return score;}
	public void decreaseScore(int n){score-=n;}
    public void increaseScore(int n){score+=n;}
	public boolean hasWon(){return winLevel;}

	public void increasexMove(float x){xMove+=x; }
	public void increaseyMove(float y){yMove+=y; }
	public void clearxMove(){xMove = 0; }
	public void clearyMove(){yMove = 0; }

}
