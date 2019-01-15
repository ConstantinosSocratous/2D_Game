package tilegame.states;

import java.awt.Graphics;

import tilegame.Handler;
import tilegame.worlds.World;


public abstract class State {

	private static State currentState = null;

	public static void setState(State state){
		currentState = state;
	}

	public static State getState(){
		return currentState;
	}

	//CLASS
	
	protected Handler handler;
	
	public State(Handler handler){
		this.handler = handler;
	}

	public abstract void init(String path);

	public abstract void tick();
	
	public abstract void render(Graphics g);

	protected void sleep(int n){
		try {
			Thread.sleep(n);
		} catch (InterruptedException e) {
			;
		}
	}


	//public abstract void setWorld(World world);


	
}
