import greenfoot.*;

/**
 * Write a description of class Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board  
{
    private final int INITIAL_X = 53 + Card.WIDTH + Tile.WIDTH/2;
    private final int INITIAL_Y = 35 + Tile.HEIGHT/2;
    private final int BOARD_WIDTH = 6;
    private final int BOARD_HEIGHT = 6;
     
    private Tile[][] tiles;
    
    public Board(World world)
    {
        tiles = new Tile[BOARD_WIDTH][BOARD_HEIGHT];
        setup(world);
    }
    
    private void setup(World world){
        
        for(int i=0; i<BOARD_WIDTH; i++){
            for(int j=0; j<BOARD_HEIGHT; j++){
                Tile tile = new Tile();
                if((i+j)%2 == 0){
                    tile.setType(Tile.GREEN_TYPE);
                } else{
                    tile.setType(Tile.WHITE_TYPE);
                }
                world.addObject(tile, 
                        INITIAL_X + (Tile.WIDTH * j), 
                        INITIAL_Y + (Tile.HEIGHT * i));
            }
        }
        
    }
}
