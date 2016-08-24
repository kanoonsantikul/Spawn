import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hand{
    public static final byte ME_TYPE = 1;
    public static final byte ENEMY_TYPE = 2;
    public static final int FULL_HAND = 4;
        
    private int cardCount;
    private Card[] cards;
    private World world;
    private byte type;
    private Manager manager;
            
    public Hand(World world, byte type, Manager manager){
        cardCount = 0;
        cards = new Card[FULL_HAND];
        
        this.world = world;
        this.type = type;
        this.manager = manager;
    }
        
    public void act(){
        checkCard();
    }
    
    public Card getCard(int position){
        return cards[position];
    }
    
    private void checkCard(){
        if(cardCount < FULL_HAND){
           int slotNum = getEmptySlot();
           draw(slotNum);
        }
    }
    
    public void draw(int slotNum){
        int cardNum = Greenfoot.getRandomNumber(DeckReference.MAX_CARD);
        Card card = DeckReference.getCard(cardNum, slotNum);
        int cardY = getCardY(slotNum, card);
        int cardX = getCardX(card);
        card.setListener(manager);

        if(world != null){
            world.addObject(card, cardX, cardY);
        }
        cards[slotNum] = card;
        cardCount++;
    }
        
    private int getEmptySlot(){
        int i = 0;
        for(i=0; i<FULL_HAND; i++){
            if(cards[i] == null){
                break;
            }
        }
        return i;
    }
    
    private int getCardY(int slotNum, Card card){
        int y;
        slotNum++;
        y = (BackgroundWorld.HEIGHT * slotNum/FULL_HAND) 
                - (Card.HEIGHT/2) - 8;
        return y;
    }
    
    private int getCardX(Card card){
        int x;
        x = 26 + Card.WIDTH/2;
        if(type == ENEMY_TYPE){
            x = BackgroundWorld.WIDTH - x;
        }
        return x;
    }
}
