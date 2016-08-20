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
    
    private final int fullHand = 4;
        
    private int cardNum;
    private Card[] cards;
    private World world;
    private byte type;
            
    public Hand(World world, byte type){
        cardNum = 0;
        cards = new Card[4];
        
        this.world = world;
        this.type = type;
    }
        
    public void act(){
        checkCard();
    }
    
    private void checkCard(){
        if(cardNum < 4){
           int slotNum = getSlot();
           draw(slotNum);
        }
    }
    
    public void draw(int slotNum){
        Card card = new Card();
        int cardY = getCardY(slotNum, card);
        int cardX = getCardX(card);

        if(world != null){
            world.addObject(card, cardX, cardY);
        }
        cards[slotNum] = card;
        cardNum++;
    }
        
    private int getSlot(){
        int i = 0;
        for(i=0; i<fullHand; i++){
            if(cards[i] == null){
                break;
            }
        }
        return i;
    }
        
    private int getCardY(int slotNum, Card card){
        int y;
        slotNum++;
        y = (BackgroundWorld.HEIGHT * slotNum/fullHand) 
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
