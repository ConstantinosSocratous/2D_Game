package tilegame.states;

import java.awt.*;
import java.awt.image.BufferedImage;

import tilegame.Handler;
import tilegame.gfx.ImageLoader;
import tilegame.gfx.SpriteSheet;
import tilegame.tiles.UIObject;

public class MenuState extends State {

	private BufferedImage background;
	private final int width = 32;
	private final int height = 32;
	private UIObject play,exit;

	public MenuState(Handler handler){
		super(handler);
		background = ImageLoader.loadImage("/textures/bg.png");
		init();
	}

	private void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/menuSheet.png"));
		BufferedImage[] playI = new BufferedImage[2];
		playI[0] = sheet.crop(0,0, width*4,height*2);
		playI[1] = sheet.crop(width*4,0, width*4,height*2);
		play = new UIObject(playI ,handler.getWidth()/2-150,handler.getHeight()/2-200);

		BufferedImage[] exitI = new BufferedImage[2];
		exitI[0] = sheet.crop(0,height*2, width,height);
		exitI[1] = sheet.crop(width,height*2, width,height);
		exit = new UIObject(exitI,handler.getGame().getWidth() - width * 3, (int) (height / 2));
	}

	@Override
	public void tick() {
            if (handler.getMouseManager().isLeftPressed() && play.isMouseOver(handler)) {
                sleep(100);
                State.setState(handler.getGame().getLevelState());
            } else if (handler.getMouseManager().isLeftPressed() && exit.isMouseOver(handler)) {
                System.exit(0);
            }
	}

	@Override
	public void render(Graphics g) {
        if(State.getState().equals(handler.getGame().getMenuState())) {
            g.drawImage(background, 0, 0, handler.getWidth(), handler.getHeight(), null);

            //PLAY BUTTON
            BufferedImage temp = play.getCurrentImage(handler);
            g.drawImage(temp, play.getX(), play.getY(), temp.getWidth() * 2, temp.getHeight() * 2, null);

            //EXIT BUTTON
            BufferedImage temp1 = exit.getCurrentImage(handler);
            g.drawImage(temp1, exit.getX(), exit.getY(), temp1.getWidth() * 2, temp1.getHeight() * 2, null);

        }
	}

	public void init(String path){}
}
