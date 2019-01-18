package tilegame.states;

import java.awt.*;
import java.awt.image.BufferedImage;

import tilegame.Handler;
import tilegame.gfx.ImageLoader;
import tilegame.gfx.SpriteSheet;
import tilegame.tiles.UIObject;
import tilegame.worlds.World;

public class GameState extends State {

	private World world;
	private UIObject exit, heart;
	private final int width = 32;
	private final int height = 32;

	private boolean won = false;
	private boolean lost = false;

	private LostState lostObj = new LostState(handler);
	private WonState wonObj = new WonState(handler);


	public GameState(Handler handler,String str) {
		super(handler);
		init(str);
	}

	public void init(String path) {
		createWorld(path);
	}

	public void createWorld(String path) {
		won = false;
		lost = false;
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/menuSheet.png"));
        BufferedImage[] temp1 = new BufferedImage[2];
		temp1[0] = sheet.crop(0, height * 2, width, height);
		temp1[1] = sheet.crop(width, height * 2, width, height);
		exit = new UIObject(temp1, handler.getGame().getWidth() - width * 3, (int) (height / 2));

		SpriteSheet sheet1 = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		BufferedImage[] temp = new BufferedImage[5];
		temp[0] = sheet1.crop(0, height * 3, width, height);
		temp[1] = sheet1.crop(width, height * 3, width, height);
		temp[2] = sheet1.crop(width * 2, height * 3, width, height);
		temp[3] = sheet1.crop(width * 3, height * 3, width, height);
		temp[4] = sheet1.crop(width * 4, height * 3, width, height);

		heart = new UIObject((temp), handler.getGame().getWidth() - width * 5 - 16, (int) (height / 2));

		world = new World(handler, path);
		handler.setWorld(world);

	}

	@Override
	public void tick() {

			world.tick();
			if (handler.getMouseManager().isLeftPressed() && exit.isMouseOver(handler)) {
			/*try {
				Thread.sleep(1500);
			} catch (Exception e) {;}*/
				exitGameState();
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
			world.render(g);

			BufferedImage temp = exit.getCurrentImage(handler);
			g.drawImage(temp, exit.getX(), exit.getY(), temp.getWidth() * 2, temp.getHeight() * 2, null);

			g.drawImage(getCurrentImage(), heart.getX(), heart.getY(), heart.getWidth() * 2, heart.getHeight() * 2, null);

			if(isWon()){
				wonObj.tick();
				wonObj.render(g);
			}else if (isLost()) {
				//System.out.println("LOST");
				//LOST METHOD
				lostObj.tick();
				lostObj.render(g);
				//exitGameState();
			}
		}
	}

	private void exitGameState() {
		sleep(100);
		State.setState(handler.getGame().getMenuState());
		setWorld(null);

		//handler.getGame().getGameState().init("LEVELS EXTENSION");
	}


	//MADE FOR HEART OBJECT
	public BufferedImage getCurrentImage() {
		int h = handler.getWorld().getEntityManager().getPlayer().getHealth();
		int dh = handler.getWorld().getEntityManager().getPlayer().getDefaultHealth();

		if (dh == h) return heart.getImage(0);
		else if (h >= dh * 66 / 100 ) {
			return heart.getImage(1);
		} else if (h >= dh * 33 / 100 ) {
			//System.out.println(h);
			return heart.getImage(2);
		} else if(h > dh *  2/ 100  ) {
			//System.out.println(h);
			return heart.getImage(3);
		}
		return heart.getImage(4);

	}

	public void setLost(boolean bool){	lost = bool;}
	public boolean isLost(){return lost;}

	public void setWon(boolean bool){	won = bool; }
	public boolean isWon(){return won;};

	public void setWorld(World world){
		this.world = world;
	}



}
