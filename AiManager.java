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
    private static ArrayList<Tile> tiles;
    
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
    
    private static void attack(Creature actor){
        tiles = board.getPossibleRange(actor);
        board.showPossibleRange(tiles);
        Greenfoot.delay(300);
        board.hidePossibleRange(tiles);
                    
        for(int i=0; i<tiles.size(); i++){
            if(tiles.get(i).getPosition() == me.getPosition()){
                new AttackAction(actor, me, board);
                return;
            }
        }
        
        actor.setIsAttacked(true);
    }
    
    private static void move(Creature actor){
        tiles = board.getPossibleRange(actor);
        board.showPossibleRange(tiles);
        Greenfoot.delay(300);
        board.hidePossibleRange(tiles);
    
        int position;
        do{
            position = Greenfoot.getRandomNumber(tiles.size());
        }while(!board.getEmptiness(tiles.get(position).getPosition()));
                    
        new MoveAction(tiles.get(position).getPosition(), actor, board);
    }
    
    public static void setListener(OnExecutionCompleteListener listener){
        AiManager.listener = listener;
    }
        
    public interface OnExecutionCompleteListener{
        public void onExecutionComplete();
    }
}
