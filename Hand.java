import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hand{
    public static final int FULL_HAND = 8;
    
    private World world;
    private Manager manager;
    
    private Card[] cards;
    private int cardCount;
            
    public Hand(World world, Manager manager){
        cardCount = 0;
        cards = new Card[FULL_HAND];
        
        this.world = world;
        this.manager = manager;
        
        checkCard();
    }
    
    public void notifyCardUse(int slotNum, int diff){
        cardCount += diff;
        cards[slotNum] = null;
    }
    
    public void setCard(int slotNum, Card card){
        if(cards[slotNum] != null){
            world.removeObject(cards[slotNum]);
        }
        
        if(card == null){
            int cardNum = Greenfoot.getRandomNumber(DeckReference.MAX_CARD);
            card = DeckReference.getCard(cardNum, slotNum);
            card.setListener(manager);
        }
        
        int cardY = getCardY(slotNum);
        int cardX = getCardX(slotNum);
        if(world != null){
            world.addObject(card, cardX, cardY);
        }      
        cards[slotNum] = card;
    }
    
    public void checkCard(){
        for(int i=0; i<FULL_HAND; i++){
            int slot = getEmptySlot();
            if(slot != -1){
                setCard(slot, null);
                cardCount++;
            }
        }
    }
    
    public Card getCard(int slotNum){
        return cards[slotNum];
    }
        
    private int getEmptySlot(){
        int i = 0;
        for(i=0; i<FULL_HAND; i++){
            if(cards[i] == null){
                return i;
            }
        }
        return -1;
    }
    
    private int getCardY(int slotNum){
        slotNum++;   
        if(slotNum > 4) slotNum -= 4;
        
        int y;
        y = (BackgroundWorld.HEIGHT/(FULL_HAND/2) * slotNum) 
                - (Card.HEIGHT/2) - 8;
        return y;
    }
    
    private int getCardX(int slotNum){
        int x;
        x = 26 + Card.WIDTH/2;
        if(slotNum > 3){
            x = BackgroundWorld.WIDTH - x;
        }
        return x;
    }
}
