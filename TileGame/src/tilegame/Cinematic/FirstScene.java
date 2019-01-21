package tilegame.Cinematic;

import tilegame.Handler;
import tilegame.Sounds.Sound;
import tilegame.entities.creatures.Player;
import tilegame.gfx.*;
import tilegame.states.State;
import tilegame.worlds.AllLevels;
import tilegame.worlds.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;

public class FirstScene extends State {

    private Handler handler;
    private World world;
    private boolean start = true;
    private Player player;
    private Kingdom kingdom;
    private ArrayList<Fire> fires = new ArrayList<>();

    //For speeches
    private Speech[] speeches = new Speech[1];
    private int currSpeach = 0;
    private boolean canSpeak = false;


    private int numOfTicks = 0;

    public FirstScene(Handler handler){
        super(handler);
        this.handler = handler;



    }
    public void init(){
        init("/cinematic/world.txt");
    }

    public void init(String path){
        SoundManager.stopAll();
        SoundManager.intro.play();
        world = new World(handler,path);
        handler.setWorld(world);
        world.getEntityManager().getPlayer().setCanMove(false);
        this.player = world.getEntityManager().getPlayer();

        kingdom = new Kingdom(handler, 2000, 511,0,0);
        fires.add(new Fire(handler,2200,610,72*2,72*2));
        fires.add(new Fire(handler,2330,630,72*2,72*2));
        fires.add(new Fire(handler,2230,690,72*2,72*2));
        fires.add(new Fire(handler,2330,700,72*2,72*2));
        fires.add(new Fire(handler,2100,620,72*2,72*2));
        fires.add(new Fire(handler,2040,700,72*2,72*2));
        fires.add(new Fire(handler,2500,730,72*2,72*2));
        fires.add(new Fire(handler,2550,650,72*2,72*2));
        fires.add(new Fire(handler,2450,690,72*2,72*2));
        fires.add(new Fire(handler,2400,660,72*2,72*2));


        speeches[0] = new Speech(ImageLoader.loadImage("/cinematic/speeches/1.png"),768,640);

    }

    public void tick(){
        world.tick();
        kingdom.tick();
        numOfTicks++;

        for(Fire f : fires) f.tick();

        if(numOfTicks < 300){//player.getX() <= 500) {
            player.setxMove(1.2f);
            player.move();
        }else if(numOfTicks>=300 && numOfTicks < 550){
            player.clearxMove();
            canSpeak = true;
        }else if(numOfTicks >= 550 && numOfTicks < 620 ){
            canSpeak = false;
        }else if(numOfTicks >= 620 && numOfTicks < 1000){
            player.setxMove(1.2f);
            player.move();
        }else if(numOfTicks >= 1000){
            player.clearxMove();

        }
    }

    public void render(Graphics g){

        world.render(g);
        kingdom.render(g);

        for(Fire f : fires) f.render(g);

        if(canSpeak)
            g.drawImage(speeches[currSpeach].getImage(),speeches[currSpeach].getX(), speeches[currSpeach].getY(),128,128,null);
        //(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset())
    }


}
