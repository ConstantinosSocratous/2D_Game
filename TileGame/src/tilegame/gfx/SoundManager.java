package tilegame.gfx;

import tilegame.Sounds.Sound;

import java.util.ArrayList;

public class SoundManager {

    public static Sound coin,menu,jungle,intro;
    public static ArrayList<Sound> sounds;
    public static boolean mute = true;

    public static void init(){
        sounds = new ArrayList<>();
        coin = new Sound("Music/coin.wav");
        menu = new Sound("Music/menu.wav");
        jungle = new Sound("Music/jungle.wav");
        intro = new Sound("Music/intro.wav");
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
