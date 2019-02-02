package tilegame.worlds;

import java.awt.*;

import tilegame.Handler;
import tilegame.entities.EntityManager;
import tilegame.entities.creatures.Player;
import tilegame.input.MouseManager;
import tilegame.tiles.Tile;
import tilegame.utils.Utils;

public class MenuWorld {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    private int xMove = 0;
    private int helperTicks = 60;
    //Entities
    private EntityManager entityManager;

    public MenuWorld(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler);


        entityManager.setPlayer(null);

        loadWorld(path);

    }

    public void tick(){
        helperTicks++;
        getInput();
        handler.getGameCamera().setxOffset(handler.getGameCamera().getxOffset()+xMove);
        xMove = 0;
    }

    public void render(Graphics g){
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for(int y = yStart;y < yEnd;y++){
            for(int x = xStart;x < xEnd;x++){
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
                getTile(x, y).tick();
            }
        }
        //Entities
        entityManager.render(g);
    }

    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.grassTile;

        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
            return Tile.white;
        return t;
    }

    public Tile getCurrentTile(int x, int y){
        return null;
    }

    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        //String file = FileLoader.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for(int y = 0;y < height;y++){
            for(int x = 0;x < width;x++){
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public int getSpawnX(){return spawnX;}
    public int getSpawnY(){return spawnY;}

    private void getInput(){
        if(handler.getKeyManager().left){
            xMove = -5;
        }
        if(handler.getKeyManager().right){
            xMove = 5;
        }
        if(handler.getMouseManager().isLeftPressed()){
            removeTile();
        }
    }

    private void removeTile() {
        int x = (int)(handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset())/ 64;
        int y = (int)(handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset())/ 64;

        if(helperTicks >= 20){
            if (getTile((int)x, (int)y).getId() == 1) {
                tiles[x][y] = 6;
                helperTicks = 0;
            }else if (getTile((int)x,(int) y).getId() == 6) {
                tiles[x][y] = 1;
                helperTicks = 0;
            }
        }
    }
}








