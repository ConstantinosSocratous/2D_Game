package tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage dirt, grass, sky, cloud3, cloud4,rock, white,checkpoint,bullet,heart;
	public static BufferedImage[] player_static, player_left, player_right, playerUpRight, playerUpLeft, lava;
	public static BufferedImage[] enemyMoving, door, movingWalls, coin, trap, kingdom,fire,playerEnemyStatic;
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
		movingWalls = new BufferedImage[1];
		coin = new BufferedImage[6];
		trap = new BufferedImage[2];
		kingdom = new BufferedImage[4];
		playerEnemyStatic = new BufferedImage[1];
		//////////////


		playerEnemyStatic[0] = sheet.crop(width*7, height*5, width, height);
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
		movingWalls[0] = sheet.crop(width*3 , height*2, width*5, height);
		trap[0] = sheet.crop(width*4 , height*4, width, height);
		trap[1] = sheet.crop(width*5 , height*4, width, height);

		SpriteSheet sheetCoin = new SpriteSheet(ImageLoader.loadImage("/textures/coin.png"));
		coin[0] = sheetCoin.crop(0, 0, width, height);
		coin[1] = sheetCoin.crop(height, 0, width, height);
		coin[2] = sheetCoin.crop(height*2, 0, width, height);
		coin[3] = sheetCoin.crop(height*3, 0, width, height);
		coin[4] = sheetCoin.crop(height*4, 0, width, height);
		coin[5] = sheetCoin.crop(height*5, 0, width, height);

		kingdom[0] = ImageLoader.loadImage("/cinematic/kingdom/kingdom1.png");
		kingdom[1] = ImageLoader.loadImage("/cinematic/kingdom/kingdom2.png");
		kingdom[2] = ImageLoader.loadImage("/cinematic/kingdom/kingdom3.png");
		kingdom[3] = ImageLoader.loadImage("/cinematic/kingdom/kingdom2.png");

		bullet = sheet.crop(0,height*3,width,height);
		checkpoint = sheet.crop(width*2,height*6, width,height*2);
		white = sheet.crop(width*7, 0, width, height);
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(0, 0, width, height);
		rock = sheet.crop(0,height, width,height);
		sky = sheet.crop(0, height * 2, width, height);
		cloud3 = sheet.crop(width, height * 2, width, height);
		cloud4 = sheet.crop(width * 2  , height * 2, width, height);
		heart = sheet.crop(width,height*3,width,height);


		fire = new BufferedImage[7];
		SpriteSheet fireSheet = new SpriteSheet(ImageLoader.loadImage("/cinematic/fire.png"));
		for(int i=0; i< fire.length; i++){
			fire[i] = fireSheet.crop(24*i, 0, 24, 24);
 		}

	}
	
}
