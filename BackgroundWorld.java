/**
 * class to controll world behavior
 * 
 * @author Niti Santikul 
 * @version 0.001
 */

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BackgroundWorld extends World
{
    public static final int WIDTH = 960; //960
    public static final int HEIGHT = 640; //640
    public static final int CELL_SIZE = 1;

    private Board board;
    private Manager manager;
    private EndTurnButton endTurnButton;
    private Hand myHand;
    private Hand enemyHand;

    public BackgroundWorld()
    {    
        super(WIDTH, HEIGHT, CELL_SIZE); 
        prepare();
    }
    
    public void act(){
        if(myHand != null) myHand.act();
        if(enemyHand != null) enemyHand.act();
        
        if(manager != null){
            manager.act();
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {   
        board = new Board(this);
        
        myHand = new Hand(this, Hand.ME_TYPE);
        enemyHand = new Hand(this, Hand.ENEMY_TYPE);
        
        endTurnButton = new EndTurnButton(true);
        addObject(endTurnButton, 760, 40);
        
        manager = new Manager(this);
    }
    
    public Board getBoard(){
        return board;
    }
    
    public EndTurnButton getEndTurnButton(){
        return endTurnButton;
    }
}
