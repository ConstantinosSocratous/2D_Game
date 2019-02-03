package tilegame.worlds;

import com.sun.java.accessibility.util.AccessibilityListenerList;
import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.entities.MovingWall;
import tilegame.entities.creatures.Enemy;
import tilegame.entities.creatures.Mushroom;
import tilegame.entities.creatures.SmallCreature;
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

    public static void LEVEL0(boolean respawnCoins){
        //ALL_LEVELS[0]
        //LevelsState.ALL_LEVELS[0].setEligableToJump(true);

        //CREATE MUSHROOMS
        handler.getWorld().getEntityManager().addEntity( new Mushroom(handler,1920,780,false,4));
        handler.getWorld().getEntityManager().addEntity( new Mushroom(handler,3456,780,false,3));
        handler.getWorld().getEntityManager().addEntity( new Mushroom(handler,5056,780,false,4));

        //CREATE COINS
        if(respawnCoins) {
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 896, 780, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1665, 780, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1856, 585, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 2688, 395, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 2880, 780, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 3776, 730, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 3400, 460, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 3530, 265, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 4595, 475, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 5312, 780, false));
        }
        //CREATE TRAPS
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,2560,590,96,64,2000));
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,4288,780,128,64,1500));

        //CREATE CHECKPOINTS
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,2944,482,64,160,2));

        //CREATE WINNING DOOR
        handler.getWorld().getEntityManager().addEntity(new Door(handler,6272,574));

    }
    public static void LEVEL1(boolean respawnCoins){

        //CREATE MUSHROOMS
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,1280,334,true,3));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,3200,851,true,4));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4992,851,true,5));

        //CREATE COINS
        if(respawnCoins) {
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 720, 651, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 2112, 266, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1472, 781, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 3200, 781, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 2368, 661, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 3392, 486, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 4416, 650, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 4672, 128, false));
        }

        //CREATE TRAPS
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,2048,266,96,64,1700));
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,1728,586,96,64,2100));
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,2176,1040,128,64,2100));

        //CREATE CHECKPOINTS
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,2368,163,64,160,2));
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,3264,163,64,160,2));

        //CREATE ENEMIES
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,4224,616,2,3.5f,"RIGHT",60));//4224

        //CREATE WINNING DOOR
        handler.getWorld().getEntityManager().addEntity(new Door(handler,6272,510));

    }

    public static void LEVEL2(boolean respawnCoins){
        //ALL_LEVELS[0]


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
        if(respawnCoins) {
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 300, 600, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 555, 600, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 780, 463, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1024, 525, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1408, 576, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1024, 730, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1540, 528, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1664, 528, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1792, 528, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1940, 700, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 2112, 768, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 2305, 768, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 2750, 320, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 2688, 448, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 2816, 576, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 3328, 448, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 3584, 576, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 4032, 196, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 4544, 323, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 4992, 773, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 3900, 755, false));
        }

        //CREATE TRAPS
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,1024,515,128,64,2000));
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,3900,770,96,64,2000));
        //handler.getWorld().getEntityManager().addEntity(new Trap(handler,400,590,0,0));


        //CREATE CHECKPOINTS
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,1940,673,64,160,2));
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,3410,673,64,160,2));
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,4232,160,64,160,2));

        //CREATE ENEMIES
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,4000,680,3,3f,"",55));//4224

        //MOVING WALL
        handler.getWorld().getEntityManager().addEntity(new MovingWall(handler,3750, 400,128,32, 300, false,true,3f));

        //CREATE WINNING DOOR
        handler.getWorld().getEntityManager().addEntity(new Door(handler,6272,574));

    }

    public static void LEVEL3(boolean respawnCoins){


        //CREATE MUSHROOMS
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,1160,773,true,3));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,700,773,true,4));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,3050,140,true,4));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,3850,780,false,5));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4224,780,true,4));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4544,780,false,4));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,5632,586,false,4));


        //CREATE COINGS
        if(respawnCoins) {
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1152, 780, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1024, 780, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 960, 585, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1400, 650, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1660, 715, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1788, 782, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1665, 460, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1680, 140, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 3250, 140, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 2690, 325, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 4030, 780, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 3950, 780, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 4400, 780, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 4630, 462, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 4780, 462, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 5248, 523, false));
        }

        //CREATE TRAPS
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,1408,272,128,64,2000));
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,3920,785,128,64,2000));
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,4608,460,128,64,3000));
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,4896,460,96,64,1000));

        //CREATE CHECKPOINTS
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,1790,673,64,160,2));
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,3650,290,64,160,2));

        //MOVING WALL
        handler.getWorld().getEntityManager().addEntity(new MovingWall(handler,1200, 400,128,32, 250, false,true,1.5f));
        handler.getWorld().getEntityManager().addEntity(new MovingWall(handler,2400, 500,128,32, 200, false,true,2.5f));
        handler.getWorld().getEntityManager().addEntity(new MovingWall(handler,2600, 300,128,32, 200, false,true,3.2f));
        handler.getWorld().getEntityManager().addEntity(new MovingWall(handler,5800, 500,128,32, 250, false,true,2.3f));


        //CREATE WINNING DOOR
        handler.getWorld().getEntityManager().addEntity(new Door(handler,6272,574));

    }

    public static void LEVEL4(boolean respawnCoins){

        //CREATE MUSHROOMS
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4800,665,true,4));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,5796,902,true,4));

        //CREATE COINGS
        if(respawnCoins) {
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1700, 690, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 710, 350, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 3560, 270, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 4690, 460, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 4620, 202, false));
        }

        //CREATE TRAPS
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,3712,660,128,64,2000));

        //CREATE CHECKPOINTS
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,1664,610,64,160,2));
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,1828,96,64,160,2));

        //CREATE ENEMIES
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,1024,850,2,3.4f,"LEFT",55));//4224
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,1920,850,3,3.4f,"RIGHT",55));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,960,384,2,3.4f,"LEFT",45));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,896,50,3,3.4f,"LEFT",60));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,1728,50,4,3.4f,"",45));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,2550,50,4,3.4f,"",50));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,3776,512,3,3.4f,"LEFT",60));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,3776,512,3,3.4f,"RIGHT",60));



        //CREATE WINNING DOOR
        handler.getWorld().getEntityManager().addEntity(new Door(handler,7532,700));

    }

    public static void LEVEL5(boolean respawnCoins) {
        //CREATE MUSHROOMS
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler, 2368, 847, true, 4));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler, 3000, 847, false, 4));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler, 3712, 1200, false, 3));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler, 896, 880, false, 3));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler, 448, 930, false, 3));

        //CREATE COINS
        if (respawnCoins) {
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1600, 384, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 2752, 512, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 2432, 1152, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 3008, 1216, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 5184, 832, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1152, 896, false));
        }

        //CREATE TRAPS
        handler.getWorld().getEntityManager().addEntity(new Trap(handler, 1856, 905, 128, 64, 2000));
        handler.getWorld().getEntityManager().addEntity(new Trap(handler, 3392, 1230, 128, 64, 2000));


        //CREATE CHECKPOINTS
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler, 2048, 675, 64, 160, 2));
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler, 2410, 1060, 64, 160, 2));

        //CREATE ENEMIES
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler, 3200, 200, 5, 3.4f, "", 120));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler, 2680, 50, 1, 3.4f, "", 150));

        handler.getWorld().getEntityManager().addEntity(new Enemy(handler, 1664, 1200, 4, 3.4f, "LEFT", 60));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler, 2280, 1080, 3, 3.4f, "", 230));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler, 4865, 832, 5, 3.4f, "", 120));
        //CREATE WINNING DOOR
        handler.getWorld().getEntityManager().addEntity(new Door(handler, 6900, 830));

    }

    public static void LEVEL6(boolean respawnCoins){
        //CREATE MUSHROOMS
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,855,245,true,2));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,1792,775,false,5));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4224,128,false,5));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4608,128,false,5));

        //CREATE COINS
        if(respawnCoins) {
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 855, 380, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 855, 160, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 930, 160, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 1920, 715, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 6720, 576, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 7040, 768, false));
            handler.getWorld().getEntityManager().addEntity(new Coin(handler, 8768, 395, false));
        }

        //CREATE TRAPS
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,1024,585,128,64,2000));
        handler.getWorld().getEntityManager().addEntity(new Trap(handler,2368,525,128,64,2000));


        //CREATE CHECKPOINTS
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,2240,482,64,160,2));
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,5696,680,64,160,2));


        //CREATE ENEMIES
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,1850,300,6,2.8f,"LEFT",60));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,3772,50,6,2.8f,"",60));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,3264,400,6,3.7f,"RIGHT",60));

        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,8270,500,6,3.7f,"",60));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,8324,200,6,3.7f,"",60));
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,8270,50,6,3.7f,"",60));

        //CREATE GREEN ENEMIES
        handler.getWorld().getEntityManager().addEntity(new EnemyGreen(handler,4736,320,120));
        handler.getWorld().getEntityManager().addEntity(new EnemyGreen(handler,5632,320,160));

        //CREATE WINNING DOOR
        handler.getWorld().getEntityManager().addEntity(new Door(handler,8820,190));

    }

    public static void LEVEL7(boolean respawnCoins){
        //CREATE MUSHROOMS
        //handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,855,245,true,2));

        //CREATE COINS
        if(respawnCoins) {
           // handler.getWorld().getEntityManager().addEntity(new Coin(handler, 855, 380, false));

        }

        //CREATE TRAPS
        //handler.getWorld().getEntityManager().addEntity(new Trap(handler,1024,585,128,64,2000));

        //CREATE CHECKPOINTS
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,3600,680,64,160,2));
        handler.getWorld().getEntityManager().addEntity(new CheckPoint(handler,6500,680,64,160,2));

        //CREATE ENEMIES
        handler.getWorld().getEntityManager().addEntity(new Enemy(handler,2600,300,6,2.8f,"",70));

        //CREATE GREEN ENEMIES
        handler.getWorld().getEntityManager().addEntity(new EnemyGreen(handler,3010,191,135));
        handler.getWorld().getEntityManager().addEntity(new EnemyGreen(handler,5380,256,135));

        //MOVING WALL
        handler.getWorld().getEntityManager().addEntity(new MovingWall(handler,950, 128,160,32, 450, false,true,2.2f));
        handler.getWorld().getEntityManager().addEntity(new MovingWall(handler,1160, 400,128,32, 400, false,true,3.1f));
        handler.getWorld().getEntityManager().addEntity(new MovingWall(handler,1500, 220,100,32, 450, false,true,3.3f));
        handler.getWorld().getEntityManager().addEntity(new MovingWall(handler,1750, 128,120,32, 450, false,true,2.7f));
        handler.getWorld().getEntityManager().addEntity(new MovingWall(handler,2100, 160,120,32, 580, false,true,3f));
        handler.getWorld().getEntityManager().addEntity(new MovingWall(handler,4450, 335,120,32, 580, false,true,3.2f));
        handler.getWorld().getEntityManager().addEntity(new MovingWall(handler,4650, 450,120,32, 520, false,true,2f));


        //CREATE WINNING DOOR
        handler.getWorld().getEntityManager().addEntity(new Door(handler,10100,600));

    }

    public static void goToLevel(int num){
        if(num != ((GameState)(handler.getGame().getGameState())).getCurrentLevel()){
            SoundManager.stopAll();
            SoundManager.jungle.loop();
        }

        if(num >= LevelObject.ALL_LEVEL_OBJ.size()){
            ((GameState)(handler.getGame().getGameState())).setCurrentLevel(0);
            ((GameState)(handler.getGame().getGameState())).exitGameState();
        }else {

            if (num == 0 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked()) {
                ((GameState) (handler.getGame().getGameState())).setCurrentLevel(0);

                AllLevels.prepareWorld();

                AllLevels.LEVEL0(true);
                int t = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
                LevelObject.ALL_LEVEL_OBJ.get(t).setMaxScore(AllLevels.getMaxScore());
            } else if (num == 1 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked()) {
                ((GameState) (handler.getGame().getGameState())).setCurrentLevel(1);
                AllLevels.prepareWorld();

                AllLevels.LEVEL1(true);
                int t = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
                LevelObject.ALL_LEVEL_OBJ.get(t).setMaxScore(AllLevels.getMaxScore());
            } else if (num == 2 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked()) {
                ((GameState) (handler.getGame().getGameState())).setCurrentLevel(2);

                AllLevels.prepareWorld();

                AllLevels.LEVEL2(true);
                int t = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
                LevelObject.ALL_LEVEL_OBJ.get(t).setMaxScore(AllLevels.getMaxScore());
            } else if (num == 3 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked()) {
                ((GameState) (handler.getGame().getGameState())).setCurrentLevel(3);

                AllLevels.prepareWorld();

                AllLevels.LEVEL3(true);
                int t = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
                LevelObject.ALL_LEVEL_OBJ.get(t).setMaxScore(AllLevels.getMaxScore());
            } else if (num == 4 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked()) {
                ((GameState) (handler.getGame().getGameState())).setCurrentLevel(4);

                AllLevels.prepareWorld();

                AllLevels.LEVEL4(true);
                int t = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
                LevelObject.ALL_LEVEL_OBJ.get(t).setMaxScore(AllLevels.getMaxScore());
            }else if (num == 5 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked()) {
                ((GameState) (handler.getGame().getGameState())).setCurrentLevel(5);

                AllLevels.prepareWorld();

                AllLevels.LEVEL5(true);
                int t = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
                LevelObject.ALL_LEVEL_OBJ.get(t).setMaxScore(AllLevels.getMaxScore());
            }else if (num == 6 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked()) {
                ((GameState) (handler.getGame().getGameState())).setCurrentLevel(6);

                AllLevels.prepareWorld();

                AllLevels.LEVEL6(true);
                int t = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
                LevelObject.ALL_LEVEL_OBJ.get(t).setMaxScore(AllLevels.getMaxScore());
            }else if (num == 7 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked()) {
                ((GameState) (handler.getGame().getGameState())).setCurrentLevel(7);

                AllLevels.prepareWorld();

                AllLevels.LEVEL7(true);
                int t = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
                LevelObject.ALL_LEVEL_OBJ.get(t).setMaxScore(AllLevels.getMaxScore());
            }


        }
    }

    public static void prepareWorld(){
        int temp = ((GameState)(handler.getGame().getGameState())).getCurrentLevel();
        handler.getGame().getGameState().init(LevelObject.ALL_LEVEL_OBJ.get(temp).getPath());
        ((GameState)handler.getGame().getGameState()).setCurrentCheckPoint(null);
        State.setState(handler.getGame().getGameState());
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
        }else if(num == 5 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked()){
            AllLevels.LEVEL5_BG();
        }else if(num == 6 && !LevelObject.ALL_LEVEL_OBJ.get(num).isLocked()){
            AllLevels.LEVEL6_BG();
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
    public static void LEVEL5_BG(){

    }
    public static void LEVEL6_BG(){

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
