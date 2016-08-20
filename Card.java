import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card extends Actor
{
    private int width;
    private int height;
    
    public Card(){
        setImage("card.png");
        width = getImage().getWidth();
        height = getImage().getHeight();
    }
    
    public void act() 
    {
        // Add your action code here.
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
}
