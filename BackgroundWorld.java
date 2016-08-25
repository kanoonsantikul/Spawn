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
    private Score score;
    private Manager manager;
    private EndTurnButton endTurnButton;
    private Hand hand;
    private Hand enemyHand;

    public BackgroundWorld()
    {    
        super(WIDTH, HEIGHT, CELL_SIZE);
        Greenfoot.setSpeed(65);
        prepare();
    }
    
    public void act(){
        if(manager != null){
            manager.act();
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    public void prepare()
    {   
        board = new Board(this);

        endTurnButton = new EndTurnButton(true);
        addObject(endTurnButton, 760, 40);
        
        score = new Score(this);
        
        manager = new Manager(this);
        
        hand = new Hand(this, manager);
    }
    
    public Board getBoard(){
        return board;
    }
    
    public Score getScore(){
        return score;
    }
    
    public EndTurnButton getEndTurnButton(){
        return endTurnButton;
    }
    
    public Hand getHand(){
        return hand;
    }
}
