package tilegame.Sounds;
import tilegame.gfx.SoundManager;
import tilegame.utils.Utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.InputStream;

public class Sound {

    private Clip clip;
    private String path;
    private boolean loop = false;

    public Sound(String path){
        this.path = path;
        SoundManager.addToSounds(this);
        try{
            clip = AudioSystem.getClip();
            InputStream is = Sound.class.getResourceAsStream(path);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(is);
            clip.open(inputStream);
        }catch(Exception e){
            e.printStackTrace();}
    }
    public void play(){
        try{
            clip = AudioSystem.getClip();
            InputStream is = Sound.class.getResourceAsStream(path);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(is);
            clip.open(inputStream);
            clip.start();
        }catch(Exception e){
            e.printStackTrace();}

    }

    public void loop(){
        try{
            clip = AudioSystem.getClip();
            InputStream is = Sound.class.getResourceAsStream(path);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(is);
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }catch(Exception e){
            e.printStackTrace();}
    }

    public void stop(){
        clip.stop();
    }
    public boolean isPlaying(){
        return clip.isRunning();
    }


}
