import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class KeeperAlarm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CardGuardKeeper extends Card
{
    private String EFFECT = "Summon Guard Keeper at random position";
    private int CARD_NUM = 2;
    
    private boolean prepared;
    
    public CardGuardKeeper(int slotNum){
        super(slotNum);
        
        cardEffect = EFFECT;
        cardNum = CARD_NUM;
        prepared = false;
    }
    
    public void prepare(Me me, ArrayList<Enemy> enemys, boolean byMe){
        super.use();
        
        BackgroundWorld world = (BackgroundWorld)getWorld();
        Enemy enemy = new GuardKeeper();
        enemys.add(enemy);
        enemy.setListener(world.getManager());
        new SpawnAction(world, enemy);
        
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
