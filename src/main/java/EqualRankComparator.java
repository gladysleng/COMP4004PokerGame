import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class EqualRankComparator {
    private HandChecker handChecker = new HandChecker();

    public List<Card> compareRoyalFlush(List<Card> c1,List<Card>c2){
        if(c1.get(0).getSuit() > c2.get(0).getSuit()){
            return c1;
        }
        else{
            return c2;
        }
    }
    public List<Card> compareStraightFlush(List<Card>c1, List<Card>c2){
        handChecker.sortHand(c1);
        handChecker.sortHand(c2);

        if(c1.get(c1.size()-1).getRank() == c2.get(c2.size()-1).getRank()){
            if(c1.get(c1.size()-1).getSuit() > c2.get(c2.size() - 1).getSuit()){
                return c1;
            }
            else{
                return c2;
            }
        }
        else{
            if(c1.get(c1.size()-1).getRank() > c2.get(c2.size()-1).getRank()){
                return c1;
            }
            else{
                return c2;
            }
        }
    }

    private List<Card> compareCenterCard(List<Card>c1,List<Card>c2){
        handChecker.sortHand(c1);
        handChecker.sortHand(c1);

        if (c1.get(2).getRank() > c2.get(2).getRank()) {
            return c1;
        } else {
            return c2;
        }
    }

    public List<Card> compareFourOfAKind(List<Card>c1, List<Card>c2){
        return compareCenterCard(c1,c2);

    }
    public List<Card> compareFullHouse(List<Card> c1, List<Card> c2) {
        return compareCenterCard(c1,c2);

    }
}
