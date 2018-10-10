import java.util.ArrayList;
import java.util.List;


public class Strategy {
    private HandChecker handChecker = new HandChecker();

    public List<Card> changeOneCardForRoyalFlush(List<Card> c, List<Card> cardToExchange) {
        handChecker.sortSuit(c);
        List<Card> discardedCard = new ArrayList<Card>();
        // pairs lower suit
        if (c.get(0).getSuit() != c.get(2).getSuit()) {
            discardedCard.add(c.remove(0));
        }
        //pairs higher suit
        else if (c.get(c.size() - 1).getSuit() != c.get(2).getSuit()) {
            discardedCard.add(c.remove(c.size() - 1));
        } else {
            //flush but lesser than 10
            handChecker.sortHand(c);
            for (int i = 0; i < c.size(); i++) {
                if (c.get(i).getRank() < 10) {
                    discardedCard.add(c.remove(i));
                    break;
                }
            }
        }
        if (c.size() != 5) {
            c.addAll(cardToExchange);
        }
        handChecker.sortHand(c);
        return discardedCard;
    }
}
