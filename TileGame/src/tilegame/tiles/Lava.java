package tilegame.tiles;

import tilegame.gfx.Animation;
import tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lava extends Tile {

    private BufferedImage[] imgs;
    private int id;
    private Animation animLava;


    public Lava(BufferedImage[] texture, int id) {
        super(null,id);
        imgs = texture;
        this.id = id;
        animLava = new Animation(500, Assets.lava);
    }

    public void tick(){
        animLava.tick();
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(animLava.getCurrentFrame(), x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

    @Override
    public boolean isDoingDamage(){
        return true;
    }
}
