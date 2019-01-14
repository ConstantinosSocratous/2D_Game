package dev.codenmore.tilegame.states;

import java.awt.Graphics;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.worlds.World;


public abstract class State {

	private static State currentState = null;
	private static State previousState = null;

	public static void setState(State state){
		currentState = state;
	}

	public static void setPreviousState(State state){
		previousState = state;
	}

	public static State getState(){
		return currentState;
	}
	public static State getPreviousState(){return previousState;}

	//CLASS
	
	protected Handler handler;
	
	public State(Handler handler){
		this.handler = handler;
	}

	public abstract void init(String path);

	public abstract void tick();
	
	public abstract void render(Graphics g);


	//public abstract void setWorld(World world);


	
}
