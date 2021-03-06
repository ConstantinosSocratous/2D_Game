package tilegame.gfx;

import tilegame.Sounds.Sound;

import java.util.ArrayList;

public class SoundManager {

    public static Sound coin,menu,jungle,intro,die,footstep,checkpoint;
    public static ArrayList<Sound> sounds;
    public static boolean mute = true;

    public static void init(){
        sounds = new ArrayList<>();
        coin = new Sound("Music/coin.wav");
        menu = new Sound("Music/menu.wav");
        jungle = new Sound("Music/jungle.wav");
        intro = new Sound("Music/intro.wav");
        die = new Sound("Music/die.wav");
        footstep = new Sound("Music/footstep.wav");
        checkpoint = new Sound("Music/checkpoint.wav");
    }

    public static void addToSounds(Sound s){
        sounds.add(s);
    }
    public static void stopAll(){
        for(Sound s : sounds){
            if(s.isPlaying())
                s.stop();
        }
    }

    public static void muteAll(){
        SoundManager.stopAll();
        mute = true;
    }
    public static void Unmute(){
        mute = false;
    }

}
