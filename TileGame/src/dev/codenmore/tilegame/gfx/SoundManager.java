package dev.codenmore.tilegame.gfx;

import dev.codenmore.tilegame.Sounds.Footstep;
import dev.codenmore.tilegame.Sounds.Sound;

public class SoundManager {

    public static Sound footstep;

    public static void init(){
        footstep = new Footstep("res/sounds/footstep.wav");
    }
}
