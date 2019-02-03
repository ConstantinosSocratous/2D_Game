package tilegame.UI;

import tilegame.Handler;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;

import java.awt.*;
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
    private int playerMaxScore = 0;
    private LevelObject next;
    private Animation animCoin;

    public LevelObject(Handler handler, int x, int y,int r, String path, String title,int difficulty, boolean locked){
        this.x = x;
        this.y = y;
        this.path = path;
        this.title = title;
        this.handler = handler;
        this.difficulty = difficulty;
        this.radius = r;
        animCoin = new Animation(100, Assets.coin);
        this.isLocked = locked;

        LevelObject.addLevel(this);
    }
    public void setMaxScore(int x){
            this.maxScore = x;
    }
    public int getMaxScore(){return maxScore;}
    public boolean isLocked(){return isLocked;}
    public void setIsLocked(boolean bool){this.isLocked = bool;}

    public String getPath(){return path;}
    public String getTitle(){return title;}


    public void tick(){
        if(isMouseOver(handler)){
            isMouseOver = true;
        }else isMouseOver = false;

        animCoin.tick();
    }

    public void render(Graphics g) {

        //Draw Line to next Level
        if(next!=null && !isLocked()){
            Graphics2D g2 = (Graphics2D) g;
            g.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(8));
            g2.drawLine((int)(x+radius/2),(int)(y+radius/2),(int)(next.getX()+next.getRadius()/2),(int)(next.getY()+next.getRadius()/2));
            g.setColor(Color.BLACK);
        }

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

        g.setFont(new Font("TimesRoman", Font.BOLD, 28));
        g.drawString(title, x,y-2); //DRAW TITLE

        g.setFont(new Font("TimesRoman", Font.BOLD, 27));
        g.drawString(""+playerMaxScore,(int)(x+radius/4),(int)(y+radius/2));    //DRAW SCORE
        //DRAW COIN
        g.drawImage(animCoin.getCurrentFrame(),(int)(x+radius/2),(int)(y+radius/3),animCoin.getCurrentFrame().getWidth(),animCoin.getCurrentFrame().getWidth(),null);

        g.setFont(new Font("TimesRoman", Font.BOLD, 25));
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


    public LevelObject getNext() {
        return next;
    }

    public void setNext(LevelObject next) {
        this.next = next;
    }

    public int getX(){ return x;}
    public int getY(){ return y;}
    public int getRadius(){ return radius;}

    public int getPlayerMaxScore() {
        return playerMaxScore;
    }

    public void setPlayerMaxScore(int playerMaxScore) {
        if(playerMaxScore > this.playerMaxScore)
        this.playerMaxScore = playerMaxScore;
    }
}
