package tilegame.states;

import java.awt.*;
import java.awt.image.BufferedImage;

import tilegame.Handler;
import tilegame.entities.creatures.Creature;
import tilegame.entities.statics.CheckPoint;
import tilegame.gfx.ImageLoader;
import tilegame.gfx.SoundManager;
import tilegame.gfx.SpriteSheet;
import tilegame.tiles.Heart;
import tilegame.UI.LevelObject;
import tilegame.UI.UIObject;
import tilegame.worlds.AllLevels;
import tilegame.worlds.World;

public class GameState extends State {

	private World world;
	private UIObject menu,repeat;//, heart;
	private final int width = 32;
	private final int height = 32;

	private boolean won = false;
	private boolean lost = false;
	private int currentLevel = -1;

	private LostState lostObj = new LostState(handler);
	private WonState wonObj = new WonState(handler);

	private BufferedImage bg;

	private int numOfTicks = 0;
	private float xSpawn = 0,ySpawn = 0;
	private CheckPoint currentCheckPoint = null;

	private Heart heart;


	public GameState(Handler handler) {
		super(handler);
	}

	public void init(String path) {

		bg = ImageLoader.loadImage("/textures/Background/bg2.png");
		createWorld(path);
	}

	public void createWorld(String path) {

		numOfTicks = 0;
		won = false;
		lost = false;
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/menuSheet.png"));
        BufferedImage[] temp1 = new BufferedImage[2];
		temp1[0] = sheet.crop(0, 0, width, height);
		temp1[1] = sheet.crop(width, 0 , width, height);
		menu = new UIObject(temp1, handler.getGame().getWidth() - width * 3, (int) (height / 2));


		BufferedImage[] temp3 = new BufferedImage[2];
		temp3[0] = sheet.crop(0, height, width, height);
		temp3[1] = sheet.crop(width, height, width, height);
		repeat = new UIObject(temp3, handler.getGame().getWidth() - width * 6, (int) (height / 2));

		heart = new Heart(handler,handler.getGame().getWidth() - width * 9, (int) (height / 2));

		world = new World(handler, path);

		handler.setWorld(world);

	}

	public CheckPoint getCurrentCheckPoint(){return currentCheckPoint;}
	public void setCurrentCheckPoint(CheckPoint c){
		currentCheckPoint = c;
	}

	public void respawn(){
		heart.setCurrentNum(heart.getCurrentNum()-1);
		if(heart.getCurrentNum()==0){
			initSpawns();
			AllLevels.goToLevel(currentLevel);
		}else {
			won = false;
			lost = false;
			world.getEntityManager().getPlayer().setHealth(Creature.DEFAULT_HEALTH);

			if (currentCheckPoint == null) {
				handler.getWorld().getEntityManager().getPlayer().setX(handler.getWorld().getSpawnX());
				handler.getWorld().getEntityManager().getPlayer().setY(handler.getWorld().getSpawnY());
				handler.getWorld().getEntityManager().getPlayer().setyMove(0.01f);
			} else {
				handler.getWorld().getEntityManager().getPlayer().setX(currentCheckPoint.getX());
				handler.getWorld().getEntityManager().getPlayer().setY(currentCheckPoint.getY());
				handler.getWorld().getEntityManager().getPlayer().setyMove(0.01f);
			}

			handler.getWorld().getEntityManager().respawnDeleted();

			//AllLevels.respawn(currentLevel);
		}
	}

	@Override
	public void tick() {
			world.tick();
			if (handler.getMouseManager().isLeftPressed()){
				if (menu.isMouseOver(handler))	exitGameState();
				else if (repeat.isMouseOver(handler)){
					//sleep(500);
					initSpawns();
					AllLevels.goToLevel(currentLevel);
				}
			}
			if (handler.getWorld().getEntityManager().getPlayer().hasWon()) {
				setWon(true);
			}else
			if (handler.getWorld().getEntityManager().getPlayer().isDead()) {
				setLost(true);
			}
			handler.getWorld().getEntityManager().deleteCollusionBullets();
	}

	@Override
	public void render(Graphics g) {
		if(State.getState().equals( handler.getGame().getGameState())) {
			g.drawImage(bg, 0, 0, handler.getWidth(), handler.getHeight(), null);
			AllLevels.createBackground(currentLevel);

			world.render(g);

			//DRAW EXIT BUTTON
			BufferedImage temp = menu.getCurrentImage(handler);
			g.drawImage(temp, menu.getX(), menu.getY(), temp.getWidth() * 2, temp.getHeight() * 2, null);

			//DRAW REPEAT BUTTON
			BufferedImage temp1 = repeat.getCurrentImage(handler);
			g.drawImage(temp1, repeat.getX(), repeat.getY(), temp1.getWidth() * 2, temp1.getHeight() * 2, null);

			//HEART
			heart.tick(g);
			heart.render(g);

			g.setFont(new Font("TimesRoman", Font.BOLD, 25));
			//DRAW SCORE
			g.drawString(handler.getWorld().getEntityManager().getPlayer().getScore() + "", handler.getGame().getWidth()-width*15,height + (int)height/2);

			g.setFont(new Font("TimesRoman", Font.PLAIN, 15));

            if(isWon()){
				//Unlock next level
				if(numOfTicks == 0){

				}
				if(getCurrentLevel()+1 < LevelObject.ALL_LEVEL_OBJ.size()){
					LevelObject.ALL_LEVEL_OBJ.get(getCurrentLevel()+1).setIsLocked(false);
				}
				wonObj.tick();
				wonObj.render(g);
				numOfTicks++;

				if(numOfTicks>125) {
					numOfTicks = 0;

					AllLevels.goToLevel(getCurrentLevel()+1);
				}
			}else if (isLost()) {
				if(numOfTicks ==0)SoundManager.die.play();

				numOfTicks++;

				if(numOfTicks>80) {
					numOfTicks = 0;
					respawn();
				}
			}
            
		}
	}
	public void initSpawns(){
		xSpawn = 0;
		ySpawn = 0;
	}
	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public void exitGameState() {
		SoundManager.jungle.stop();
		sleep(500);
		//handler.getGame().getMenuState().init("");
        SoundManager.menu.loop();
		State.setState(handler.getGame().getMenuState());
		setWorld(null);
	}

	public void setLost(boolean bool){	lost = bool;}
	public boolean isLost(){return lost;}

	public void setWon(boolean bool){	won = bool; }
	public boolean isWon(){return won;};

	public void setWorld(World world){
		this.world = world;
	}

}
