import greenfoot.*;

/**
 * Write a description of class Action here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Action{
    private BackgroundWorld world;
    
    private Actor actor;
    
    private boolean isMoving = false;
    private boolean isActing = false;
    private int targetX, targetY;
    private int speedX, speedY;
    
    public Action(BackgroundWorld world){
        this.world = world;
    }
    
    public void spawn(BackgroundWorld world, Creature actor){
        Board board = world.getBoard();
        
        int position = 0;
        do{
            position = Greenfoot.getRandomNumber(Board.BOARD_NUM);
        }while(!board.getEmptiness(position));
        
        world.addObject(actor,
            board.getTileX(position), 
            board.getTileY(position));
        
        board.setEmptiness(position, false);
        actor.setPosition(position);
    }
    
    public void moveTo(int target, Creature actor){
        Board board = world.getBoard();
        targetX = board.getTileX(target);
        targetY = board.getTileY(target);
        
        speedX = speedY = Creature.SPEED;
        if(actor.getX() > targetX){
            speedX *= -1;
        }
        if(actor.getY() > targetY){
            speedY *= -1;
        }
        
        this.actor = actor;
        isMoving = true;
        isActing = true;
    }
    
    public void animate(){
        checkAction();
        if(isActing){
            if(isMoving){
                if(targetX != actor.getX()){
                    actor.setLocation(actor.getX() + speedX, actor.getY());
                }
                if(targetY != actor.getY()){
                    actor.setLocation(actor.getX(), actor.getY() + speedY);
                }
                if(actor.getX() == targetX && actor.getY() == targetY){
                    isMoving = false;
                    actor = null;
                }
            }
        }
    }
    
    public boolean isActing(){
        return isActing;
    }
    
    private void checkAction(){
        if(!isMoving){
            isActing = false;
        }
    }
}
