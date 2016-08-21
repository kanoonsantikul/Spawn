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
    protected Action action;
    
    public int getRange(){
        return range;
    }
    
    public void setPosition(int position){
        this.position = position;
    }
    
    public int getPosition(){
        return position;
    }
    
    public void setAction(Action action){
        this.action = action;
    }
    
    public void removeAction(){
        this.action = null;
    }
    
    public boolean hasAction(){
        return action != null;
    }
    
    public void act(){
        if(action != null)action.act();
    }
}
