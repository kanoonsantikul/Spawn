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
        AiManager.OnExecutionCompleteListener,
        Card.ClickEventListener,
        CardQueue.QueueListener
{  
    private static final int STARTER_ENEMY = 5;
    
    private BackgroundWorld world;
    private boolean isMyTurn = true;
    private int myCardUseCount;
    private int playAbleCard = 2;
    
    private AiManager aiManager;
    private CardQueue cardQueue;
    private Me me;
    private ArrayList<Enemy> enemys;
    
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
        
        enemys = new ArrayList<Enemy>();
        for(int i=0; i<STARTER_ENEMY; i++){
            Enemy enemy = new Minion();
            enemys.add(enemy);
            enemy.setListener(this);
            new SpawnAction(world, enemy);
        }
        
        aiManager = new AiManager(board, me);
        aiManager.setListener(this);
        
        cardQueue = new CardQueue();
        cardQueue.setListener(this);
        
        setupStartTurn();
    }
    
    @Override 
    public void onCardLeftClicked(Card card){
        if(myCardUseCount < playAbleCard){
            cardQueue.add(card);
            myCardUseCount++;
            world.getHand().notifyCardUse(card.getSlotNum(), -1);
        }
    }
    
    @Override 
    public void onCardRightClicked(int slotNum){
        
    }
    
    @Override
    public void onEndTurnClicked(){
        world.getBoard().hidePossibleRange(me);
        
        Greenfoot.delay(200);
        isMyTurn = false;
        for(int i=0; i<playAbleCard; i++){
            useCard();
        }
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
            if(enemys.size() == 0){
                world.repaint();
                Greenfoot.setWorld(new GameOver(world.getScore().getScore(), true));
            }
        } else if(actor instanceof Me){
            world.repaint();
            Greenfoot.setWorld(new GameOver(world.getScore().getScore(), false));
        }
    }
    
    @Override
    public void onExecutionComplete(){
        setupStartTurn();
        world.getScore().addScore();
    }
    
    @Override
    public void onQueueComplete(boolean byMe){
        if(!byMe){
            aiManager.execute(enemys, world.getHand());
        }
    }
    
    private void useCard(){
        int slotNum;
        Card card;
        do{
            slotNum = Greenfoot.getRandomNumber(Hand.FULL_HAND);
            card = world.getHand().getCard(slotNum);
        }while(card == null);
        cardQueue.add(card);
        world.getHand().notifyCardUse(slotNum, -1);
    }
    
    public void act(){
        if((!me.getIsMoved() || !me.getIsAttacked())
                && isMyTurn
                && !me.hasAction()){
            world.getBoard().showPossibleRange(me);
        }
        cardQueue.act(me, enemys, isMyTurn);
        aiManager.act();
    }

    private void setupStartTurn(){
        world.getEndTurnButton().setActive(true);
        isMyTurn = true;
        me.setIsMoved(false);
        me.setIsAttacked(false);
        myCardUseCount = 0;
        
        if(world.getHand() != null){
            world.getHand().checkCard();
        }
    }
}