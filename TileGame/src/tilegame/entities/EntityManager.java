package tilegame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import tilegame.Cinematic.Kingdom;
import tilegame.Handler;
import tilegame.entities.creatures.Player;
import tilegame.entities.statics.CheckPoint;
import tilegame.entities.statics.Coin;

public class EntityManager {

	private boolean checkpoint = false;
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private ArrayList<Entity> deletedEntities;
	private Comparator<Entity> renderSorter = new Comparator<Entity>(){
		@Override
		public int compare(Entity a, Entity b) {
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
				return -1;
			return 1;
		}
	};
	
	public EntityManager(Handler handler){
		this.handler = handler;
		entities = new ArrayList<Entity>();
		deletedEntities = new ArrayList<Entity>();
	}
	
	public void tick(){
		for(int i = 0;i < entities.size();i++){
			Entity e = entities.get(i);
			if(e instanceof Kingdom) e.tick();
		}
		for(int i = 0;i < entities.size();i++){
			Entity e = entities.get(i);
			if(!(e instanceof  Kingdom))e.tick();
		}
		entities.sort(renderSorter);
	}
	
	public void render(Graphics g){
		for(Entity e : entities){
			e.render(g);
		}
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}

	public void removeEntityEntirely(Entity e){
		entities.remove(e);
	}

	public void deleteEntity(Entity e){
		//for(Entity ent : entities){
		//	if(e.equals(ent))
		if(e instanceof CheckPoint){

            ArrayList<Entity> temp = new ArrayList<>();
            if(deletedEntities.size() == 0)return;
            for(Entity ent: deletedEntities){
                if((ent instanceof Coin))
                		continue;
				temp.add(ent);
            }
            for(Entity ent: temp){
                deletedEntities.remove(ent);
            }


            for(Entity entity : entities){
				if(entity instanceof Coin)
					((Coin) entity).setGenerated(false);
			}

			entities.remove(e);
		}else{

			deletedEntities.add(e);
			entities.remove(e);
		}
		//}
	}

	public void respawnDeleted(){

		for(Entity e: deletedEntities){
			if((e instanceof Coin)) {
				continue;
			}
            player.decreaseScore(1);
			entities.add(e);

		}

		for(Entity e: entities){
            if((e instanceof Coin)) {
                if(((Coin) e).isGenerated()) {
                    player.increaseScore(1);
                }
            }
        }
		deleteGeneratedCoins();
		emptyDeletedEntities();
	}

	public void deleteGeneratedCoins(){
		ArrayList<Entity> temp = new ArrayList<>();
		for(Entity e: entities){
			if(e instanceof Coin){
				if(((Coin) e).isGenerated()) temp.add(e);
			}
		}
		for(Entity e: temp){
			entities.remove(e);
		}
	}

	public void emptyDeletedEntities(){
		ArrayList<Entity> temp = new ArrayList<>();
		if(deletedEntities.size() == 0)return;
		for(Entity ent: deletedEntities){
			temp.add(ent);
		}
		for(Entity ent: temp){
			deletedEntities.remove(ent);
		}
	}


	//GETTERS SETTERS
	public void deleteCollusionBullets(){
		ArrayList<Entity> temp = new ArrayList<>();
		for(Entity entity : entities){
			if(entity instanceof Bullet){
				if(((Bullet) entity).isCollusion()){
					temp.add(entity);
				}
			}
		}
		for(Entity e: temp){
			entities.remove(e);
		}
	}

	public ArrayList<Entity> getBullets(){
		ArrayList<Entity> temp = new ArrayList<>();
		for(Entity entity : entities){
			if(entity instanceof Bullet){
				temp.add(entity);
			}
		}

		return temp;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

}
