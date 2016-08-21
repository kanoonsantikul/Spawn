import greenfoot.*;

/**
 * Write a description of class Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board  
{   
    private static final int INITIAL_X = 53 + Card.WIDTH + Tile.WIDTH/2;
    private static final int INITIAL_Y = 35 + Tile.HEIGHT/2;
    private static final int BOARD_WIDTH = 6;
    private static final int BOARD_HEIGHT = 6;
     
    private Tile[][] tiles;

    public static final int BOARD_NUM = BOARD_HEIGHT * BOARD_WIDTH;
    
    public Board(World world)
    {
        tiles = new Tile[BOARD_WIDTH][BOARD_HEIGHT];
        setup(world);
    }
    
    private void setup(World world){
        
        for(int i=0; i<BOARD_WIDTH; i++){
            for(int j=0; j<BOARD_HEIGHT; j++){
                Tile tile = new Tile(i*6 + j);
                
                if((i+j)%2 == 0){
                    tile.setType(Tile.GREEN_TYPE);
                } else{
                    tile.setType(Tile.WHITE_TYPE);
                }
                
                world.addObject(tile, 
                        INITIAL_X + (Tile.WIDTH * j), 
                        INITIAL_Y + (Tile.HEIGHT * i));
                tiles[i][j] = tile;
            }
        }
        
    }
   
    private Tile getTile(int tileNum){
        int i = tileNum/6;
        int j = tileNum%6;
        return tiles[i][j];
    }
    
    public int getTileX(int tileNum){
        return getTile(tileNum).getX();
    }
    
    public int getTileY(int tileNum){
        return getTile(tileNum).getY();
    }
    
    public boolean getEmptiness(int tileNum){
        return getTile(tileNum).getEmptiness();
    }
    
    public void setEmptiness(int tileNum, boolean isEmpty){
        getTile(tileNum).setEmptiness(isEmpty);
    }
}
