package tilegame.tiles;

import tilegame.Handler;
import tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
	
	//STATIC STUFF HERE
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(5);
	public static Tile dirtTile = new DirtTile(0);
	public static Tile sky = new Sky(2);
	public static Tile cloud3 = new Cloud3(3);
	public static Tile cloud4 = new Cloud4(4);
	public static Tile rock = new Rock(1);
	public static Tile lava = new Lava(Assets.lava, 6);

	//CLASS
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;	// 64-64
	
	protected BufferedImage texture;
	protected final int id;


	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
		tiles[id] = this;
	}
	
	public void tick(){
		
	}

	public boolean isDoingDamage(){
		return false;
	}
	
	public void render(Graphics g, int x, int y){
		//+h.getGameCamera().getxOffset()
		//h.getGameCamera().getyOffset(/
		//int x1 = (int)(x + h.getGameCamera().getxOffset())/64;
		//int y1 = (int)(y + h.getGameCamera().getyOffset())/64;
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
		/*g.setColor(Color.RED);
		g.drawString("x:" + x1*64 , x, y);
		g.drawString("y:" + y1*64 , x, y+9);*/
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public int getId(){
		return id;
	}
	
}
