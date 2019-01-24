package tilegame;


public class Launcher {

	public static void main(String[] args){
		Game game = new Game("My Game!", 1000, 1000);
		game.start();

		//Save save = new Save(game);
		//save.saveObj();

	}

	
}
