import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class CardThunderBolt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CardMiniPotion extends Card
{
    private String EFFECT = "HP + 1";
    private int CARD_NUM = 1;
    
    private int addHealth = 1;
    private boolean prepared;
    
    public CardMiniPotion(int slotNum){
        super(slotNum);
        
        cardEffect = EFFECT;
        cardNum = CARD_NUM;
        prepared = false;
    }    
    
    public void prepare(Me me, ArrayList<Enemy> enemys, boolean byMe){
        super.use();
        
        if(byMe){
            me.changeHealth(addHealth);
        } else{
            int i = Greenfoot.getRandomNumber(enemys.size());
            enemys.get(i).changeHealth(addHealth);
        }
        
        prepared = true;
    }
    
    public void execute(Me me, ArrayList<Enemy> enemys, boolean byMe){
        if(!prepared){
            prepare(me, enemys, byMe);
        } else{
            isActed = true;
            getWorld().removeObject(this);
        }
    }
}
