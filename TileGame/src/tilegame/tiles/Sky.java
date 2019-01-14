package tilegame.tiles;

import tilegame.gfx.Assets;

public class Sky extends Tile {

    public Sky(int id) {
        super(Assets.sky, id);
    }

    @Override
    public boolean isSolid(){
        return false;
    }
}
