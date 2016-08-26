import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class CardTheVoid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CardTheVoid extends Card
{
    private String EFFECT = "I am 'The Void' I have no effect";
    private int CARD_NUM = 2;
    
    private boolean prepared;
    
    public CardTheVoid(int slotNum){
        super(slotNum);
        
        cardEffect = EFFECT;
        cardNum = CARD_NUM;
        prepared = false;
    }
    
    public void prepare(Me me, ArrayList<Enemy> enemys, boolean byMe){
        super.use();
        
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
