package tilegame.worlds;

import tilegame.Handler;
import tilegame.entities.JumpingWall;
import tilegame.entities.creatures.Mushroom;
import tilegame.entities.statics.Coin;
import tilegame.entities.statics.Door;
import tilegame.states.LevelsState;
import tilegame.states.State;

public class AllLevels {

    private static Handler handler;
    private static int numOfLevels = 2;

    public AllLevels(Handler handler){
        this.handler = handler;
    }

    public static void LEVEL0(){

        //ALL_LEVELS[0]
        LevelsState.ALL_LEVELS[0].setEligableToJump(true);
        handler.getGame().getGameState().init(LevelsState.ALL_LEVELS[0].getPath());
        handler.getGame().getGameState().setCurrentLevel(1);
        State.setState(handler.getGame().getGameState());


        //CREATE MUSHROOMS
        handler.getWorld().getEntityManager().addEntity( new Mushroom(handler,1600,517,false,4));
        handler.getWorld().getEntityManager().addEntity( new Mushroom(handler,2300,582,true,3));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,2800,582,false,3));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,3800,582,true,3));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4150,800,true,5));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4980,800,false,4));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4400,800,false,5));
        handler.getWorld().getEntityManager().addEntity(new Mushroom(handler,4980,800,true,4));

        //CREATE COINS
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,300,600,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,555,600,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,685,520,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,780,463,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,576,245,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,830,245,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1024,515,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1280,384,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1408,576,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1024,730,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1095,730,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1216,730,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1540,528,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1664,528,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1792,528,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1728,320,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1792,832,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,1940,700,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2112,768,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2305,768,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2300,595,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2432,640,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2750,320,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2688,448,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2588,220,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2880,320,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,2816,576,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,3328,448,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,3328,640,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,3584,576,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,3328,138,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,4032,196,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,4544,323,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,4480,581,0,0));
        handler.getWorld().getEntityManager().addEntity(new Coin(handler,4992,773,0,0));
        //CREATE WINNING DOOR
        handler.getWorld().getEntityManager().addEntity(new Door(handler,6272,574));

        //JumpingWall wall = new JumpingWall(handler,300,460);
        //handler.getWorld().getEntityManager().addEntity(wall);

    }

    public static void LEVEL1(){

        handler.getGame().getGameState().init(LevelsState.ALL_LEVELS[1].getPath());
        handler.getGame().getGameState().setCurrentLevel(2);
        State.setState(handler.getGame().getGameState());
        //Mushroom m = new Mushroom(handler,800,550);


        //Door door = new Door(handler,2500,897);
        //handler.getWorld().getEntityManager().addEntity(door);
    }

    public static void goToLevel(int num){
        if(num == 0 )AllLevels.LEVEL0();
        else if(num == 1 )AllLevels.LEVEL1();

        else if(num >= LevelsState.ALL_LEVELS.length){
            AllLevels.LEVEL0();
        }
    }
}
