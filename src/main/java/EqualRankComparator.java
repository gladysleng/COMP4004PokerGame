import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class EqualRankComparator {
    private HandChecker handChecker = new HandChecker();

    public List<Card> compareRoyalFlush(List<Card> c1, List<Card> c2) {
        if (c1.get(0).getSuit() > c2.get(0).getSuit()) {
            return c1;
        } else {
            return c2;
        }
    }

    public List<Card> compareStraightFlush(List<Card> c1, List<Card> c2) {
        handChecker.sortHand(c1);
        handChecker.sortHand(c2);

        if (c1.get(c1.size() - 1).getRank() == c2.get(c2.size() - 1).getRank()) {
            if (c1.get(c1.size() - 1).getSuit() > c2.get(c2.size() - 1).getSuit()) {
                return c1;
            } else {
                return c2;
            }
        } else {
            if (c1.get(c1.size() - 1).getRank() > c2.get(c2.size() - 1).getRank()) {
                return c1;
            } else {
                return c2;
            }
        }
    }

    private List<Card> compareCenterCard(List<Card> c1, List<Card> c2) {
        handChecker.sortHand(c1);
        handChecker.sortHand(c1);

        if (c1.get(2).getRank() > c2.get(2).getRank()) {
            return c1;
        } else {
            return c2;
        }
    }

    public List<Card> compareFourOfAKind(List<Card> c1, List<Card> c2) {
        return compareCenterCard(c1, c2);

    }

    public List<Card> compareFullHouse(List<Card> c1, List<Card> c2) {
        return compareCenterCard(c1, c2);

    }


    public List<Card> compareThreeOfAKind(List<Card> c1, List<Card> c2) {
        return compareCenterCard(c1, c2);

    }

    public List<Card> compareFlush(List<Card> c1, List<Card> c2) {
        handChecker.sortHand(c1);
        handChecker.sortHand(c2);

        for (int i = c1.size() - 1; i >= 0; i--) {

            //if c1 has higher rank in one of its card
            if (c1.get(i).getRank() > c2.get(i).getRank()) {
                return c1;
            } else if (c2.get(i).getRank() > c1.get(i).getRank()) {
                return c2;
            }
        }

        //both rank equal
        if (c1.get(0).getSuit() > c2.get(0).getSuit()) {
            return c1;
        } else {
            return c2;
        }

    }

    private List<Card> compareHighestRankOfTwoHands(List<Card> c1, List<Card> c2) {
        handChecker.sortHand(c1);
        handChecker.sortHand(c2);

        if (c1.get(c1.size() - 1).getRank() > c2.get(c2.size() - 1).getRank()) {
            return c1;
        } else if (c2.get(c2.size() - 1).getRank() > c1.get(c1.size() - 1).getRank()) {
            return c2;
        } else if (c1.get(c1.size() - 1).getSuit() > c2.get(c2.size() - 1).getSuit()) {
            return c1;
        } else {
            return c2;
        }
    }

    public List<Card> compareStraight(List<Card> c1, List<Card> c2) {
        return compareHighestRankOfTwoHands(c1, c2);
    }

    private Card returnHighestCardOfPairs(List<Card> c) {
        HashMap<Integer, List<Card>> rankBucket = new HashMap<Integer, List<Card>>();
        List<Card> cards;
        for (Card currentCard : c) {
            cards = rankBucket.get(currentCard.getRank());
            if (cards == null) {
                cards = new ArrayList<Card>();
                rankBucket.put(currentCard.getRank(), cards);
            }
            cards.add(currentCard);
        }

        Map.Entry<Integer, List<Card>> highestRankMap = null;
        for (Map.Entry<Integer, List<Card>> entry : rankBucket.entrySet()) {
            if (entry.getValue().size() == 2 && (highestRankMap == null || entry.getKey() > highestRankMap.getKey())) {
                highestRankMap = entry;
            }
        }

        List<Card> highestPair = highestRankMap.getValue();
        handChecker.sortSuit(highestPair);
        return highestPair.get(highestPair.size() - 1);
    }

    private List<Card> compareHighestPair(List<Card> c1, List<Card> c2) {
        Card highestFromC1 = returnHighestCardOfPairs(c1);
        Card highestFromC2 = returnHighestCardOfPairs(c2);

        if (highestFromC1.getRank() > highestFromC2.getRank()) {
            return c1;
        } else if (highestFromC2.getRank() > highestFromC1.getRank()) {
            return c2;
        } else if (highestFromC1.getSuit() > highestFromC2.getSuit()) {
            return c1;
        } else {
            return c2;
        }

    }

    public List<Card> compareTwoPairs(List<Card> c1, List<Card> c2) {
        return compareHighestPair(c1, c2);
    }

    public List<Card> compareOnePair(List<Card> c1, List<Card> c2) {
        return compareHighestPair(c1, c2);

    }

    public List<Card> compareHighCard(List<Card> c1, List<Card> c2) {
        return compareHighestRankOfTwoHands(c1, c2);

    }
}
