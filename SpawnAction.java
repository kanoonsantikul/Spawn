import greenfoot.*;

/**
 * Write a description of class SpawnAction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpawnAction extends Action 
{
    public SpawnAction(BackgroundWorld world, Creature actor){ 
        this(world, actor, findPossiblePosition(world));
    }
    
    public SpawnAction(BackgroundWorld world, Creature actor, int position){
        Board board = world.getBoard();
        
        world.addObject(actor,
                board.getTileX(position), 
                board.getTileY(position));
            
        board.setIsEmpty(position, false);
        actor.setPosition(position);
    }
    
    private static int findPossiblePosition(BackgroundWorld world){
        Board board = world.getBoard();
        
        int position = 0;
        do{
            position = Greenfoot.getRandomNumber(Board.BOARD_NUM);
        }while(!board.getIsEmpty(position));
        return position;
    }
}
