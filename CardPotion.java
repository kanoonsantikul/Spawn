import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class CardPotion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CardPotion extends Card
{
    private String EFFECT = "Give 4 HP to one that play this card and 2 to an enemy";
    private int CARD_NUM = 1;
    
    private int addMyHealth = 4;
    private int addEnemyHealth = 2; 
    private boolean prepared;
    
    public CardPotion(int slotNum){
        super(slotNum);
        
        cardEffect = EFFECT;
        cardNum = CARD_NUM;
        prepared = false;
    }    
    
    public void prepare(Me me, ArrayList<Enemy> enemys, boolean byMe){
        super.use();
        
        int position;
        do{
            position = Greenfoot.getRandomNumber(enemys.size());
        }while(enemys.get(position) == null);
        Enemy enemy = enemys.get(position);
        
        if(byMe){
            me.changeHealth(addMyHealth);
            enemy.changeHealth(addEnemyHealth);
        } else{
            me.changeHealth(addEnemyHealth);
            enemy.changeHealth(addMyHealth);
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
