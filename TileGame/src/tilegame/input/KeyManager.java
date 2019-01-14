package tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys;
	public boolean up, left, right;
	private boolean isUp = false;
	
	public KeyManager(){
		keys = new boolean[256];
	}
	
	public void tick(){
		up = keys[KeyEvent.VK_UP];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
	}

	@Override
	public synchronized void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		isUp = false;
		//keys[e.getKeyCode()] = true;
		/*if(e.getKeyCode() == KeyEvent.VK_A)
			current = "left";
		if(e.getKeyCode() == KeyEvent.VK_D)
			current = "right";
		if(e.getKeyCode() == KeyEvent.VK_W)
			isUp = true;
			*/
	}

	public void stopJump(){
		isUp = false;
	}

	@Override
	public synchronized void keyReleased(KeyEvent e) {
		//if(e.getKeyCode() == KeyEvent.VK_W) {
		//	isUp = true;
		//}
		//else isUp = false;
		keys[e.getKeyCode()] = false;


	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
