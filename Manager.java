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
        Enemy.EventListener,
        AiManager.OnExecutionCompleteListener
{    
    private BackgroundWorld world;
    private boolean isMyTurn = true;
    
    private AiManager aiManager;
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
        me.setListener(this);
        new SpawnAction(world, me);
        
        enemys = new ArrayList<Creature>();
        for(int i=0; i<4; i++){
            Enemy enemy = new Enemy();
            enemys.add(enemy);
            enemy.setListener(this);
            new SpawnAction(world, enemy);
        }
        
        aiManager = new AiManager(board, me);
        aiManager.addCreature(enemys);
        aiManager.setListener(this);
        
        setupStartTurn();
    }
    
    @Override
    public void onEndTurnClicked(){
        world.getBoard().hidePossibleRange(me);
        
        isMyTurn = false;
        aiManager.execute();
    }
    
    @Override
    public void onTileClicked(int position){
        if(!me.hasAction() && isMyTurn && !me.getIsMoved()){
            ArrayList<Tile> tiles = world.getBoard().getPossibleRange(me);
            for(int i=0 ;i<tiles.size(); i++){
                if(tiles.get(i).getPosition() == position){
                    world.getBoard().hidePossibleRange(me);
                    new MoveAction(position, me, world.getBoard());
                }
            }
        }
    }
    
    @Override
    public void onCreatureClicked(Creature actor){
        if(actor instanceof Enemy
                && !me.hasAction() 
                && isMyTurn 
                && !me.getIsAttacked()){
            ArrayList<Tile> tiles = world.getBoard().getPossibleRange(me);
            for(int i=0 ;i<tiles.size(); i++){
                if(tiles.get(i).getPosition() == actor.getPosition()){
                    world.getBoard().hidePossibleRange(me);
                    new AttackAction(me, actor, world.getBoard());
                }
            }    
        }
    }
    
    @Override 
    public void onCreatureKilled(Creature actor){
        if(actor instanceof Enemy){
            enemys.remove(actor);
            aiManager.removeCreature(actor);
        } else if(actor instanceof Me){
            world.repaint();
            Greenfoot.setWorld(new GameOver(world.getScore().getScore()));
        }
    }
    
    @Override
    public void onExecutionComplete(){
        setupStartTurn();
        world.getScore().addScore();
    }
    
    public void act(){
        if((!me.getIsMoved() || !me.getIsAttacked())
                && isMyTurn
                && !me.hasAction()){        
            
            world.getBoard().showPossibleRange(me);
        }
        aiManager.act();
    }

    private void setupStartTurn(){
        world.getEndTurnButton().setActive(true);
        isMyTurn = true;
        me.setIsMoved(false);
        me.setIsAttacked(false);
    }
}