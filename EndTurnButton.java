import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndTurnButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndTurnButton extends Actor
{
    private boolean isActive;
    private ClickCallbackListener listener;
    
    public EndTurnButton(boolean isActive){
        setActive(isActive);    
    }
    
    @Override
    public void act(){
        if(Greenfoot.mouseClicked(this)){
            setActive(!isActive);
            if(listener != null){
                listener.endTurnClicked(isActive);
            }
        }
    }
    
    public void setActive(boolean isActive){
        this.isActive = isActive;
        if(isActive){
            setImage("end-turn-active.png");
        } else{
            setImage("end-turn-disable.png");
        }
    }
        
    public void setListener(ClickCallbackListener listener){
        this.listener = listener;
    }
    
    public interface ClickCallbackListener{
        public void endTurnClicked(boolean isActive);
    }
}
