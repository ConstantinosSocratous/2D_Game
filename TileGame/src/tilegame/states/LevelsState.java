package tilegame.states;

import tilegame.Handler;
import tilegame.gfx.ImageLoader;
import tilegame.gfx.SoundManager;
import tilegame.gfx.SpriteSheet;
import tilegame.UI.LevelObject;
import tilegame.UI.UIObject;
import tilegame.worlds.AllLevels;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelsState extends State{

    private BufferedImage lvls;
    private Background bg;
    private final int width = 32;
    private final int height = 32;
    public static LevelObject[] ALL_LEVELS = new LevelObject[3];
    private UIObject menu;
    private LevelObject lvl0,lvl1,lvl2,lvl3,lvl4,lvl5,lvl6,lvl7;

    public LevelsState(Handler handler){
        super(handler);
        bg = new Background(handler);

        lvls = ImageLoader.loadImage("/textures/Background/lvls.png");

        int w = (int) handler.getWidth()/12;
        int h = (int) handler.getHeight()/10;

        lvl0 = new LevelObject(handler, (int)(w/0.75), (int)(h*6.5),120, "/worlds/world0.txt", "The Basics",1,false);
        lvl1 = new LevelObject(handler, (int)(w*1.8) , (int)(h*4.5),120, "/worlds/world1.txt", "Getting into",1,true);
        lvl2 = new LevelObject(handler, (int)(w*1.3),  (int)(h*2.5),120, "/worlds/world2.txt", "Be Careful!!",1,true);
        lvl3 = new LevelObject(handler, (int)(w*2.4), (int)(h*0.7),120, "/worlds/world3.txt", "Above ground",2,true);
        lvl4 = new LevelObject(handler, (int)(w*3.8), (int)(h*0.9),120, "/worlds/MoreEnemies.txt", "More Enemies",2,true);
        lvl5 = new LevelObject(handler, (int)(w*3.4), (int)(h*2.7),120, "/worlds/UndergroundForest.txt", "Underground Forest",2,true);
        lvl6 = new LevelObject(handler, (int)(w*3.6), (int)(h*4.8),120, "/worlds/LongRun.txt", "Long Run",2,true);
        lvl7 = new LevelObject(handler, (int)(w*5), (int)(h*6.2),120, "/worlds/BeQuick.txt", "Be Quick",3,true);

        connectLevels();

        SpriteSheet sheet1 = new SpriteSheet(ImageLoader.loadImage("/textures/menuSheet.png"));
        BufferedImage[] menuI = new BufferedImage[2];
        menuI[0] = sheet1.crop(0,0, width,height);
        menuI[1] = sheet1.crop(width,0, width,height);
        menu = new UIObject(menuI,handler.getGame().getWidth() - width * 3, (int) (height / 2));
    }

    public void init(String path){

    }

    private void connectLevels(){
        lvl0.setNext(lvl1);
        lvl1.setNext(lvl2);
        lvl2.setNext(lvl3);
        lvl3.setNext(lvl4);
        lvl4.setNext(lvl5);
        lvl5.setNext(lvl6);
        lvl6.setNext(lvl7);
        lvl7.setNext(null);

    }

    public void tick(){
        if(handler.getMouseManager().isLeftPressed()){
            if(menu.isMouseOver(handler)){
                //handler.getGame().getMenuState().init("");
                State.setState(handler.getGame().getMenuState());
                sleep(500);
            }else if(lvl0.isMouseOver(handler)){    //LEVEL 0
                AllLevels.goToLevel(0);
                SoundManager.menu.stop();
                sleep(500);
            }else if(lvl1.isMouseOver(handler)){    //LEVEL 1
                AllLevels.goToLevel(1);
                SoundManager.menu.stop();
                sleep(500);
            }else if(lvl2.isMouseOver(handler)){    //LEVEL 2
                AllLevels.goToLevel(2);
                SoundManager.menu.stop();
                sleep(500);
            }else if(lvl3.isMouseOver(handler)){    //LEVEL 3
                AllLevels.goToLevel(3);
                SoundManager.menu.stop();
                sleep(500);
            }else if(lvl4.isMouseOver(handler)){    //LEVEL 4
                AllLevels.goToLevel(4);
                SoundManager.menu.stop();
                sleep(500);
            }else if(lvl5.isMouseOver(handler)){    //LEVEL 5
                AllLevels.goToLevel(5);
                SoundManager.menu.stop();
                sleep(500);
            }else if(lvl6.isMouseOver(handler)){    //LEVEL 6
                AllLevels.goToLevel(6);
                SoundManager.menu.stop();
                sleep(500);
            }else if(lvl7.isMouseOver(handler)){    //LEVEL 7
                AllLevels.goToLevel(7);
                SoundManager.menu.stop();
                sleep(500);
            }
        }
    }

    public void render(Graphics g){
        if(State.getState().equals(handler.getGame().getLevelState())) {
            bg.render(g);
            g.drawImage(lvls, 0, 0, handler.getWidth()-50, handler.getHeight()-50, null);

            for(LevelObject lvl : LevelObject.ALL_LEVEL_OBJ){
                lvl.tick();
                lvl.render(g);
            }
            BufferedImage temp3 = menu.getCurrentImage(handler);
            g.drawImage(temp3, menu.getX(), menu.getY(), temp3.getWidth() * 2, temp3.getHeight() * 2, null);

        }
    }

    public LevelObject[] getALL_LEVELS() {
        return ALL_LEVELS;
    }
}
