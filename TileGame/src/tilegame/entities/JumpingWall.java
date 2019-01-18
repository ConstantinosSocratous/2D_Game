package tilegame.entities;

import tilegame.Handler;
import tilegame.entities.creatures.Creature;
import tilegame.entities.statics.StaticEntity;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;
import tilegame.tiles.Tile;

import java.awt.*;

public class JumpingWall extends StaticEntity {

    private int distance;
    private Animation anim;
    private int jumpPower = 5;
    private boolean isPressed = false;
    //private float speed = 2;

    public JumpingWall(Handler handler, float x, float y){
        super(handler,x,y, Assets.movingWalls[0].getWidth(), Assets.movingWalls[0].getHeight());
        this.distance = distance;

        bounds.x = 0;
        bounds.y = 14;
        bounds.width = this.width;
        bounds.height = 15;

        anim = new Animation(500, Assets.movingWalls);

    }

    public void tick(){
        if(isPressed)
            anim.tick();
        isPressed = false;
    }

    public int getJumpPower(){return jumpPower;}

    public boolean isPressed(){return isPressed;}

    public void render(Graphics g){
        g.drawImage(anim.getCurrentFrame(),(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()),anim.getCurrentFrame().getWidth(),anim.getCurrentFrame().getHeight(),null);

        //+ handler.getGameCamera().getxOffset()
        //+ handler.getGameCamera().getyOffset()
        //g.setColor(Color.RED);
        //g.fillRect((int)(x ),(int)(y ), bounds.x, bounds.y);
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
