import java.util.ArrayList;
import java.util.List;

public class Player {

    private final static int MAX_CARD = 5;
    private String name;
    private int handRank;
    private List<Card> hand;


    public Player(String name){
        this.name = name;
        this.handRank = 0 ;
        this.hand = new ArrayList<Card>();
    }

    public void setHand(List<Card>cardsFromInput){
        this.hand = cardsFromInput;
    }

    public List<Card> getHand(){
        return hand;
    }

    public int handSize(){
        return hand.size();
    }


    public void discardCard(Card c){
        hand.remove(c);
    }

    public void setHandRank(int value){
        this.handRank = value;
    }

    public int getHandRank(){
        return handRank;
    }

    public void printHand(){
        String handString = name + "  : ";
        for(int i = 0 ; i < handSize(); i ++){
            handString += hand.get(i).toString() + " ";
        }
        System.out.println(handString.trim());
    }
}
