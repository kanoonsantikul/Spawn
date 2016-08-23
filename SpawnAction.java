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
        Board board = world.getBoard();
        
        int position = 0;
        do{
            position = Greenfoot.getRandomNumber(Board.BOARD_NUM);
        }while(!board.getEmptiness(position));
        
        world.addObject(actor,
                board.getTileX(position), 
                board.getTileY(position));
        actor.drawStateText();
            
        board.setEmptiness(position, false);
        actor.setPosition(position);
    }
}
