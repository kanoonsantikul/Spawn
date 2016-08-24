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
    protected EffectText effectText;
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
                listener.onCardLeftClicked(slotNum);
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
                effectText = new EffectText(cardEffect);
            }
            getWorld().addObject(effectText, 
                    BackgroundWorld.WIDTH/2, 
                    BackgroundWorld.HEIGHT/2);
        } else{ 
            getWorld().removeObject(effectText);
        }
    }
    
    public void use(Me me, ArrayList<Enemy> enemys, Board board){
    }
    
    public interface ClickEventListener{
        public void onCardLeftClicked(int slotNum);
        public void onCardRightClicked(int slotNum);
    }
    
    public class EffectText extends Actor{
        public EffectText(String text){
            GreenfootImage textImage 
                    = new GreenfootImage(text, 35, Color.WHITE, new Color(0,0,0,180));
            setImage(textImage);
        }
    }
}
