package tilegame.states;

import java.awt.*;
import java.awt.image.BufferedImage;

import tilegame.Handler;
import tilegame.gfx.ImageLoader;
import tilegame.gfx.SoundManager;
import tilegame.gfx.SpriteSheet;
import tilegame.UI.UIObject;
import tilegame.worlds.AllLevels;
import tilegame.worlds.MenuWorld;
import tilegame.worlds.World;

public class MenuState extends State {

	private BufferedImage background;
	private final int width = 32;
	private final int height = 32;
	private UIObject play,exit,instr,settings;
	private Background bg;
	private MenuWorld menuWorld;

	public MenuState(Handler handler){
		super(handler);
		background = ImageLoader.loadImage("/textures/Background/bg.png");

		//init();
	}

	private void init(){
		bg = new Background(handler);
		SoundManager.menu.stop();
		SoundManager.menu.loop();
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/menuSheet.png"));

		int w = (int) handler.getWidth()/12;
		int h = (int) handler.getHeight()/10;

		BufferedImage[] exitI = new BufferedImage[2];
		exitI[0] = sheet.crop(0,height*2, width,height);
		exitI[1] = sheet.crop(width,height*2, width,height);
		exit = new UIObject(exitI,handler.getGame().getWidth() - width * 3, (int) (height / 2));

		BufferedImage[] sett = new BufferedImage[2];
		sett[0] = sheet.crop(width*2,height*5, width*3,height*3);
		sett[1] = sheet.crop(width*5,height*5, width*3,height*3);
		settings = new UIObject(sett,(int)(w*11.2), (int)(h*8));

		BufferedImage tst = ImageLoader.loadImage("/textures/menuObjects/play.png");
		BufferedImage[] playI = new BufferedImage[2];
		playI[0] = tst;
		playI[1] = tst;
		play = new UIObject(playI ,(int)(w*0.7),(int)(h*2.5),96,288);

		BufferedImage tst1 = ImageLoader.loadImage("/textures/menuObjects/instructions.png");
		BufferedImage[] instructions = new BufferedImage[2];
		instructions[0] = tst1;
		instructions[1] = tst1;
		instr = new UIObject(instructions ,(int)(w*0.7),(int)(h*4.5),96,288);


		menuWorld = new MenuWorld(handler,"/worlds/MENU.txt");
	}

	@Override
	public void tick() {

		if(handler.getMouseManager().isLeftPressed()){
			if (play.isMouseOver(handler)) {
				//SoundManager.menu.stop();
				State.setState(handler.getGame().getLevelState());
				sleep(500);
				//AllLevels.goToLevel(0);
			} else if ( exit.isMouseOver(handler)) {
				System.exit(0);
			} else if( instr.isMouseOver(handler)){
				handler.getGame().newGame();
				//handler.getGame().newLevelState();
				//State.setState(handler.getGame().getLevelState());
				//sleep(500);
			}else if( settings.isMouseOver(handler)){
				State.setState(handler.getGame().getSettingsState());
				sleep(500);
			}
		}
		if(State.getState().equals(handler.getGame().getMenuState())) {
			menuWorld.tick();
		}

	}

	@Override
	public void render(Graphics g) {
        if(State.getState().equals(handler.getGame().getMenuState())) {
			bg.render(g);
			menuWorld.render(g);
            //PLAY BUTTON
            BufferedImage temp = play.getCurrentImage(handler);
            g.drawImage(temp, play.getX(), play.getY(), play.getWidth() * 2, play.getHeight() * 2, null);

			//INSTRUCTIONS BUTTON
			BufferedImage tempIn = instr.getCurrentImage(handler);
			g.drawImage(tempIn, instr.getX(), instr.getY(), instr.getWidth() * 2, instr.getHeight() * 2, null);

			//EXIT BUTTON
            BufferedImage temp1 = exit.getCurrentImage(handler);
            g.drawImage(temp1, exit.getX(), exit.getY(), exit.getWidth() * 2, exit.getHeight() * 2, null);

			//SETTINGS BUTTON
			BufferedImage temp3 = settings.getCurrentImage(handler);
			g.drawImage(temp3, settings.getX(), settings.getY(), settings.getWidth(), settings.getHeight(), null);

        }
	}

	public void init(String path){this.init();}
}
