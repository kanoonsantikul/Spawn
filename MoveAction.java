import java.util.ArrayList;

/**
 * Write a description of class MoveAction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MoveAction extends Action 
{
    private Creature actor;
    private Creature.StateText stateText;
    private int targetX, targetY;
    private int speedX, speedY;
    
    public MoveAction(int position, Creature actor, Board board){
        super(actor);
        this.actor = actor;
        this.stateText = actor.getStateText();
        
        board.setEmptiness(actor.getPosition(), true);
        board.setEmptiness(position, false);
        actor.setPosition(position);
        
        targetX = board.getTileX(position);
        targetY = board.getTileY(position);
        speedX = speedY = Creature.SPEED;
        if(actor.getX() > targetX){
            speedX *= -1;
        }
        if(actor.getY() > targetY){
            speedY *= -1;
        }
    }
    
    @Override
    public void act(){
        if(targetX != actor.getX()){
            actor.setLocation(actor.getX() + speedX, actor.getY());
            stateText.setLocation(stateText.getX() + speedX, stateText.getY());
        }
        if(targetY != actor.getY()){
            actor.setLocation(actor.getX(), actor.getY() + speedY);
            stateText.setLocation(stateText.getX(), stateText.getY() + + speedY);
        }
        if(actor.getX() == targetX && actor.getY() == targetY){
            actor.removeAction(this);
            actor.setIsMoved(true);
        }
    }
}
