package dev.codenmore.tilegame.states;

import java.awt.*;
import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.ImageLoader;
import dev.codenmore.tilegame.gfx.SpriteSheet;
import dev.codenmore.tilegame.tiles.UIObject;
import dev.codenmore.tilegame.worlds.World;

public class GameState extends State {

	private World world;
	private UIObject exit, heart;
	private final int width = 32;
	private final int height = 32;

	private boolean won = false;
	private boolean lost = false;


	public GameState(Handler handler) {
		super(handler);
		init("LEVELS EXTENSION");

	}

	public void init(String path) {
		createWorld(path);
	}

	public void createWorld(String path) {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/menuSheet.png"));
		exit = new UIObject(sheet.crop(0, height * 2, width, height), sheet.crop(width, height * 2, width, height), handler.getGame().getWidth() - width * 3, (int) (height / 2));

		SpriteSheet sheet1 = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		BufferedImage[] temp = new BufferedImage[5];
		temp[0] = sheet1.crop(0, height * 3, width, height);
		temp[1] = sheet1.crop(width, height * 3, width, height);
		temp[2] = sheet1.crop(width * 2, height * 3, width, height);
		temp[3] = sheet1.crop(width * 3, height * 3, width, height);
		temp[4] = sheet1.crop(width * 4, height * 3, width, height);

		heart = new UIObject((temp), handler.getGame().getWidth() - width * 5 - 16, (int) (height / 2));

		world = new World(handler, "res/worlds/world1.txt");
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
		}if (handler.getWorld().getEntityManager().getPlayer().isDead()) {
			setLost(true);

		}

	}

	private void exitGameState() {
			try {
				Thread.sleep(2500);
				handler.getGame().setMenuState(new MenuState(handler));
				State.setState(handler.getGame().getMenuState());
				setWorld(null);
			} catch (InterruptedException e) {
				;
			}

        //handler.getGame().getGameState().init("LEVELS EXTENSION");
	}

	@Override
	public void render(Graphics g) {
		world.render(g);

		if (exit.isMouseOver(handler))
			g.drawImage(exit.getImageOver(), exit.getX(), exit.getY(), exit.getWidth() * 2, exit.getHeight() * 2, null);
		else g.drawImage(exit.getImage(), exit.getX(), exit.getY(), exit.getWidth() * 2, exit.getHeight() * 2, null);
		g.drawImage(getCurrentImage(), heart.getX(), heart.getY(), heart.getWidth() * 2, heart.getHeight() * 2, null);

		if(isLost()){
			//System.out.println("LOST");
			//LOST METHOD
			exitGameState();
		}

	}

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
