import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
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
            if (!isRoyalFlush(c)) {
                if (isStraight(c)) {
                    //check if they have the same suit
                    for (int i = 0; i < c.size() - 1; i++) {
                        if ((c.get(i + 1).getSuit()) != ((c.get(i).getSuit()))) {
                            return false;
                        }
                    }
                    return true;
                }
            }
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

    //check for straight
    public boolean isStraight(List<Card> c) {
        if (validSize(c)) {
            sortHand(c);
            if (c.get(c.size() - 1).getRank() == 14 && c.get(0).getRank() == 2) { // if Ace involve, change to rank 1
                c.get(c.size() - 1).setRank(1);
                sortHand(c);
            }
            //check if the rank are +1 of the previous one
            for (int i = 0; i < c.size() - 1; i++) {
                if ((c.get(i + 1).getRank()) != (((c.get(i)).getRank()) + 1)) {
                    if (c.get(0).getRank() == 1) {  // if not straight flush, change Ace to rank 14 again
                        c.get(0).setRank(14);
                    }
                    return false;
                }
            }

            return true;
        }
        return false;
    }


    //check three of a kind
    public boolean isThreeOfAKind(List<Card> c) {
        if (validSize(c)) {
            sortHand(c);

            if (isFullHouse(c) || isFourOfAKind(c)) {
                return false;
            }

            boolean a1, a2, a3;

            //AAA23
            a1 = c.get(0).getRank() == c.get(1).getRank() &&
                    c.get(1).getRank() == c.get(2).getRank();

            //67778
            a2 = c.get(1).getRank() == c.get(2).getRank() &&
                    c.get(2).getRank() == c.get(3).getRank();
            //67888
            a3 = c.get(2).getRank() == c.get(3).getRank() &&
                    c.get(3).getRank() == c.get(4).getRank();


            return (a1 || a2 || a3);
        }

        return false;
    }

    //check two pair
    public boolean isTwoPair(List<Card> c) {
        if (validSize(c)) {
            sortHand(c);

            if (isFullHouse(c) || isFourOfAKind(c) || isThreeOfAKind(c)) {
                return false;
            }

            boolean a1, a2, a3;

            //44566
            a1 = c.get(0).getRank() == c.get(1).getRank() &&
                    c.get(3).getRank() == c.get(4).getRank();

            //44556
            a2 = c.get(0).getRank() == c.get(1).getRank() &&
                    c.get(2).getRank() == c.get(3).getRank();

            //34455
            a3 = c.get(1).getRank() == c.get(2).getRank() &&
                    c.get(3).getRank() == c.get(4).getRank();

            return (a1 || a2 || a3);
        }

        return false;
    }

    //check one Pair
    public boolean isOnePair(List<Card> c) {
        if (validSize(c)) {
            sortHand(c);

            int counter = 1;

            for (int i = 0; i < c.size() - 1; i++) {
                if (c.get(i + 1).getRank() == c.get(i).getRank()) {
                    counter++;
                }
            }
            if (counter == TWO_OF_A_KIND) {
                return true;
            }

        }
        return false;
    }

    //check high card
    public boolean isHighCard(List<Card> c) {
        if (validSize(c)) {
            sortHand(c);

            if (!isTwoPair(c) && !isThreeOfAKind(c) && !isFourOfAKind(c)
                    && !isFullHouse(c) && !isStraight(c) && !isStraightFlush(c)
                    && !isFlush(c) && !isOnePair(c)) {
                return true;
            }
        }
        return false;
    }

    //royal flush
    public boolean isRoyalFlush(List<Card> c) {
        if (validSize(c)) {
            sortHand(c);

            boolean t;

            t = (c.get(0).getRank() == 10)
                    && (c.get(1).getRank() == 11)
                    && (c.get(2).getRank() == 12)
                    && (c.get(3).getRank() == 13)
                    && (c.get(4).getRank() == 14);

            return (t && isFlush(c));

        }
        return false;
    }


    public int getPokerRank(List<Card> c) {
        if (isRoyalFlush(c)) {
            System.out.println("Royal Flush!");
            return 10;
        } else if (isStraightFlush(c)) {
            System.out.println("Straight FLush!");
            return 9;
        } else if (isFourOfAKind(c)) {
            System.out.println("Four of A Kind!");
            return 8;
        } else if (isFullHouse(c)) {
            System.out.println("Full House!");
            return 7;
        } else if (isFlush(c)) {
            System.out.println("Flush!");
            return 6;
        } else if (isStraight(c)) {
            System.out.println("Straight!");
            return 5;
        } else if (isThreeOfAKind(c)) {
            System.out.println("Three of A Kind!");
            return 4;
        } else if (isTwoPair(c)) {
            System.out.println("Two Pairs!");
            return 3;
        } else if (isOnePair(c)) {
            System.out.println("One Pair!");
            return 2;
        } else if (isHighCard(c)) {
            System.out.println("High Card!");
            return 1;
        } else {
            return 0;
        }
    }


    public boolean rangeOfFourOrThree(int i1, int i2) {
        return i1 - i2 == 4 || i1 - i2 == 3;
    }

    public boolean oneCardFromRoyalFlush(List<Card> c) {
        if (validSize(c)) {
            sortHand(c);

            // check for pairs
            if (isOnePair(c) && rangeOfFourOrThree(c.get(c.size() - 1).getRank(), c.get(0).getRank())) {
                sortSuit(c);
                //check 1 & 4 || check 2 & 5
                if ((c.get(0).getSuit() == c.get(3).getSuit()) || (c.get(1).getSuit() == c.get(4).getSuit())) {
                    return true;
                }
                // check for first is the odd card, following is 11,12,13,14
            } else if ((!(c.get(0).getRank() >= 10 && c.get(0).getRank() <= 14))) {
                for (int i = 1; i < c.size() - 1; i++) {

                    if ((!(c.get(i).getRank() >= 10 && c.get(i).getRank() <= 14))) {
                        return false;
                    }

                    if (c.get(i).getSuit() != c.get(i + 1).getSuit()) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }


    public boolean oneCardFromFlush(List<Card> c) {
        if (validSize(c)) {
            HashMap<Integer, Integer> suitBucket = new HashMap<Integer, Integer>();
            Integer count;
            for (Card currentCard : c) {
                count = suitBucket.get(currentCard.getSuit());
                if (count == null) {
                    count = 0;
                }
                suitBucket.put(currentCard.getSuit(), ++count);
            }

            for (Map.Entry<Integer, Integer> entry : suitBucket.entrySet()) {
                if (entry.getValue() == 4) {
                    return true;
                }
            }
        }

        return false;
    }
    public boolean oneCardFromFullHouse(List<Card> c) {
        if (validSize(c)) {
            sortHand(c);

            if (isTwoPair(c) || isThreeOfAKind(c)) {
                return true;
            }
        }

        return false;
    }

    public void changeRankChangeForAce(List<Card> c) {
        sortHand(c);
        {
            int counter = 0;
            for (int i = 0; i < c.size() - 1; i++) {
                if (c.get(i).getRank() >= 2 && c.get(i).getRank() <= 5) {
                    counter++;
                }
            }
            // if other four cards is in a range of 2345
            if (counter == 4 || counter == 3) {
                c.get(c.size() - 1).setRank(1);
                sortHand(c);
            }

        }
    }

    public boolean oneCardFromStraight(List<Card> c) {
        if (validSize(c)) {
            sortHand(c);

            // case Ace but with 2345 straight
            if (c.get(c.size() - 1).getRank() == 14) {
                changeRankChangeForAce(c);
            }
            if (isOnePair(c)) {
                if (rangeOfFourOrThree((c.get(c.size() - 1).getRank()), (c.get(0).getRank()))) {
                    return true;
                }
            } else {
                if ((rangeOfFourOrThree((c.get(c.size() - 1).getRank()), (c.get(1).getRank()))) || (rangeOfFourOrThree((c.get(3).getRank()), (c.get(0).getRank())))) {
                    return true;
                }
            }
        }
        return false;

    }

    public boolean oneCardFromStraightFlush(List<Card> c) {
        if (validSize(c) && oneCardFromFlush(c)) {
            sortSuit(c);

            List<Card> clone = new ArrayList<Card>(c);

            if (c.get(0).getSuit() != c.get(2).getSuit()) {
                clone.remove(0);
            } else {
                clone.remove(clone.size() - 1);
            }

            sortHand(clone);

            //ace case
            if ((clone.get(clone.size() - 1).getRank() == 14)) {
                for (int i = 0; i < clone.size() - 1; i++) {
                    if ((!(clone.get(i).getRank() >= 2 && clone.get(i).getRank() <= 5)) || (clone.get(i).getRank() == clone.get(i + 1).getRank())) {
                        return false;
                    }
                }
                clone.get(clone.size() - 1).setRank(1);
                sortHand(clone);
            }

            if (rangeOfFourOrThree(clone.get(clone.size() - 1).getRank(), clone.get(0).getRank())) {
                return true;
            }
        }
        return false;
    }

    public boolean isThreeOfSameSuit(List<Card> c) {
        if (validSize(c)) {
            HashMap<Integer, Integer> suitBucket = new HashMap<Integer, Integer>();
            Integer count;
            for (Card currentCard : c) {
                count = suitBucket.get(currentCard.getSuit());
                if (count == null) {
                    count = 0;
                }
                suitBucket.put(currentCard.getSuit(), ++count);
            }

            for (Map.Entry<Integer, Integer> entry : suitBucket.entrySet()) {
                if (entry.getValue() == 3) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isThreeInSequence(List<Card> c) {
        int maxSequence = 0;
        if (validSize(c)) {
            sortHand(c);

            int[] sequence = new int[c.size()];
            sequence[0] = 1;


            for (int i = 1; i < c.size(); i++) {
                sequence[i] = 1;
                if (c.get(i).getRank() - c.get(i - 1).getRank() == 1) {
                    sequence[i] = sequence[i - 1] + 1;
                }
                if (sequence[i] > maxSequence) {
                    maxSequence = sequence[i];
                }
            }
        }
        return maxSequence == 3;
    }

}
