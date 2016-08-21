import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Creature here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Creature extends Actor
{
    public static final int SPEED = 1;
    
    protected int position;
    protected int range;
    
    public int getRange(){
        return range;
    }
    
    public void setPosition(int position){
        this.position = position;
    }
    
    public int getPosition(){
        return position;
    }
}
