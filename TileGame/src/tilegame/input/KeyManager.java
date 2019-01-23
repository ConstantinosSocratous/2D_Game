package tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys;
	public boolean up, left, right;
	public boolean isUp = false;
	public boolean isSpace = false;
	private int iSpace = 0;
	private int iUp = 0;
	
	public KeyManager(){
		keys = new boolean[256];
	}
	
	public void tick(){
		//up = keys[KeyEvent.VK_UP];
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
		}else{
			isUp = false;
			keys[e.getKeyCode()] = true;
		}
	}

	@Override
	public synchronized void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			iUp= 0;
		}/*else{
			isUp = false;
			isSpace = false;
		}*/

		keys[e.getKeyCode()] = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
