package tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys;
	public boolean up, left, right,down;
	public boolean isUp = false;
	public boolean isShoot = false;
	private int iShoot = 0;
	private int iUp = 0;
	
	public KeyManager(){
		keys = new boolean[256];
	}
	
	public void tick(){
		down = keys[KeyEvent.VK_S];
		up = keys[KeyEvent.VK_W];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
	}

	@Override
	public synchronized void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(iUp < 1){
				isUp = true;
			}
			iUp++;
		}else if(e.getKeyCode() == KeyEvent.VK_L) {
			if(iShoot < 1){
				isShoot = true;
			}
			iShoot++;
		}else{
			isUp = false;
			isShoot = false;
			keys[e.getKeyCode()] = true;
		}
	}

	@Override
	public synchronized void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			iUp= 0;
		}else if(e.getKeyCode() == KeyEvent.VK_L) {
			iShoot= 0;
		}else{
			isUp = false;
			isShoot = false;
		}

		keys[e.getKeyCode()] = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
