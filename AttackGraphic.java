import greenfoot.*;
import java.awt.Color;

/**
 * Write a description of class AttackGraphicAction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AttackGraphic extends Actor 
{
    long timeStart;
    int attack;
    Creature actor;
    Text text;
    
    public AttackGraphic(Creature actor, int attack){
        this.attack = attack;
        this.actor = actor;
        timeStart = System.currentTimeMillis();
        
        Greenfoot.playSound("attack-sound.wav");
    }
    
    @Override 
    protected void addedToWorld(World world){
        text = new Text("-" + attack, 30, Color.BLACK, new Color(0,0,0,0));
        world.addObject(text, actor.getX(), actor.getY());
        actor.changeHealth(-1*attack);
        if(actor.getHealth() < 1){
            actor.kill();
        }
    }
    
    @Override
    public void act(){
        if(System.currentTimeMillis() - timeStart >= 350){
            getWorld().removeObject(text);
            getWorld().removeObject(this);
        }
    }
}
