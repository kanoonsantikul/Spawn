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
    private Me me;
    private ArrayList<Creature> creatures;
    
    private int queue;
    private boolean isExecuting = false;
    private OnExecutionCompleteListener listener;
    private ArrayList<Tile> tiles;
    
    public AiManager(Board board, Me me){
        this.board = board;
        this.me = me;
        creatures = new ArrayList<Creature>();
    }
    
    public void addCreature(Creature actor){
        creatures.add(actor);
    }
    
    public void addCreature(ArrayList<Creature> actors){
        creatures.addAll(actors);
    }
    
    public void removeCreature(Creature actor){
        creatures.remove(actor);
    }
    
    public void execute(){
        for(int i=0; i<creatures.size(); i++){
            creatures.get(i).setIsMoved(false);
            creatures.get(i).setIsAttacked(false);
        }
        isExecuting = true;
        queue = 0;
    }
    
    public void act(){
        if(isExecuting){
            Creature actor = creatures.get(queue);
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
            
            if(queue == creatures.size()){
                isExecuting = false;
                if(listener != null){
                    listener.onExecutionComplete();
                }
            }
        }
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
        }while(!board.getEmptiness(tiles.get(position).getPosition()));
                    
        new MoveAction(tiles.get(position).getPosition(), actor, board);
    }
    
    public void setListener(OnExecutionCompleteListener listener){
        this.listener = listener;
    }
        
    public interface OnExecutionCompleteListener{
        public void onExecutionComplete();
    }
}
