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
    private int tempPosition;
    
    private MoveAction moveAction;
    private boolean isReachedTarget;
    
    public AttackAction(Creature from, Creature to, Board board){
        super(from);
        this.from = from;
        this.to = to;
        this.board = board;
        tempPosition = from.getPosition();
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
                to.drawHealthText();
                moveAction = new MoveAction(tempPosition, from, board);
            }
        } else{
            if(from.getIsMoved()){
                board.setEmptiness(to.getPosition(), false);
                from.setIsAttacked(true);
                from.removeAction(this);
            }
        }
    }
}
