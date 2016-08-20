/**
 * class to controll world behavior
 * 
 * @author Niti Santikul 
 * @version 0.001
 */

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BackgroundWorld extends World
{
    public static final int worldWidth = 960; //960
    public static final int worldHeight = 640; //640
    public static final int worldCellSize = 1;

    private Manager manager;
    
    public BackgroundWorld()
    {    
        super(worldWidth, worldHeight, worldCellSize); 
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
    private void prepare()
    {
        Board board = new Board();
        addObject(board, worldWidth/2, worldHeight/2);
        
        manager = new Manager(this);
    }
}
