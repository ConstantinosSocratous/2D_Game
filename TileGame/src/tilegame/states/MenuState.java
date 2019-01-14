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
		play = new UIObject(sheet.crop(0,0, width*4,height*2),sheet.crop(width*4,0, width*4,height*2) ,handler.getWidth()/2-150,handler.getHeight()/2-200);
		exit = new UIObject(sheet.crop(0,height*2, width,height),sheet.crop(width,height*2, width,height) ,(int) width/2,(int)(height/2));
	}

	@Override
	public void tick() {
		if(handler.getMouseManager().isLeftPressed() && play.isMouseOver(handler)) {
			/*try {
				Thread.sleep(1500);
			} catch (Exception e) {;}*/
			//handler.getGame().getGameState()
			handler.getGame().getGameState().init("LEVELS EXTENSION");
			State.setState(handler.getGame().getGameState());
			//try{Thread.sleep(3000);}catch (InterruptedException e){;}	//CREATE LOADING STATE
			//handler.getGame().setMenuState(null);
		}
		else if(handler.getMouseManager().isLeftPressed() && exit.isMouseOver(handler)){
			System.exit(0);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(background, 0, 0, handler.getWidth(), handler.getHeight(), null);

		//PLAY BUTTON
		if(play.isMouseOver(handler))
			g.drawImage(play.getImageOver(), play.getX(), play.getY(), play.getImageOver().getWidth()*2, play.getImageOver().getHeight()*2, null);
		else g.drawImage(play.getImage(), play.getX(), play.getY(), play.getImage().getWidth()*2, play.getImage().getHeight()*2, null);

		//EXIT BUTTON
		if(exit.isMouseOver(handler))
			g.drawImage(exit.getImageOver(), exit.getX(), exit.getY(), exit.getWidth()*2, exit.getHeight()*2, null);
		else g.drawImage(exit.getImage(), exit.getX(), exit.getY(), exit.getWidth()*2, exit.getHeight()*2, null);
	}

	public void init(String path){}
}
