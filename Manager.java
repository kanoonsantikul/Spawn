import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Manager implements 
        EndTurnButton.ClickCallbackListener,
        Tile.ClickCallbackListener,
        AiManager.OnExecutionCompleteListener
{    
    private BackgroundWorld world;
    private boolean isMyTurn = true;
    
    private Me me;
    private ArrayList<Creature> enemys;
    
    public Manager(BackgroundWorld world){
        this.world = world;
        
        world.getEndTurnButton().setListener(this);
        Board board = world.getBoard();
        for(int i=0; i<Board.BOARD_NUM; i++){
            board.getTile(i).setListener(this);
        }
        
        me = new Me();
        new SpawnAction(world, me);
        
        enemys = new ArrayList<Creature>();
        for(int i=0; i<3; i++){
            Enemy enemy = new Enemy();
            enemys.add(enemy);
            new SpawnAction(world, enemy);
        }
        
        AiManager.init(board, me);
        AiManager.addCreature(enemys);
        AiManager.setListener(this);
        
        setupStartTurn();
    }
    
    @Override
    public void endTurnClicked(){
        ArrayList<Tile> tiles = world.getBoard().getPossibleRange(me);
        world.getBoard().hidePossibleRange(tiles);
        
        isMyTurn = false;
        AiManager.execute();

    }
    
    @Override
    public void tileClicked(int position){
        if(!me.hasAction() && isMyTurn && !me.getIsMoved()){
            ArrayList<Tile> tiles = world.getBoard().getPossibleRange(me);
            for(int i=0 ;i<tiles.size(); i++){
                if(tiles.get(i).getPosition() == position){
                    world.getBoard().hidePossibleRange(tiles);
                    new MoveAction(position, me, world.getBoard());
                }
            }
        }
    }
    
    @Override
    public void onExecutionComplete(){
        setupStartTurn();
    }
    
    public void act(){
        AiManager.act();
    }

    private void setupStartTurn(){
        world.getEndTurnButton().setActive(true);
        isMyTurn = true;
        me.setIsMoved(false);
        ArrayList<Tile> tiles = world.getBoard().getPossibleRange(me);
        world.getBoard().showPossibleRange(tiles);
    }
}