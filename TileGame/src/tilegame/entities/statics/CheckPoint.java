package tilegame.entities.statics;
import tilegame.Handler;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CheckPoint extends StaticEntity{

    public static ArrayList<CheckPoint> AllCheckPoints = new ArrayList<>();
    private int priority;
    private BufferedImage img;

    public CheckPoint(Handler handler, float x, float y, int width, int height, int priority){
        super(handler, x, y, width, height);
        this.priority = priority;
        CheckPoint.AllCheckPoints.add(this);
        img = Assets.checkpoint;

        bounds.x = (width/32)*7;
        bounds.width = (width/32)*17;
        bounds.y = (width/32)*23;
        bounds.height= height - bounds.y;
    }

    public void tick(){

    }

    public void render(Graphics g){
       g.drawImage(img,(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),width,height,null);
    }





}
