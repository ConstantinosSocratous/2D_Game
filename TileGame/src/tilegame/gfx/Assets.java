package tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage dirt, grass, sky, cloud3, cloud4,rock;
	public static BufferedImage[] player_static, player_left, player_right, playerUpRight, playerUpLeft, lava;
	public static BufferedImage[] enemyMoving, door;
	//public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;

	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		
		player_right = new BufferedImage[2];
		//player_jump = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_static = new BufferedImage[1];
		playerUpRight = new BufferedImage[1];
		playerUpLeft = new BufferedImage[1];
		lava = new BufferedImage[2];
		enemyMoving = new BufferedImage[2];
		door = new BufferedImage[2];
		/*player_down[0] = sheet.crop(width * 4, 0, width, height);
		player_down[1] = sheet.crop(width * 5, 0, width, height);
		player_up[0] = sheet.crop(width * 6, 0, width, height);
		player_up[1] = sheet.crop(width * 7, 0, width, height);*/

		enemyMoving[0] = sheet.crop(0, height*4, width*2, height*2);
		enemyMoving[1] = sheet.crop(width *2 , height*4, width*2, height*2);
		player_static[0] = sheet.crop(width * 2, 0, width, height);
		player_right[0] = sheet.crop(width * 3, 0, width, height);
		player_right[1] = sheet.crop(width * 3, height, width, height);
		player_left[0] = sheet.crop(width * 4, 0, width, height);
		player_left[1] = sheet.crop(width * 4, height, width, height);
		playerUpRight[0] = sheet.crop(width * 5, 0, width, height);
		playerUpLeft[0] = sheet.crop(width * 6, 0, width, height);
		lava[0] = sheet.crop(width , height, width, height);
		lava[1] = sheet.crop(width *2 , height, width, height);

		door[0] = sheet.crop(0 , height*6, width, height*2);
		door[1] = sheet.crop(width , height*6, width, height*2);
		/*zombie_down = new BufferedImage[2];
		zombie_up = new BufferedImage[2];
		zombie_left = new BufferedImage[2];
		zombie_right = new BufferedImage[2];
		
		zombie_down[0] = sheet.crop(width * 4, height * 2, width, height);
		zombie_down[1] = sheet.crop(width * 5, height * 2, width, height);
		zombie_up[0] = sheet.crop(width * 6, height * 2, width, height);
		zombie_up[1] = sheet.crop(width * 7, height * 2, width, height);
		zombie_right[0] = sheet.crop(width * 4, height * 3, width, height);
		zombie_right[1] = sheet.crop(width * 5, height * 3, width, height);
		zombie_left[0] = sheet.crop(width * 6, height * 3, width, height);
		zombie_left[1] = sheet.crop(width * 7, height * 3, width, height);
		*/
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(0, 0, width, height);
		rock = sheet.crop(0,height, width,height);
		sky = sheet.crop(0, height * 2, width, height);
		cloud3 = sheet.crop(width, height * 2, width, height);
		cloud4 = sheet.crop(width * 2  , height * 2, width, height);

	}
	
}
