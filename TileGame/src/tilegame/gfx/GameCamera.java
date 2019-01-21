package tilegame.gfx;

import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.tiles.Tile;

public class GameCamera {
	
	private Handler handler;
	private float xOffset, yOffset;
	private float previousY = 0 ;
	
	public GameCamera(Handler handler, float xOffset, float yOffset){
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void checkBlankSpace(){
		if(xOffset < 0){
			xOffset = 0;
		}else if(xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()){
			xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
		}
		
		if(yOffset < 0){
			yOffset = 0;
		}else if(yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()){
			yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
		}
	}
	
	public void centerOnEntity(Entity e){
		//if(Math.abs(xOffset-e.getX))
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;

		int camera2 = handler.getHeight()/2;
		int temp2 = (int)(-yOffset+e.getY());

		//if(camera2-temp2 > 32|| temp2-camera2 >32)
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		//if(e.getY() < yDown )
		//if(Math.abs((int)(-e.getY()+previousY)) < 32*4 )//|| Math.abs((int)(e.getY()-previousY)) < 32* )


		//previousY = e.getY();
		//else if(e.getY() >= yDown)
			//yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;

		checkBlankSpace();
	}

	public void centerOnPointX(int xIn){
		//if(Math.abs(xOffset-e.getX))
		xOffset = xIn - handler.getWidth() / 2 + 16;
		//if(camera2-temp2 > 32|| temp2-camera2 >32)
		//yOffset = yIn;
		//if(e.getY() < yDown )
		//if(Math.abs((int)(-e.getY()+previousY)) < 32*4 )//|| Math.abs((int)(e.getY()-previousY)) < 32* )


		//previousY = e.getY();
		//else if(e.getY() >= yDown)
		//yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;

		checkBlankSpace();
	}
	
	public void move(float xAmt, float yAmt){
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}
