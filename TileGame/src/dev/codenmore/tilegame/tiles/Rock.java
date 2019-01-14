package dev.codenmore.tilegame.tiles;

import dev.codenmore.tilegame.gfx.Assets;

public class Rock extends Tile {

    public Rock(int id) {
        super(Assets.rock, id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }
}
