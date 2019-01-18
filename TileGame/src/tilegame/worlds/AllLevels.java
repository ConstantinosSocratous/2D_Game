package tilegame.worlds;

import tilegame.Handler;
import tilegame.entities.JumpingWall;
import tilegame.entities.creatures.Mushroom;
import tilegame.entities.statics.Coin;
import tilegame.entities.statics.Door;

public class AllLevels {

    private static Handler handler;

    public AllLevels(Handler handler){
        this.handler = handler;
    }

    public static void LEVEL1(){

        Mushroom m = new Mushroom(handler,1600,517,false,4);
        handler.getWorld().getEntityManager().addEntity(m);

        Mushroom m1 = new Mushroom(handler,2300,582,true,3);
        handler.getWorld().getEntityManager().addEntity(m1);

        Mushroom m2 = new Mushroom(handler,2800,582,false,3);
        handler.getWorld().getEntityManager().addEntity(m2);

        Mushroom m3 = new Mushroom(handler,3800,582,true,3);
        handler.getWorld().getEntityManager().addEntity(m3);

        Mushroom m4 = new Mushroom(handler,4150,800,true,5);
        handler.getWorld().getEntityManager().addEntity(m4);

        Mushroom m5 = new Mushroom(handler,4980,800,false,5);
        handler.getWorld().getEntityManager().addEntity(m5);

        Mushroom m6 = new Mushroom(handler,4400,800,false,5);
        handler.getWorld().getEntityManager().addEntity(m6);

        Coin c = new Coin(handler,300,300,0,0);
        handler.getWorld().getEntityManager().addEntity(c);

        Door door = new Door(handler,2500,897);
        handler.getWorld().getEntityManager().addEntity(door);

        //JumpingWall wall = new JumpingWall(handler,300,460);
        //handler.getWorld().getEntityManager().addEntity(wall);

    }

    public static void LEVEL2(){
        //Mushroom m = new Mushroom(handler,800,550);


        //Door door = new Door(handler,2500,897);
        //handler.getWorld().getEntityManager().addEntity(door);
    }
}
