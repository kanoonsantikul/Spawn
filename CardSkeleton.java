import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Tombstone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CardSkeleton extends Card
{
    private String EFFECT = "Summon 4 Skeleton at board corner";
    private int CARD_NUM = 2;
    
    private boolean prepared;
    
    public CardSkeleton(int slotNum){
        super(slotNum);
        
        cardEffect = EFFECT;
        cardNum = CARD_NUM;
        prepared = false;
    }
    
    public void prepare(Me me, ArrayList<Enemy> enemys, boolean byMe){
        super.use();
        
        BackgroundWorld world = (BackgroundWorld)getWorld();
        Board board = world.getBoard();
        
        Enemy enemy;
        int position = 0;
        for(int i=0; i<4 ;i++){
            switch(i){
                case 0:
                    position = 0;
                    break;
                case 1:
                    position = 5;
                    break;
                case 2:
                    position = 30;
                    break;
                case 3:
                    position = 35;
                    break;                    
            }
            
            if(board.getIsEmpty(position)){
                enemy = new Skeleton();
                enemy.setListener(world.getManager());
                enemys.add(enemy);
                new SpawnAction(world, enemy, position);
            }
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
