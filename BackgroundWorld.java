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

    private Manager manager;
    private Board board;
    
    public BackgroundWorld()
    {    
        super(WIDTH, HEIGHT, CELL_SIZE); 
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
        manager = new Manager(this);
        board = new Board(this);
    }
}
