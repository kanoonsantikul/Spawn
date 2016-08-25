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
    private String EFFECT = "HP + 2";
    private int CARD_NUM = 1;
    
    public CardMiniPotion(int slotNum){
        super(slotNum);
        
        cardEffect = EFFECT;
        cardNum = CARD_NUM;
    }
    
    public void use(Me me, ArrayList<Enemy> enemys, Board board, boolean byMe){
        super.use(me ,enemys, board, byMe);
        
        if(byMe){
            me.changeHealth(2);
        } else{
            int i = Greenfoot.getRandomNumber(enemys.size());
            enemys.get(i).changeHealth(2);
        }
        
        getWorld().removeObject(this);
    }
}
