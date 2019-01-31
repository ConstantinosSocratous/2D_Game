package tilegame.Sounds;
import tilegame.gfx.SoundManager;

import javax.sound.sampled.*;
import java.io.InputStream;
import java.net.URL;

public class Sound {

    private Clip clip;
    private String path;
    private InputStream is;
    private AudioInputStream inputStream;
    private URL url;

    public Sound(String path){
        this.path = path;
        SoundManager.addToSounds(this);
        try{
            clip = AudioSystem.getClip();
            url = getClass().getResource(path);
            //is = getClass().getResourceAsStream(path);
            inputStream = AudioSystem.getAudioInputStream(url);
            clip.open(inputStream);


        }catch(Exception e){
            e.printStackTrace();}
    }

    public void play(){
        if(SoundManager.mute)  return;
        try{

            //clip.open(inputStream);
            clip.setFramePosition(0);
            clip.start();

        }catch(Exception e){
            e.printStackTrace();}
    }

    public void loop(){
        if(SoundManager.mute)  return;
        try{

//            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        }catch(Exception e){
            e.printStackTrace();}
    }

    public void stop(){
        if(SoundManager.mute)  return;
        clip.stop();
    }
    public boolean isPlaying(){
        return clip.isRunning();
    }


}
