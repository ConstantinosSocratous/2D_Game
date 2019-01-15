package tilegame.Sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Footstep extends Sound {

    private Clip clip;
    private String path;

    public Footstep(String path){
        try{
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            clip.open(inputStream);
            clip.start();
        }catch(Exception e){
            e.printStackTrace();}

    }

    public void start(){
        try{
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            clip.open(inputStream);

        }catch(Exception e){
            e.printStackTrace();}

    }
    public void stop(){
        clip.stop();
        clip.close();
        clip.setMicrosecondPosition(0);
    }
    public void close(){
        clip.close();
    }

}
