import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CardThunderBolt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CardMiniPotion extends Card
{
    private String EFFECT = "HP + 2";
    private int CARD_NUM = 1;
    
    public CardMiniPotion(int slotNum){
        super(slotNum);
        
        cardEffect = EFFECT;
        cardNum = CARD_NUM;
    }
}
