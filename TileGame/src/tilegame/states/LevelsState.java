package tilegame.states;

import tilegame.Handler;
import tilegame.gfx.ImageLoader;
import tilegame.gfx.SpriteSheet;
import tilegame.tiles.LevelObject;
import tilegame.tiles.UIObject;
import tilegame.worlds.AllLevels;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelsState extends State{

    private BufferedImage background;
    private final int width = 32;
    private final int height = 32;
    public static LevelObject[] ALL_LEVELS = new LevelObject[2];
    private UIObject exit;

    public LevelsState(Handler handler){
        super(handler);
        background = ImageLoader.loadImage("/textures/bg.png");
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/levels.png"));

        BufferedImage[] temp = new BufferedImage[2];
        temp[0] = sheet.crop(0,0, width,height);
        temp[1] = sheet.crop(width,0, width,height);
        ALL_LEVELS[0] = new LevelObject(temp, handler.getWidth()/2 -200 , handler.getHeight()/2-200 , "/worlds/world0.txt");

        BufferedImage[] temp2 = new BufferedImage[2];
        temp2[0] = sheet.crop(width*2,0, width,height);
        temp2[1] = sheet.crop(width*3,0, width,height);
        ALL_LEVELS[1] = new LevelObject(temp2, handler.getWidth()/2  , handler.getHeight()/2-200 , "/worlds/world1.txt");

        SpriteSheet sheet1 = new SpriteSheet(ImageLoader.loadImage("/textures/menuSheet.png"));
        BufferedImage[] exitI = new BufferedImage[2];
        exitI[0] = sheet1.crop(0,height*2, width,height);
        exitI[1] = sheet1.crop(width,height*2, width,height);
        exit = new UIObject(exitI,handler.getGame().getWidth() - width * 3, (int) (height / 2));
    }

    public void init(String path){

    }

    public void tick(){

            if(handler.getMouseManager().isLeftPressed()){
                if(exit.isMouseOver(handler)){
                    sleep(100);
                    State.setState(handler.getGame().getMenuState());
                }
                if(ALL_LEVELS[0].isMouseOver(handler)){    //LEVEL 1
                    sleep(100);
                    AllLevels.goToLevel(0);
                    handler.getGame().getGameState().setCurrentLevel(0);
                }
                if(ALL_LEVELS[1].isMouseOver(handler)){    //LEVEL 2
                    sleep(100);
                    AllLevels.goToLevel(1);
                    handler.getGame().getGameState().setCurrentLevel(1);
                }
            }

    }

    public void render(Graphics g){
        if(State.getState().equals(handler.getGame().getLevelState())) {
            g.drawImage(background, 0, 0, handler.getWidth(), handler.getHeight(), null);

            BufferedImage temp = ALL_LEVELS[0].getCurrentImage(handler);
            g.drawImage(temp, ALL_LEVELS[0].getX(), ALL_LEVELS[0].getY(), temp.getWidth() * 3, temp.getHeight() * 3, null);

            BufferedImage temp2 = ALL_LEVELS[1].getCurrentImage(handler);
            g.drawImage(temp2, ALL_LEVELS[1].getX(), ALL_LEVELS[1].getY(), temp2.getWidth() * 3, temp2.getHeight() * 3, null);

            BufferedImage temp3 = exit.getCurrentImage(handler);
            g.drawImage(temp3, exit.getX(), exit.getY(), temp3.getWidth() * 2, temp3.getHeight() * 2, null);

        }
    }

    public LevelObject[] getALL_LEVELS() {
        return ALL_LEVELS;
    }
}
