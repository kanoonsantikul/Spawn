import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score
{
    private static final int X = 200;
    private static final int Y = 40;
    
    private World world;
    private int score;
    private ScoreText text;
    
    public Score(World world){
        this.world = world;
        score = 0;
        drawText();
    }
    
    public void addScore(){
       score++;
       drawText();
    }
    
    public int getScore(){
        return score;
    }
    
    public void drawText(){
        if(text != null){
            world.removeObject(text);
        }
 
        text = new ScoreText(score);
        world.addObject(text, X + text.getImage().getWidth()/2, Y);
    }
    
    public class ScoreText extends Actor{
        private static final int TEXT_SIZE = 25;
        
        public ScoreText(int score){
            setImage(new GreenfootImage("Survival " + score + " turn", TEXT_SIZE, Color.WHITE, new Color(0,0,0,0)));
        }
    }
}
