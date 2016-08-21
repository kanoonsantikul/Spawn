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
    
    private Action action;
    private Creature me;
    
    public Manager(BackgroundWorld world){
        myHand = new Hand(world, Hand.ME_TYPE);
        enemyHand = new Hand(world, Hand.ENEMY_TYPE);
        
        //add action controll
        action = new Action(world);
        
        me = new Me();
        action.spawn(world, me);
        
        for(int i=0; i<3; i++){
            action.spawn(world, new Enemy());
        }
    }
    
    public void act(){
        if(myHand != null) myHand.act();
        if(enemyHand != null) enemyHand.act();
        
        
        if(Greenfoot.mouseClicked(null)){
            Actor actor = Greenfoot.getMouseInfo().getActor();
            if(actor != null && actor instanceof Tile && !action.isActing()){
                Tile tile = (Tile)actor;
                System.out.println("Cliked" + tile.getPosition());
                action.moveTo(tile.getPosition(), me);
            } 
        }
        
        action.animate();
    }
}

