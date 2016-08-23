/**
 * Write a description of class AttackAction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AttackAction extends Action 
{
    private Creature from;
    private Creature to;
    private Board board;
    private int tempFromPosition;
    private int tempToPosition;
    private boolean tempIsMoved;
    
    private MoveAction moveAction;
    private boolean isReachedTarget;
    
    public AttackAction(Creature from, Creature to, Board board){
        super(from);
        this.from = from;
        this.to = to;
        this.board = board;
        tempFromPosition = from.getPosition();
        tempToPosition = to.getPosition();
        tempIsMoved = from.getIsMoved();
        from.setIsMoved(false);
    }
    
    @Override
    public void act(){
        if(moveAction == null){
            moveAction = new MoveAction(to.getPosition(), from, board);
        }
        if(!isReachedTarget){
            if(from.getIsMoved()){
                isReachedTarget = true;
                from.setIsMoved(false);
               
                to.getWorld().addObject(
                        new AttackGraphic(to, from.getAttack()),
                        to.getX(),
                        to.getY());
                moveAction = new MoveAction(tempFromPosition, from, board);
            }
        } else{
            if(from.getIsMoved()){
                board.setEmptiness(tempToPosition, false);
                from.setIsAttacked(true);
                from.setIsMoved(tempIsMoved);
                from.removeAction(this);
            }
        }
    }
}
