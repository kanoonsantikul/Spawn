import greenfoot.*;
import java.util.ArrayList;

/**
 * Write a description of class AiManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AiManager  
{
    private Board board;
    private Hand hand;
    private Me me;
    private ArrayList<Enemy> enemys;
    
    private int enemyCardUseCount;
    private int queue;
    private boolean isExecuting = false;
    private OnExecutionCompleteListener listener;
    private ArrayList<Tile> tiles;
    
    public AiManager(Board board, Me me){
        this.board = board;
        this.me = me;
        enemys = new ArrayList<Enemy>();
    }
    
    public void execute(ArrayList<Enemy> enemys, Hand hand){
        this.enemys = enemys;
        this.hand = hand;
        
        for(int i=0; i<enemys.size(); i++){
            enemys.get(i).setIsMoved(false);
            enemys.get(i).setIsAttacked(false);
        }
        isExecuting = true;
        queue = 0;
        enemyCardUseCount = 0;
    }
    
    public void act(){
        if(isExecuting){
            Creature actor = enemys.get(queue);
            if(enemyCardUseCount < 2){
                for(int i=0; i<2; i++){
                    drawCard();
                }
            }
            if(!actor.getIsMoved()){
                if(!actor.hasAction()){
                    move(actor);
                }
            } 
            else if(!actor.getIsAttacked()){               
                if(!actor.hasAction()){
                    attack(actor);
                }
            }
            else{
                queue++;
                Greenfoot.delay(100);
            }
            
            if(queue == enemys.size()){
                isExecuting = false;
                if(listener != null){
                    listener.onExecutionComplete();
                }
            }
        }
    }
    
    private void drawCard(){
        Greenfoot.delay(200);
        enemyCardUseCount++;
        
        int slotNum;
        do{
            slotNum = Greenfoot.getRandomNumber(Hand.FULL_HAND);
        }while(hand.getCard(slotNum) == null);
        
        Card card = hand.getCard(slotNum);
        card.use(me, enemys, board, false);
        hand.notifyCardUse(slotNum, -1);
    }
    
    private void attack(Creature actor){
        tiles = board.getPossibleRange(actor);
      
        for(int i=0; i<tiles.size(); i++){
            if(tiles.get(i).getPosition() == me.getPosition()){
                board.showPossibleRange(actor);
                Greenfoot.delay(400);
                board.hidePossibleRange(actor);    
                
                new AttackAction(actor, me, board);
                return;
            }
        }
        
        actor.setIsAttacked(true);
    }
    
    private void move(Creature actor){
        tiles = board.getPossibleRange(actor);
        board.showPossibleRange(actor);
        Greenfoot.delay(300);
        board.hidePossibleRange(actor);
    
        int position;
        do{
            position = Greenfoot.getRandomNumber(tiles.size());
        }while(!board.getIsEmpty(tiles.get(position).getPosition()));
                    
        new MoveAction(tiles.get(position).getPosition(), actor, board);
    }
    
    public void setListener(OnExecutionCompleteListener listener){
        this.listener = listener;
    }
        
    public interface OnExecutionCompleteListener{
        public void onExecutionComplete();
    }
}
