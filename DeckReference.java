/**
 * Write a description of class Deck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DeckReference
{
    public static int MAX_CARD = 2;
    
    public static Card getCard(int cardNum, int slotNum){
        switch(cardNum){
            case 0:
                return new CardThunderBolt(slotNum);
            case 1:
                return new CardMiniPotion(slotNum);
            default:
                return new Card(slotNum);
        }
    }
}
