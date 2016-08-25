import greenfoot.*;
import java.util.ArrayList;
/**
 * Write a description of class CardQueue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CardQueue
{
    private static ArrayList<Card> cardQueue;
    private int queue;
    private QueueListener listener;
    private boolean hasRun;
    
    public CardQueue(){
        cardQueue = new ArrayList<Card>();
        queue = 0;
        hasRun = false;
    }
    
    public void add(Card card){
       cardQueue.add(card);
    }
    
    public void act(Me me, ArrayList<Enemy> enemys, boolean byMe){
        if(cardQueue.size() != 0){
            hasRun = true;
            Card card = cardQueue.get(queue);
            if(!card.isActed()){
                card.execute(me, enemys, byMe);
            } else{
                cardQueue.remove(queue);
            }
        } else{
            if(listener != null && hasRun){
                listener.onQueueComplete(byMe);
                hasRun = false;
            }
        }
    }
    
    public void setListener(QueueListener listener){
        this.listener = listener;
    }
        
    public interface QueueListener{
        public void onQueueComplete(boolean byMe);
    }
}
