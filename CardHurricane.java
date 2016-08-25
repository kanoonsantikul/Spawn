import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class KeeperAlarm here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CardHurricane extends Card
{
    private String EFFECT = "Change position of all character";
    private int CARD_NUM = 2;
    
    private boolean prepared;
    private ArrayList<Creature> actors;
    private boolean[] movedTemp;
    private int queue;
    
    public CardHurricane(int slotNum){
        super(slotNum);
        
        cardEffect = EFFECT;
        cardNum = CARD_NUM;
        
        prepared = false;
    }
    
    public void prepare(Me me, ArrayList<Enemy> enemys, boolean byMe){
        super.use();
        
        actors = new ArrayList<Creature>();
        actors.addAll(enemys);
        actors.add(me);
        queue = 0;
        
        movedTemp = new boolean[actors.size()];
        for(int i=0; i<actors.size(); i++){
            movedTemp[i] = actors.get(i).getIsMoved();
            actors.get(i).setIsMoved(false);
        }
        
        prepared = true;
    }
    
    public void execute(Me me, ArrayList<Enemy> enemys, boolean byMe){
        if(!prepared){
            prepare(me, enemys, byMe);
        } else{
            ((BackgroundWorld)getWorld())
                    .getBoard()
                    .hidePossibleRange(me);
            
            if(actors.size() != 0){
                Creature actor = actors.get(0);
                if(!actor.getIsMoved() && !actor.hasAction()){
                    Board board = ((BackgroundWorld)getWorld()).getBoard();
                    int target;
                    do{
                        target = Greenfoot.getRandomNumber(Board.BOARD_NUM);
                    }while(!board.getIsEmpty(target));
                    
                    new MoveAction(target, actor, board);
                } else if(actor.getIsMoved()){
                    actor.setIsMoved(movedTemp[queue]);
                    actors.remove(0);
                    queue++;
                }
            } else{
                isActed = true;
                getWorld().removeObject(this);
            }
        }
    }
}
