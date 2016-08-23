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

    }
    
    @Override
    public void act(){
        if(text == null){
            text = new Text("-" + attack, 30);
            getWorld().addObject(text, actor.getX(), actor.getY());
        }
        if(System.currentTimeMillis() - timeStart >= 270){
            getWorld().removeObject(text);
            getWorld().removeObject(this);
            actor.changeHealth(-1*attack);
            
            if(actor.getHealth() < 1){
                actor.kill();
            }
        }
    }
    
    public class Text extends Actor{
        public Text(String text, int size){
            GreenfootImage textImage 
                    = new GreenfootImage(text, size, Color.BLACK, new Color(0,0,0,0));
            setImage(textImage);
        }
    }
}
