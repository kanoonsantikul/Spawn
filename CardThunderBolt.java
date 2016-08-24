import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CardThunderBolt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CardThunderBolt extends Card
{
    private String EFFECT = "deal 3 damage to all opponent";
    private int CARD_NUM = 0;
    
    public CardThunderBolt(int slotNum){
        super(slotNum);
        
        cardEffect = EFFECT;
        cardNum = CARD_NUM;
    }
}
