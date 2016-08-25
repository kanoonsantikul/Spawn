import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.ArrayList;

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
    protected int attack;
    protected int health;
    protected Text stateText;
    
    protected EventListener listener;
    protected boolean isMoved = false;
    protected boolean isAttacked = false;
    protected ArrayList<Action> actions;
    
    public Creature(){
        actions = new ArrayList<Action>();
    }
    
    @Override
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            if(listener != null){
                listener.onCreatureClicked(this);
            }
        }
        
        for(int i=0; i<actions.size(); i++){
            actions.get(i).act();
        }
    }
    
    @Override
    protected void addedToWorld(World world){
        drawStateText();
    }
    
    public int getRange(){
        return range;
    }
    
    public int getAttack(){
        return attack;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void changeHealth(int diff){
        health += diff;
        drawStateText();
    }
    
    public Text getStateText(){
        return stateText;
    }
       
    public Text makeStateText(){
        return new Text("ATK:" + attack + "  HP:" + health, 
                    15, 
                    Color.WHITE, 
                    new Color(0, 0, 0, 150));
    }
    
    public void drawStateText(){
        World world = getWorld();
        if(stateText != null){
            world.removeObject(stateText);
        }
        stateText = makeStateText();
        world.addObject(stateText,
                getX(),
                getY() + getImage().getHeight()/2);
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
    
    public void kill(){
        getWorld().removeObject(this.stateText);
        getWorld().removeObject(this);
        if(listener != null){
            listener.onCreatureKilled(this);
        }
    }
    
    public void setListener(EventListener listener){
        this.listener = listener;
    }
    
    public interface EventListener{
        public void onCreatureClicked(Creature actor);
        public void onCreatureKilled(Creature actor);
    }
}
