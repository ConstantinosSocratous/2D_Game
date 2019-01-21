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

    public FirstScene(Handler handler){
        super(handler);
        this.handler = handler;

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

    }

    public void tick(){
        world.tick();
        kingdom.tick();

        for(Fire f : fires) f.tick();

        if(player.getX() <= 1800) {
            player.setxMove(1.5f);
            player.move();
        }else{
            player.clearxMove();
        }

    }

    public void render(Graphics g){

        world.render(g);
        kingdom.render(g);

        for(Fire f : fires) f.render(g);
    }


}
