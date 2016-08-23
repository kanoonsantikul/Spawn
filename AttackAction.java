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
               
                to.changeHealth(-1*from.getAttack());
                if(to.getHealth() < 1){
                    to.kill();
                }
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
