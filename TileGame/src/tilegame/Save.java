package tilegame;

import java.io.*;

public class Save implements Serializable {

    private Game game;

    public Save(Game g){
        this.game = game;
    }

    public void saveObj(){
        try {
            FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(game);

            o.close();
            f.close();

            FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
           // Person pr1 = (Person) oi.readObject();
            //Person pr2 = (Person) oi.readObject();

            Game ga = (Game) oi.readObject();

            System.out.println(ga.getHandler().getWorld().getEntityManager().getPlayer().getX());

//            System.out.println(pr2.toString());

            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}

