package tilegame.worlds;

import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.entities.creatures.Enemy;
import tilegame.entities.creatures.Mushroom;
import tilegame.entities.statics.*;
import tilegame.gfx.SoundManager;
import tilegame.states.GameState;
import tilegame.states.State;
import tilegame.UI.LevelObject;

import java.util.ArrayList;

public class AllLevels {

    private static Handler handler;

    public AllLevels(Handler handler){
        this.handler = handler;
    }

    public static void LEVEL0(){
        //ALL_LEVELS[0]
        //LevelsState.ALL_LEVELS[0].setEligableToJump(true);
        int temp = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
        handler.getGame().getGameState().init(LevelObject.ALL_LEVEL_OBJ.get(temp).getPath());
        ((GameState)handler.getGame().getGameState()).setCurrentCheckPoint(null);
        State.setState(handler.getGame().getGameState());

        //CREATE MUSHROOMS
        handler.getWorld().getEntityManager().addEntity( new Mushroom(handler,1920,780,false,4));
        handler.getWorld().getEntityManager().addEntity( new Mushroom(handler,3456,780,false,3));
        handler.getWorld().getEntityManager().addEntity( new Mushroom(handler,5056,780,false,4));

        //CREATE COINS
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,896,780,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1665,780,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1856,585,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2688,395,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2880,780,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,3776,730,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,3400,460,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,3530,265,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,4595,475,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,5312,780,0,0));

        //CREATE TRAPS
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,2560,590,96,64,2000));
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,4288,780,128,64,1500));

        //CREATE CHECKPOINTS
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,2944,482,64,160,2));


        //CREATE WINNING DOOR
        handler.getWorld().getEntityManager().addEntity(new Door(handler,6272,574));

        int t = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
        LevelObject.ALL_LEVEL_OBJ.get(t).setMaxScore(AllLevels.getMaxScore());


    }
    public static void LEVEL1(){

        int temp = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
        handler.getGame().getGameState().init(LevelObject.ALL_LEVEL_OBJ.get(temp).getPath());
        ((GameState)handler.getGame().getGameState()).setCurrentCheckPoint(null);
        State.setState(handler.getGame().getGameState());

        //CREATE MUSHROOMS
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,1280,334,true,3));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,3200,851,true,4));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4992,851,true,5));

        //CREATE COINS
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,720,651,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2112,266,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1472,781,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,3200,781,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2368,661,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,3392,486,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,4416,650,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,4672,128,0,0));

        //CREATE TRAPS
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,2048,266,96,64,1700));
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,1728,586,96,64,2100));
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,2176,1040,128,64,2100));

        //CREATE CHECKPOINTS
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,2368,163,64,160,2));
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,3264,163,64,160,2));

        //CREATE ENEMIES
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,4224,616,2,3.5f,"RIGHT"));//4224

        //CREATE WINNING DOOR
        handler.getWorld().getEntityManager().addEntity(new Door(handler,6272,510));

        int t = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
        LevelObject.ALL_LEVEL_OBJ.get(t).setMaxScore(AllLevels.getMaxScore());

    }

    public static void LEVEL2(){
        //ALL_LEVELS[0]
        int temp = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
        handler.getGame().getGameState().init(LevelObject.ALL_LEVEL_OBJ.get(temp).getPath());
        ((GameState)handler.getGame().getGameState()).setCurrentCheckPoint(null);
        State.setState(handler.getGame().getGameState());

        //CREATE MUSHROOMS
        handler.getWorld().getEntityManager().addEntity( new Mushroom(handler,1600,517,false,4));
        handler.getWorld().getEntityManager().addEntity( new Mushroom(handler,2300,582,true,3));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,2800,582,false,3));
        //handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,3800,582,true,3));
        //handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4150,800,true,5));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4980,800,false,4));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4400,800,false,5));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4980,800,true,4));

        //CREATE COINS
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,300,600,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,555,600,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,780,463,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1024,525,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1408,576,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1024,730,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1540,528,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1664,528,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1792,528,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1940,700,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2112,768,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2305,768,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2750,320,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2688,448,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2816,576,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,3328,448,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,3584,576,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,4032,196,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,4544,323,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,4992,773,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,3900,755,0,0));

        //CREATE TRAPS
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,1024,515,128,64,2000));
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,3900,770,96,64,2000));
        //handler.getWorld().getEntityManager().addEntity(new Trap(handler,400,590,0,0));


        //CREATE CHECKPOINTS
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,1940,673,64,160,2));
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,3410,673,64,160,2));
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,4232,160,64,160,2));

        //CREATE ENEMIES
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,4000,680,3,3f,""));//4224

        //CREATE WINNING DOOR
        handler.getWorld().getEntityManager().addEntity(new Door(handler,6272,574));

        int t = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
        LevelObject.ALL_LEVEL_OBJ.get(t).setMaxScore(AllLevels.getMaxScore());
    }

    public static void LEVEL3(){

        int temp = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
        handler.getGame().getGameState().init(LevelObject.ALL_LEVEL_OBJ.get(temp).getPath());
        ((GameState)handler.getGame().getGameState()).setCurrentCheckPoint(null);
        State.setState(handler.getGame().getGameState());

        //CREATE MUSHROOMS
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,1160,773,true,3));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,700,773,true,4));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,2432,715,true,4));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,3050,140,true,4));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,3850,780,false,5));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4224,780,true,4));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4544,780,false,4));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,5632,586,false,4));


        //CREATE COINGS
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1152,780,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1024,780,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,960,585,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1400,650,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1660,715,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1788,782,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1665,460,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1680,140,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,3250,140,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2450,715,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2690,325,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,4030,780,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,3950,780,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,4400,780,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,4630,462,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,4780,462,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,5248,523,0,0));

        //CREATE TRAPS
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,1408,272,128,64,2000));
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,3920,785,128,64,2000));
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,4608,460,128,64,3000));
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,4896,460,96,64,1000));

        //CREATE CHECKPOINTS
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,1790,673,64,160,2));
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,3650,290,64,160,2));

        //CREATE WINNING DOOR
        handler.getWorld().getEntityManager().addEntity(new Door(handler,6272,574));

        int t = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
        LevelObject.ALL_LEVEL_OBJ.get(t).setMaxScore(AllLevels.getMaxScore());
    }

    public static void LEVEL4(){

        int temp = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
        handler.getGame().getGameState().init(LevelObject.ALL_LEVEL_OBJ.get(temp).getPath());
        ((GameState)handler.getGame().getGameState()).setCurrentCheckPoint(null);
        State.setState(handler.getGame().getGameState());

        //CREATE MUSHROOMS
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4800,665,true,4));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,5796,902,true,4));

        //CREATE COINGS
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1700,690,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,710,350,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,3560,270,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,4690,460,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,4620,202,0,0));


        //CREATE TRAPS
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,3712,660,128,64,2000));

        //CREATE CHECKPOINTS
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,1664,610,64,160,2));
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,1828,96,64,160,2));

        //CREATE ENEMIES
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,1024,850,2,3.4f,"LEFT"));//4224
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,1920,850,4,3.4f,"RIGHT"));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,960,384,3,3.4f,"LEFT"));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,896,50,4,3.4f,"LEFT"));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,1728,50,3,3.4f,""));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,2550,50,3,3.4f,""));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,3776,512,3,3.4f,"LEFT"));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,3776,512,3,3.4f,"RIGHT"));

        //CREATE WINNING DOOR
        handler.getWorld().getEntityManager().addEntity(new Door(handler,7532,700));

        int t = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
        LevelObject.ALL_LEVEL_OBJ.get(t).setMaxScore(AllLevels.getMaxScore());
    }

    public static void goToLevel(int num){
        if(num != ((GameState)(handler.getGame().getGameState())).getCurrentLevel()){
            SoundManager.stopAll();
            SoundManager.jungle.loop();
        }

        if(num == 0 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked() ){
            ((GameState)(handler.getGame().getGameState())).setCurrentLevel(0);
            AllLevels.LEVEL0();
        }else if(num == 1 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked() ){
            ((GameState)(handler.getGame().getGameState())).setCurrentLevel(1);
            AllLevels.LEVEL1();
        }else if(num == 2 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked()){
            ((GameState)(handler.getGame().getGameState())).setCurrentLevel(2);
            AllLevels.LEVEL2();
        }else if(num == 3 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked()){
            ((GameState)(handler.getGame().getGameState())).setCurrentLevel(3);
            AllLevels.LEVEL3();
        }else if(num == 4 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked()){
            ((GameState)(handler.getGame().getGameState())).setCurrentLevel(4);
            AllLevels.LEVEL4();
        }else if(num >= LevelObject.ALL_LEVEL_OBJ.size()){
            ((GameState)(handler.getGame().getGameState())).setCurrentLevel(0);
            ((GameState)(handler.getGame().getGameState())).exitGameState();
        }
    }

    public static void createBackground(int num){
        if(num == 0 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked() ){
            AllLevels.LEVEL0_BG();
        }else if(num == 1 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked() ){
            AllLevels.LEVEL1_BG();
        }else if(num == 2 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked()){
            AllLevels.LEVEL2_BG();
        }else if(num == 3 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked()){
            AllLevels.LEVEL3_BG();
        }else if(num == 4 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked()){
            AllLevels.LEVEL4_BG();
        }
    }

    public static void LEVEL0_BG(){
        //CREATE TREES
        //Tree t = new Tree(handler,1400,350,96,256);
        //t.render(handler.getGame().getGraphics());

    }
    public static void LEVEL1_BG(){

    }
    public static void LEVEL2_BG(){

    }
    public static void LEVEL3_BG(){

    }
    public static void LEVEL4_BG(){

    }

    public static int getMaxScore(){
        ArrayList<Entity> entities = handler.getWorld().getEntityManager().getEntities();

        int score = 0;
        for(Entity e : entities){
            if(e instanceof  Mushroom || e instanceof Coin || e instanceof Enemy){
                score+=100;
            }
        }

        return score;
    }
}
