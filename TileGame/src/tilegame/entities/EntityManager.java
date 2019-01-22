package tilegame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import tilegame.Cinematic.Kingdom;
import tilegame.Handler;
import tilegame.entities.creatures.Player;

public class EntityManager {
	
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
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

	public void deleteEntity(Entity e){
		//for(Entity ent : entities){
		//	if(e.equals(ent))
				entities.remove(e);
		//}
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
