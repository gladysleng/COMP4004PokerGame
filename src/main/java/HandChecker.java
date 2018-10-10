
import java.util.List;

import java.util.Collections;
import java.util.Comparator;

public class HandChecker {

    private int TWO_OF_A_KIND = 2;
    private int FOUR_OF_A_KIND = 4;
    private int THREE_OF_A_KIND = 3;

    public boolean validSize(List<Card> c) {

        return c.size() == 5;
    }

    // sort the hand in ascending order according to rank
    public void sortHand(List<Card> c) {
        Collections.sort(c, new Comparator<Card>() {
            public int compare(Card c1, Card c2) {

                int result = c1.getRank() - c2.getRank();

                if (result != 0) {
                    return result;

                } else { // if their rank are equal, compare the suit
                    int result2 = c1.getSuit() - c2.getSuit();
                    return result2;
                }

            }
        });
    }

    // sort suit according to suit rank
    public void sortSuit(List<Card> c) {
        Collections.sort(c, new Comparator<Card>() {
            public int compare(Card card1, Card card2) {
                return card1.getSuit() - card2.getSuit();
            }
        });
    }

}