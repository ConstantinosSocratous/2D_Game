package tilegame.gfx;

import tilegame.Sounds.Sound;

import java.util.ArrayList;

public class SoundManager {

    public static Sound coin,menu,jungle,intro;
    public static ArrayList<Sound> sounds;

    public static void init(){
        sounds = new ArrayList<>();
        coin = new Sound("/sounds/coin.wav");
        menu = new Sound("/sounds/music/menu.wav");
        jungle = new Sound("/sounds/music/jungle.wav");
        intro = new Sound("/sounds/music/intro.wav");
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

}
