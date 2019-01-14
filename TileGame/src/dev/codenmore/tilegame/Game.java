package dev.codenmore.tilegame;

import java.awt.*;
import java.awt.image.BufferStrategy;

import dev.codenmore.tilegame.display.Display;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.gfx.GameCamera;
import dev.codenmore.tilegame.gfx.SoundManager;
import dev.codenmore.tilegame.input.KeyManager;
import dev.codenmore.tilegame.input.MouseManager;
import dev.codenmore.tilegame.states.GameState;
import dev.codenmore.tilegame.states.MenuState;
import dev.codenmore.tilegame.states.State;

public class Game implements Runnable {

	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private State gameState;
	private State menuState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;

	//Sound
	private SoundManager soundManager;

	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;

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
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
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

		g.drawString("FPS: " + lastFPS , getWidth()- 32*5, 32/2);
		//g.drawRect(300,300,300,300);
		//g.drawString("FPS: " + ticks ,0,0);

		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	public void run(){
		
		init();
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		double ticks = 0;

		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			//delta = (now - lastTime);
			timer += now - lastTime;
			lastTime = now;

			if(delta >= 1) {
				delta--;
				tick();
				render();
				ticks++;

			}

			if(timer >= 1000000000 ){//|| ticks == fps){
				lastFPS = ticks;
				//System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		
		stop();
		
	}
	public SoundManager getSoundManager(){return soundManager;}
	public State getGameState(){return gameState;}
	public State getMenuState(){return menuState;}

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

	public void setMenuState(MenuState menu){
		menuState = menu;
	}
	
}











