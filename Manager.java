import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Manager implements 
        EndTurnButton.ClickCallbackListener,
        Tile.ClickCallbackListener
{    
    private BackgroundWorld world;
    private boolean isMyTurn = true;
    
    private Creature me;
    private Creature[] enemys;
    
    public Manager(BackgroundWorld world){
        this.world = world;
        
        world.getEndTurnButton().setListener(this);
        Board board = world.getBoard();
        for(int i=0; i<Board.BOARD_NUM; i++){
            board.getTile(i).setListener(this);
        }
        
        me = new Me();
        new SpawnAction(world, me);
        
        enemys = new Enemy[3];
        for(int i=0; i<3; i++){
            Enemy enemy = new Enemy();
            enemys[i] = enemy;
            new SpawnAction(world, enemy);
        }
    }
    
    public void act(){
        me.act();
        if(!isMyTurn){
            Board board = world.getBoard();
            int i = Greenfoot.getRandomNumber(3);
            if(!enemys[i].hasAction()){
                int position;
                do{
                    position = Greenfoot.getRandomNumber(Board.BOARD_NUM);
                }while(!board.getEmptiness(position));
                new MoveAction(position, enemys[i], board);
            }
        }
    }
    
    @Override
    public void endTurnClicked(boolean isActive){
        isMyTurn = isActive;
    }
    
    @Override
    public void tileClicked(int position){
        if(!me.hasAction() && isMyTurn){
            new MoveAction(position, me, world.getBoard());
        }
    }
}