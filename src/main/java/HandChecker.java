
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


}