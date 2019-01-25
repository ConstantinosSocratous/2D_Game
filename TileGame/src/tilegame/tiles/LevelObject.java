package tilegame.tiles;

import javafx.scene.shape.Circle;
import tilegame.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LevelObject{

    public static ArrayList<LevelObject> ALL_LEVEL_OBJ = new ArrayList<>();

    public static void addLevel(LevelObject l){
        LevelObject.ALL_LEVEL_OBJ.add(l);
    }

    private int x,y;
    private String path,title;
    private Handler handler;
    private boolean isEligableToJump = true;
    private int difficulty,radius;
    private boolean isMouseOver = false;
    private boolean isLocked;

    private int maxScore;

    public LevelObject(Handler handler, int x, int y,int r, String path, String title,int difficulty, boolean locked){
        this.x = x;
        this.y = y;
        this.path = path;
        this.title = title;
        this.handler = handler;
        this.difficulty = difficulty;
        this.radius = r;

        this.isLocked = locked;

        LevelObject.addLevel(this);
    }
    public void setMaxScore(int x){this.maxScore = x;}
    public int getMaxScore(){return maxScore;}
    public boolean isLocked(){return isLocked;}
    public void setIsLocked(boolean bool){this.isLocked = bool;}

    public String getPath(){return path;}
    public String getTitle(){return title;}


    public void tick(){
        if(isMouseOver(handler)){
            isMouseOver = true;
        }else isMouseOver = false;
    }

    public void render(Graphics g) {
        if(isLocked){
            g.setColor(new Color(0,0,0));
            g.fillOval(x, y, radius, radius);
            g.setColor(new Color(255,0,0));
            g.drawString("LOCKED" ,x, y-2);
            //g.setFont(new Font("TimesRoman", Font.BOLD, 25));
            return;
        }

        if(!isMouseOver) {
            g.setColor(chooseColor());
        }else{
            Color color = getModifiedColored(chooseColor());
            g.setColor(color);
        }
        g.fillOval(x, y, radius, radius);

        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.BOLD, 25));
        g.drawString(title, x,y-2);
    }

    public Color chooseColor(){
        if(difficulty == 1) {
            Color color = new Color(255,255,0 );
            return color;
        }else if(difficulty == 2) {
            Color color = new Color(255, 125, 0);
            return color;
        }else {
            Color color = new Color(255, 0, 0);
            return color;
        }
    }

    public Color getModifiedColored(Color c){
        int red = c.getRed();
        int green = c.getGreen();
        int blue = c.getBlue();
        //if(red + 100 <= 255) red+=100;
        //if(green + 100 <= 255) green+=100;
        if(blue + 100 <= 255) blue+=100;

        return new Color(red,green,blue);
    }
    
    public boolean isMouseOver(Handler handler){
        int mouseX = handler.getMouseManager().getMouseX();
        int mouseY = handler.getMouseManager().getMouseY();
        if( mouseX >= x && mouseY >= y
                && mouseX<= x + radius
                && mouseY<= y + radius )
            return true;
        else
            return false;
    }

    public boolean isEligableToJump() {
        return isEligableToJump;
    }

    public void setEligableToJump(boolean eligableToJump) {
        isEligableToJump = eligableToJump;
    }
}
