import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.lang.InterruptedException;

/**
 * Write a description of class Creature here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Creature extends Actor
{
    public static final int SPEED = 1;
    
    protected int range;
    protected int health;
    protected HealthText healthText;
    
    protected int position;
    protected boolean isMoved = false;
    protected boolean isAttacked = false;
    protected ArrayList<Action> actions;
    
    public Creature(){
        healthText = new HealthText();
        actions = new ArrayList<Action>();
    }
    
    public int getRange(){
        return range;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void changeHealth(int diff){
        health += diff;
    }
    
    public HealthText getHealthText(){
        return healthText;
    }
    
    public void drawHealthText(){
        World world = getWorld();
        world.removeObject(healthText);
        healthText = new HealthText();
        world.addObject(getHealthText(),
                getX(),
                getY() - getImage().getHeight()/2);
    }
    
    public void setPosition(int position){
        this.position = position;
    }
    
    public int getPosition(){
        return position;
    }
    
    public void setIsMoved(boolean isMoved){
        this.isMoved = isMoved;
    }
    
    public boolean getIsMoved(){
        return isMoved;
    }
    
    public void setIsAttacked(boolean isAttacked){
        this.isAttacked = isAttacked;
    }
    
    public boolean getIsAttacked(){
        return isAttacked;
    }

    public void addAction(Action action){
        actions.add(action);
    }
    
    public void removeAction(Action action){
        actions.remove(action);
    }
    
    public boolean hasAction(Action action){
        return actions.contains(action);
    }
    
    public boolean hasAction(){
        if(actions.size() > 0)
            return true;
        else
            return false;
    }
    
    public void act(){
        for(int i=0; i<actions.size(); i++){
            actions.get(i).act();
        }
    }
    
    public class HealthText extends Actor{
        public HealthText(){
            setImage(new GreenfootImage(health+"", 20, Color.BLACK, new Color(0,0,0,0)));
        }
    }
    
    public static void delay(int sec){
        try{
            TimeUnit.SECONDS.sleep(sec);
        } catch(InterruptedException e){
            System.out.println("can't delay");
        }
    }
}
