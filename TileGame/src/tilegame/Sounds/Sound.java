package tilegame.Sounds;
import tilegame.gfx.SoundManager;
import tilegame.utils.FileLoader;
import tilegame.utils.Utils;

import javax.rmi.CORBA.Util;
import javax.sound.sampled.*;
import javax.sound.sampled.spi.AudioFileReader;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        try{

            //clip.open(inputStream);
            clip.setFramePosition(0);
            clip.start();

        }catch(Exception e){
            e.printStackTrace();}
    }

    public void loop(){
        try{

//            clip.open(inputStream);
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
