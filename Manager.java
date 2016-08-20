import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Manager extends Actor
{
    private Hand myHand;
    private Hand enemyHand;
    
    public Manager(World world){
        myHand = new Hand(world, Hand.ME_TYPE);
        enemyHand = new Hand(world, Hand.ENEMY_TYPE);
    }
    
    public void act(){
        if(myHand != null) myHand.act();
        if(enemyHand != null) enemyHand.act();
    }
}

