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
    private static Board board;
    private static Me me;
    private static ArrayList<Creature> creatures;
    
    private static int queue;
    private static boolean isExecuting = false;
    private static OnExecutionCompleteListener listener;
    
    public static void init(Board board, Me me){
        AiManager.board = board;
        AiManager.me = me;
        creatures = new ArrayList<Creature>();
    }
    
    public static void addCreature(Creature actor){
        creatures.add(actor);
    }
    
    public static void addCreature(ArrayList<Creature> actors){
        creatures.addAll(actors);
    }
    
    public static void execute(){
        for(int i=0; i<creatures.size(); i++){
            creatures.get(i).setIsMoved(false);
            creatures.get(i).setIsAttacked(false);
        }
        isExecuting = true;
        queue = 0;
    }
    
    public static void act(){
        if(isExecuting){
            Creature actor = creatures.get(queue);
            if(!actor.getIsMoved()){
                if(!actor.hasAction()){
                    int position;
                    do{
                        position = Greenfoot.getRandomNumber(Board.BOARD_NUM);
                    }while(!board.getEmptiness(position));
                    
                    new MoveAction(position, actor, board);
                }
            } 
            else if(!actor.getIsAttacked()){
                if(!actor.hasAction()){
                    Creature.delay(1);
                    new AttackAction(actor, me, board);
                }
            }
            else{
                queue++;
            }
            
            if(queue == creatures.size()){
                isExecuting = false;
                if(listener != null){
                    listener.onExecutionComplete();
                }
            }
        }
    }
    
    public static void setListener(OnExecutionCompleteListener listener){
        AiManager.listener = listener;
    }
        
    public interface OnExecutionCompleteListener{
        public void onExecutionComplete();
    }
}
