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
    public static final byte GREEN_ALPHA_TYPE = 2;
    public static final byte WHITE_ALPHA_TYPE = 3;
    
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
                listener.onTileClicked(position);
            }
        }
    }
    
    public byte getType(){
        return type;
    }

    public void setType(byte type){
        this.type = type;
        
        switch(type){
            case GREEN_TYPE:
                setImage("green-tile.jpg");
                break;
            case WHITE_TYPE:
                setImage("white-tile.jpg");
                break;
            case GREEN_ALPHA_TYPE:
                setImage("white-tile-alpha.png");
                break;
            case WHITE_ALPHA_TYPE:
                setImage("green-tile-alpha.png");
                break;
        }
    }

    public boolean getIsEmpty(){
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty){
        this.isEmpty = isEmpty;
    }
    
    public int getPosition(){
        return position;
    }
    
    public void setListener(ClickCallbackListener listener){
        this.listener = listener;
    }
    
    public interface ClickCallbackListener{
        public void onTileClicked(int position);
    }
}
