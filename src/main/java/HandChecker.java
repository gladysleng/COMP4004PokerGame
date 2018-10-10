
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

    //check for full house
    public boolean isFullHouse(List<Card> c) {
        if (validSize(c)) {
            sortHand(c);

            boolean c1;
            boolean c2;

            // check the case for AAA22
            c1 = c.get(0).getRank() == c.get(1).getRank() &&
                    c.get(1).getRank() == c.get(2).getRank() &&
                    c.get(3).getRank() == c.get(4).getRank();

            // check the case for AA444
            c2 = c.get(0).getRank() == c.get(1).getRank() &&
                    c.get(2).getRank() == c.get(3).getRank() &&
                    c.get(3).getRank() == c.get(4).getRank();


            return (c1 || c2);
        }

        return false;
    }

    //check flush
    public boolean isFlush(List<Card> c) {
        if (validSize(c)) {
            sortSuit(c);
            if (c.get(0).getSuit() == c.get(c.size() - 1).getSuit()) {
                return true;
            }
        }
        return false;
    }

    //check straight flush
    public boolean isStraightFlush(List<Card> c) {
        if (validSize(c)) {
            sortHand(c);

                    //check if they have the same suit
                    for (int i = 0; i < c.size() - 1; i++) {
                        if ((c.get(i + 1).getSuit()) != ((c.get(i).getSuit()))) {
                            return false;
                        }
                    }
                    return true;

        }
        return false;
    }


    //check four of a kind
    public boolean isFourOfAKind(List<Card> c) {

        if (validSize(c)) {
            sortHand(c);

            boolean t1, t2;

            //DAAAA
            t1 = (c.get(0).getRank() != c.get(1).getRank())
                    && (c.get(1).getRank() == c.get(c.size() - 1).getRank());

            //AAAAD
            t2 = (c.get(0).getRank() != c.get(c.size() - 1).getRank())
                    && (c.get(0).getRank() == c.get(3).getRank());
            return (t2 || t1);

        }
        return false;
    }

}