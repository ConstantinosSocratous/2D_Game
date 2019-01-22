package tilegame.states;

import tilegame.Handler;
import tilegame.gfx.ImageLoader;
import tilegame.gfx.SoundManager;
import tilegame.gfx.SpriteSheet;
import tilegame.tiles.MuteObject;
import tilegame.tiles.UIObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SettingsState extends State{

    private BufferedImage background;
    private final int width = 32;
    private final int height = 32;
    private UIObject menu;
    private MuteObject mute;

    public SettingsState(Handler handler){
        super(handler);
        background = ImageLoader.loadImage("/textures/Background/bg.png");

        SpriteSheet sheet1 = new SpriteSheet(ImageLoader.loadImage("/textures/menuSheet.png"));

        BufferedImage[] menuI = new BufferedImage[2];
        menuI[0] = sheet1.crop(0,0, width,height);
        menuI[1] = sheet1.crop(width,0, width,height);
        menu = new UIObject(menuI,handler.getGame().getWidth() - width * 3, (int) (height / 2));

        BufferedImage[] muteI = new BufferedImage[2];
        muteI[0] = sheet1.crop(width*8,0, width*3,height*3);
        muteI[1] = sheet1.crop(width*11,0, width*3,height*3);
        mute = new MuteObject(muteI,handler.getGame().getWidth()/2-125,handler.getGame().getHeight()/2-150);
    }

    public void init(String path){

    }

    public void tick(){

        if(handler.getMouseManager().isLeftPressed()){
            if(menu.isMouseOver(handler)){
                //handler.getGame().getMenuState().init("");
                State.setState(handler.getGame().getMenuState());
                sleep(500);
            }else if(mute.isMouseOver(handler)){
                if(!SoundManager.mute){
                    SoundManager.muteAll();
                    mute.setCurrentImage(1);
                }else{
                    SoundManager.Unmute();
                    SoundManager.menu.loop();
                    mute.setCurrentImage(0);
                }
                try{
                    Thread.sleep(500);
                }catch (Exception e){e.printStackTrace();}
            }

        }

    }

    public void render(Graphics g){
        if(State.getState().equals(handler.getGame().getSettingsState())) {
            g.drawImage(background, 0, 0, handler.getWidth(), handler.getHeight(), null);

            BufferedImage temp3 = menu.getCurrentImage(handler);
            g.drawImage(temp3, menu.getX(), menu.getY(), temp3.getWidth() * 2, temp3.getHeight() * 2, null);

            BufferedImage temp4 = mute.getImage(mute.getCurrentImage());
            g.drawImage(temp4, mute.getX(), mute.getY(), temp4.getWidth() * 2, temp4.getHeight() * 2, null);

        }
    }
}
