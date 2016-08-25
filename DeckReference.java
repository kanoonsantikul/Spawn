/**
 * Write a description of class Deck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DeckReference
{
    public static int MAX_CARD = 6;
    
    public static Card getCard(int cardNum, int slotNum){
        switch(cardNum){
            case 0:
                return new CardThunderBolt(slotNum);
            case 1:
                return new CardMiniPotion(slotNum);
            case 2:
                return new CardGuardKeeper(slotNum);
            case 3:
                return new CardHurricane(slotNum);
            case 4:
                return new CardSkeleton(slotNum);
            case 5:
                return new CardMythDragon(slotNum);
            default:
                return new Card(slotNum);
        }
    }
}
