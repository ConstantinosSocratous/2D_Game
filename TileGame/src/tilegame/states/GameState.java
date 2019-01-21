package tilegame.states;

import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.image.BufferedImage;

import sun.util.resources.cldr.ebu.LocaleNames_ebu;
import tilegame.Handler;
import tilegame.Sounds.Sound;
import tilegame.gfx.ImageLoader;
import tilegame.gfx.SoundManager;
import tilegame.gfx.SpriteSheet;
import tilegame.tiles.UIObject;
import tilegame.worlds.AllLevels;
import tilegame.worlds.World;

public class GameState extends State {

	private World world;
	private UIObject menu,repeat;//, heart;
	private final int width = 32;
	private final int height = 32;

	private boolean won = false;
	private boolean lost = false;

	private LostState lostObj = new LostState(handler);
	private WonState wonObj = new WonState(handler);

	private BufferedImage bg1,bg2,bg3,bg4,bg5;

	private int numOfTicks = 0;

	public GameState(Handler handler) {
		super(handler);
	}

	public void init(String path) {
		bg1 = ImageLoader.loadImage("/textures/Background/bg1.png");
		bg2 = ImageLoader.loadImage("/textures/Background/bg2.png");
		bg3 = ImageLoader.loadImage("/textures/Background/bg3.png");
		bg4 = ImageLoader.loadImage("/textures/Background/bg4.png");
		bg5 = ImageLoader.loadImage("/textures/Background/bg5.png");

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
		//heart = new UIObject((temp), handler.getGame().getWidth() - width * 5 - 16, (int) (height / 2));

		world = new World(handler, path);
		handler.setWorld(world);

	}

	@Override
	public void tick() {
			world.tick();
			if (handler.getMouseManager().isLeftPressed()){
				if (menu.isMouseOver(handler))	exitGameState();
				else if (repeat.isMouseOver(handler)){
					//sleep(500);

					AllLevels.goToLevel(currentLevel);
				}
			}
			if (handler.getWorld().getEntityManager().getPlayer().hasWon()) {
				setWon(true);
			}else
			if (handler.getWorld().getEntityManager().getPlayer().isDead()) {
				setLost(true);
			}
	}

	@Override
	public void render(Graphics g) {
		if(State.getState().equals( handler.getGame().getGameState())) {
			g.drawImage(bg2, 0, 0, handler.getWidth(), handler.getHeight(), null);
			world.render(g);

			BufferedImage temp = menu.getCurrentImage(handler);
			//DRAW EXIT BUTTON
			g.drawImage(temp, menu.getX(), menu.getY(), temp.getWidth() * 2, temp.getHeight() * 2, null);


			BufferedImage temp1 = repeat.getCurrentImage(handler);
			g.drawImage(temp1, repeat.getX(), repeat.getY(), temp1.getWidth() * 2, temp1.getHeight() * 2, null);
			//DRAW HEART IMAGE
			//g.drawImage(getCurrentImage(), heart.getX(), heart.getY(), heart.getWidth() * 2, heart.getHeight() * 2, null);

			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			//DRAW SCORE
			g.drawString(handler.getWorld().getEntityManager().getPlayer().getScore() + "", handler.getGame().getWidth()-240,48);
			//DRAW TITLE OF THE LEVEL;
			g.drawString(LevelsState.ALL_LEVELS[currentLevel].getTitle(), handler.getGame().getWidth()/2-30,32);


            if(isWon()){
				wonObj.tick();
				wonObj.render(g);
			}else if (isLost()) {
				//System.out.println("LOST");
				numOfTicks++;
				if(numOfTicks>60) {
					numOfTicks = 0;
					AllLevels.goToLevel(currentLevel);
				}
				//exitGameState();
			}
            
		}
	}

	private void exitGameState() {
		SoundManager.jungle.stop();
		sleep(500);
		//handler.getGame().getMenuState().init("");
        SoundManager.menu.loop();
		State.setState(handler.getGame().getMenuState());
		setWorld(null);

		//handler.getGame().getGameState().init("LEVELS EXTENSION");
	}


	//MADE FOR HEART OBJECT
	/*
	public BufferedImage getCurrentImage() {
		int h = handler.getWorld().getEntityManager().getPlayer().getHealth();
		int dh = handler.getWorld().getEntityManager().getPlayer().getDefaultHealth();

		if (dh == h) return heart.getImage(0);
		else if (h >= dh * 66 / 100 ) {
			return heart.getImage(1);
		} else if (h >= dh * 33 / 100 ) {
			//System.out.println(h);
			return heart.getImage(2);
		} else if(h > dh *  1/ 100  ) {
			//System.out.println(h);
			return heart.getImage(3);
		}
		return heart.getImage(4);

	}*/

	public void setLost(boolean bool){	lost = bool;}
	public boolean isLost(){return lost;}

	public void setWon(boolean bool){	won = bool; }
	public boolean isWon(){return won;};

	public void setWorld(World world){
		this.world = world;
	}



}
