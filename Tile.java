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
    private int position;
    private ClickCallbackListener listener;

    public Tile(byte type, int position){
        setType(type);
        this.position = position;
    }

    public Tile(int position){
        this.position = position;
    }
    
    @Override
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            if(listener != null){
                listener.tileClicked(position);
            }
        }
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
    
    public int getPosition(){
        return position;
    }
    
    public void setListener(ClickCallbackListener listener){
        this.listener = listener;
    }
    
    public interface ClickCallbackListener{
        public void tileClicked(int position);
    }
}
