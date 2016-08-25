import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.ArrayList;

/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card extends Actor
{
    public static final int WIDTH = 141;
    public static final int HEIGHT = 145;
    
    protected String cardEffect = "test";
    protected Text effectText;
    protected int slotNum;
    protected int cardNum;
    protected ClickEventListener listener;
    
    public Card(int slotNum){
        this.slotNum = slotNum;
    }
    
    public void act() 
    {
        if(Greenfoot.mousePressed(this)){
            int button = Greenfoot.getMouseInfo().getButton();
            if(button == 3){
                showCardEffect(true);
            }
        }
        if(Greenfoot.mouseClicked(this) && listener != null){
            int button = Greenfoot.getMouseInfo().getButton();
            if(button == 1){
                listener.onCardLeftClicked(this);
            } else if(button == 3){
                showCardEffect(false);
            }
        }
    }
    
    public int getSlotNum(){
        return slotNum;
    }
    
    public void setListener(ClickEventListener listener){
        this.listener = listener;
    }
    
    public int getCardNum(){
        return cardNum;
    }
    
    public void showCardEffect(boolean show){
        if(show){
            if(effectText == null){
                effectText = new Text(cardEffect, 35, Color.WHITE, new Color(0,0,0,180));
            }
            getWorld().addObject(effectText, 
                    BackgroundWorld.WIDTH/2, 
                    BackgroundWorld.HEIGHT/2);
        } else{ 
            getWorld().removeObject(effectText);
        }
    }
    
    public void showFullCard(){
        setLocation(BackgroundWorld.WIDTH/2, BackgroundWorld.HEIGHT/2);
        Greenfoot.delay(550);
    }
    
    public void use(Me me, ArrayList<Enemy> enemys, Board board, boolean byMe){
        showFullCard();
        getImage().setTransparency(0);
    }
    
    public interface ClickEventListener{
        public void onCardLeftClicked(Card card);
        public void onCardRightClicked(int slotNum);
    }
}
