package tilegame.worlds;

import tilegame.Handler;
import tilegame.entities.statics.Door;
import tilegame.entities.creatures.Mushroom;

public class AllLevels {

    private static Handler handler;

    public AllLevels(Handler handler){
        this.handler = handler;
    }

    public static void LEVEL1(){

        Mushroom m = new Mushroom(handler,520,205,false);
        handler.getWorld().getEntityManager().addEntity(m);

        Mushroom m1 = new Mushroom(handler,700,205,true);
        handler.getWorld().getEntityManager().addEntity(m1);

        Mushroom m2 = new Mushroom(handler,1325,327,false);
        handler.getWorld().getEntityManager().addEntity(m2);

        Mushroom m3 = new Mushroom(handler,1550,327,true);
        handler.getWorld().getEntityManager().addEntity(m3);

        Door door = new Door(handler,2860,1216-300);
        handler.getWorld().getEntityManager().addEntity(door);


    }

    public static void LEVEL2(){
        //Mushroom m = new Mushroom(handler,800,550);


        //handler.getWorld().getEntityManager().addEntity(m);
    }
}
