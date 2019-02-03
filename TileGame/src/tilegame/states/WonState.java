package tilegame.states;

import tilegame.Handler;
import tilegame.gfx.ImageLoader;
import tilegame.gfx.SpriteSheet;
import tilegame.UI.LevelObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WonState {
    private BufferedImage[] won;
    private Handler handler;
    private int width = 32, height = 32;

    public WonState(Handler handler){
        this.handler = handler;
        init();
    }

    public void init(){
        won = new BufferedImage[4];
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/won/won.png"));
        won[0] = sheet.crop(0, 0, width*5, height*3);
        won[1] = sheet.crop(width *5, 0, width*5, height*3);
        won[2] = sheet.crop(0, height*3, width*5, height*3);
        won[3] = sheet.crop(width *5, height*3, width*5, height*3);

    }

    public void tick(){

    }

    public void render(Graphics g){
        BufferedImage img = getCurrentImage();
        g.drawImage(img,handler.getWidth()/2- 200,handler.getHeight()/2-200, img.getWidth()*3, img.getHeight()*3,null);


        int scr = handler.getWorld().getEntityManager().getPlayer().getScore();
        //g.drawString(scr+ "",handler.getWidth()/2,handler.getHeight()/2+100);

    }

    private BufferedImage getCurrentImage(){
        int curLevel = ((GameState)handler.getGame().getGameState()).getCurrentLevel();
        int maxScore = LevelObject.ALL_LEVEL_OBJ.get(curLevel).getMaxScore();
        int scr = handler.getWorld().getEntityManager().getPlayer().getScore();

        if(scr <= maxScore*20/100)return won[0];
        else if(scr > maxScore*20/100 && scr <= maxScore*50/100) return won[1];
        else if(scr > maxScore*50/100 && scr <= maxScore*80/100) return won[2];
        else return won[3];

    }
}
