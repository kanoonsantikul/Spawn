import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends World
{   
    public GameOver(int score)
    {    
        super(BackgroundWorld.WIDTH,
                BackgroundWorld.HEIGHT,
                BackgroundWorld.CELL_SIZE);
        
        Text gameOverText = new Text("DEFEATED", 50);
        addObject(gameOverText, BackgroundWorld.WIDTH/2, BackgroundWorld.HEIGHT/2 - 50);
        Text scoreText = new Text("Longest Survival : " + score, 40);
        addObject(scoreText, BackgroundWorld.WIDTH/2, BackgroundWorld.HEIGHT/2 + 20);
        Text instructText = new Text("Click Anywhere to restart", 20);
        addObject(instructText, BackgroundWorld.WIDTH/2, BackgroundWorld.HEIGHT/2 + 50);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            Greenfoot.setWorld(new BackgroundWorld());
        }
    }
    
    public class Text extends Actor{
        public Text(String text, int size){
            GreenfootImage textImage 
                    = new GreenfootImage(text, size, Color.WHITE, new Color(0,0,0,0));
            setImage(textImage);
        }
    }
}
