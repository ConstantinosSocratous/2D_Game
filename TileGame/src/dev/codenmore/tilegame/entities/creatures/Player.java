package dev.codenmore.tilegame.entities.creatures;

import java.awt.*;
import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Animation;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.tiles.Tile;
import dev.codenmore.tilegame.worlds.World;

import javax.annotation.processing.SupportedSourceVersion;

public class Player extends Creature {
	
	//Animations
	//private Animation animDown, animUp, animLeft, animRight;
	private Animation standar,animRight,animLeft, animUpRight, animUpLeft;
	private boolean isDead = false;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 21;
		bounds.y = 0;
		bounds.width = 64-21-21;
		bounds.height = 64-1;

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
		checkForDamage();
		//handler.getGame().getSoundManager().footstep.start();
		handler.getGameCamera().centerOnEntity(this);

		//if(isDead()) handler.getGame().getGameState()..setLost(true);
	}

	private void checkForDamage(){


			if(xMove >= 0){//Moving right
				int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

				if(collisionWithDamageTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) ||
						collisionWithDamageTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                    decreaseHealth();
                    return;
				}else{

				}

			}if(xMove < 0){//Moving left
				int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

				if(collisionWithDamageTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) ||
						collisionWithDamageTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                    decreaseHealth();
                    return;
				}else{

				}

			}
			if(yMove < 0){//Up
				int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

				if(collisionWithDamageTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) ||
						collisionWithDamageTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){
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
					decreaseHealth();
                    return;
				}else {

				}

			}

	}

	private void getInput(){
		xMove = 0;
		//yMove = 0;

		if(handler.getKeyManager().up){
			jump(17f);
			handler.getKeyManager().stopJump();
		}
		if(handler.getKeyManager().left)
			xMove = -speed  * (float)handler.getGame().delta;
		if(handler.getKeyManager().right)
			xMove = speed * (float) handler.getGame().delta;
		fall();

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

//		g.setColor(Color.red);
		//g.fillRect( (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), bounds.width,bounds.height);
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//				bounds.width, bounds.height);
	}
	
	private BufferedImage getCurrentAnimationFrame(){

		if(yMove < 0 && xMove > 0){//Jump Right
			return animUpRight.getCurrentFrame();
		}else if(yMove < 0 && xMove < 0){//Jump Right
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

}
