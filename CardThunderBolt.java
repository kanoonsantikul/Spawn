import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class CardThunderBolt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CardThunderBolt extends Card
{
    private String EFFECT = "deal 1 damage to all character";
    private int CARD_NUM = 0;
    
    private int attack = 1;
    private long timeStart;
    private boolean prepared;
    
    public CardThunderBolt(int slotNum){
        super(slotNum);
        
        cardEffect = EFFECT;
        cardNum = CARD_NUM;
        prepared = false;
    }
    
    public void prepare(Me me, ArrayList<Enemy> enemys, boolean byMe){
        super.use();
        
        ArrayList<Creature> actors = new ArrayList<Creature>();
        actors.addAll(enemys);
        actors.add(me);
        
        Creature actor;
        for(int i=0; i<actors.size(); i++){
            actor = actors.get(i);
            getWorld().addObject(
                    new AttackGraphic(actor, attack),
                    actor.getX(), 
                    actor.getY());
        }
        
        timeStart = System.currentTimeMillis();
        
        prepared = true;
    }
    
    public void execute(Me me, ArrayList<Enemy> enemys, boolean byMe){
        if(!prepared){
            prepare(me, enemys, byMe);
        } else if(System.currentTimeMillis() - timeStart >= 350){
            isActed = true;
            getWorld().removeObject(this);
        }
    }
}