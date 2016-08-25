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
    private String EFFECT = "deal 1 damage to all opponent";
    private int CARD_NUM = 0;
    
    public CardThunderBolt(int slotNum){
        super(slotNum);
        
        cardEffect = EFFECT;
        cardNum = CARD_NUM;
    }
    
    public void use(Me me, ArrayList<Enemy> enemys, Board board, boolean byMe){
        super.use(me ,enemys, board, byMe);
        
        if(byMe){
            Enemy enemy;
            for(int i=0; i<enemys.size(); i++){
                enemy = enemys.get(i);
                getWorld().addObject(
                        new AttackGraphic(enemy, 1),
                        enemy.getX(), 
                       enemy.getY());
            }
        } else{
            getWorld().addObject(
                    new AttackGraphic(me, 1), 
                    me.getX(),
                    me.getY());
        }
        
        getWorld().removeObject(this);
    }
}
