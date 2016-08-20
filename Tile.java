import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tile extends Actor{
    public static final int WIDTH = 95;
    public static final int HEIGHT = 95;

    public static final byte GREEN_TYPE = 0;
    public static final byte WHITE_TYPE = 1;

    private byte type;
    private boolean isEmpty = true;

    public Tile(byte type){
        setType(type);
    }

    public Tile(){
    }

    public void setType(byte type){
        this.type = type;
        if(type == GREEN_TYPE){
            setImage("green-tile.jpg");
        } else if(type == WHITE_TYPE){
            setImage("white-tile.jpg");
        }
    }

    public boolean getEmptiness(){
        return isEmpty;
    }

    public void setEmptiness(boolean isEmpty){
        this.isEmpty = isEmpty;
    }
}
