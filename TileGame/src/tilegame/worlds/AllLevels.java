package tilegame.worlds;

import tilegame.Handler;
import tilegame.entities.creatures.Mushroom;

public class AllLevels {

    private static Handler handler;

    public AllLevels(Handler handler){
        this.handler = handler;
    }

    public static void LEVEL1(){
        Mushroom m = new Mushroom(handler,150,700);
        handler.getWorld().getEntityManager().addEntity(m);
    }

    public static void LEVEL2(){
        Mushroom m = new Mushroom(handler,150,700);
        handler.getWorld().getEntityManager().addEntity(m);
    }
}
