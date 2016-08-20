import greenfoot.*;

/**
 * Write a description of class Action here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Action{
    public void spawn(World world, Actor actor){
        Board board = ((BackgroundWorld)world).getBoard();
        
        int position = 0;
        do{
            position = Greenfoot.getRandomNumber(Board.BOARD_NUM);
        }while(!board.getEmptiness(position));
        
        world.addObject(actor,
            board.getTileX(position), 
            board.getTileY(position));
        board.setEmptiness(position, false);
    }
}
