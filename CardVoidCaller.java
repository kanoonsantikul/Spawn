import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class CardVoidCaller here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CardVoidCaller extends Card
{
    private String EFFECT = "Replace 2 card with \'the Void\'";
    private int CARD_NUM = 1;
    
    private int cardReplace = 2;
    private boolean prepared;
    
    public CardVoidCaller(int slotNum){
        super(slotNum);
        
        cardEffect = EFFECT;
        cardNum = CARD_NUM;
        prepared = false;
    }    
    
    public void prepare(Me me, ArrayList<Enemy> enemys, boolean byMe){
        super.use();
        
        Hand hand = ((BackgroundWorld)getWorld()).getHand();
        for(int i=0; i<cardReplace; i++){
            int slotNum;
            do{
                slotNum = Greenfoot.getRandomNumber(Hand.FULL_HAND);
            }while(hand.getCard(slotNum) == null);
            Card card = new CardTheVoid(slotNum);
            card.setListener(((BackgroundWorld)getWorld()).getManager());
            hand.setCard(slotNum, card);
        }
        prepared = true;
    }
    
    public void execute(Me me, ArrayList<Enemy> enemys, boolean byMe){
        if(!prepared){
            prepare(me, enemys, byMe);
        } else{
            isActed = true;
            getWorld().removeObject(this);
        }
    }  
}
