package tilegame;

import java.awt.*;
import java.awt.image.BufferStrategy;

import tilegame.Cinematic.FirstScene;
import tilegame.display.Display;
import tilegame.gfx.Assets;
import tilegame.gfx.GameCamera;
import tilegame.gfx.SoundManager;
import tilegame.input.KeyManager;
import tilegame.input.MouseManager;
import tilegame.states.*;
import tilegame.worlds.AllLevels;

public class Game implements Runnable {

	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private State gameState,menuState,levelState,firstScene,settingsState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;

	//Sound
	private SoundManager soundManager;

	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;

	//ALL LEVELS
	private AllLevels allLevels;

	//fps
	public double lastFPS;
	//private int ticks;
	//public float DT;
	public double delta;

	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		soundManager = new SoundManager();
	}
	
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);

		Assets.init();
		soundManager.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);


		allLevels = new AllLevels(handler);
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		levelState = new LevelsState(handler);
		firstScene = new FirstScene(handler);
		settingsState = new SettingsState(handler);

		menuState.init("");
		State.setState(menuState);
		//firstScene.init("/cinematic/world.txt");
		//State.setState(firstScene);
	}

	private void tick(){
		keyManager.tick();
		
		if(State.getState() != null)
			State.getState().tick();
	}

	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, display.getFrame().getWidth() , display.getFrame().getHeight());
		//Draw Here!
		
		if(State.getState() != null)
			State.getState().render(g);

		g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman", Font.BOLD, 25));
		g.drawString("FPS: " + lastFPS , 32, 32);

		bs.show();
		g.dispose();
	}
	
	public void run() {

		init();

		long lastTime = System.nanoTime();
		double amountOfTicks = 65.0;
		double ns = 1000000000 / amountOfTicks;
		delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				//updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				lastFPS = frames;
				frames = 0;
				//updates = 0;
			}
		}
		stop();

	}

	public State getGameState(){return gameState;}
	public State getMenuState(){return menuState;}
	public State getLevelState(){return levelState;}
	public State getSettingsState(){return settingsState;}

	public Handler getHandler(){return handler;}
	public Graphics getGraphics(){return g;}

	public MouseManager getMouseManager(){	return mouseManager;}
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public GameCamera getGameCamera(){
		return gameCamera;
	}
	
	public int getWidth(){
		return display.getFrame().getWidth();
	}
	
	public int getHeight(){
		return display.getFrame().getHeight();
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}











