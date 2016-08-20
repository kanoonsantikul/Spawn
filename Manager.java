import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Manager
{
    private Hand myHand;
    private Hand enemyHand;
    
    private Me me;
    private Action action;
    
    public Manager(World world){
        myHand = new Hand(world, Hand.ME_TYPE);
        enemyHand = new Hand(world, Hand.ENEMY_TYPE);
        
        action = new Action();
        
        me = new Me();
        action.spawn(world, me);
        
        Enemy en1 = new Enemy();
        action.spawn(world, en1);
        Enemy en2 = new Enemy();
        action.spawn(world, en2);
        Enemy en3 = new Enemy();
        action.spawn(world, en3);
    }
    
    public void act(){
        if(myHand != null) myHand.act();
        if(enemyHand != null) enemyHand.act();
    }
}

