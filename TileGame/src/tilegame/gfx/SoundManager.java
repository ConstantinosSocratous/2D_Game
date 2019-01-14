package tilegame.gfx;

import tilegame.Sounds.Footstep;
import tilegame.Sounds.Sound;

public class SoundManager {

    public static Sound footstep;

    public static void init(){
        footstep = new Footstep("res/sounds/footstep.wav");
    }
}
