import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


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


    public List<Card> changeOneCardForStraightFlush(List<Card> c, List<Card> cardToExchange) {
        handChecker.sortSuit(c);
        List<Card> discardedCard = new ArrayList<Card>();
        if (c.get(0).getSuit() != c.get(2).getSuit()) {
            discardedCard.add(c.remove(0));
        } else {
            discardedCard.add(c.remove(c.size() - 1));
        }
        if (c.size() != 5) {
            c.addAll(cardToExchange);
        }
        handChecker.sortHand(c);
        return discardedCard;

    }

    public List<Card> changeOneCardForFlush(List<Card> c, List<Card> cardsToChange) {
        List<Card> discardedCard = new ArrayList<Card>();
        handChecker.sortSuit(c);

        if (c.get(0).getSuit() != c.get(2).getSuit()) {
            discardedCard.add(c.remove(0));
        } else {
            discardedCard.add(c.remove(c.size() - 1));
        }
        c.addAll(cardsToChange);
        handChecker.sortHand(c);
        return discardedCard;
    }

    public List<Card> changeOneCardForStraight(List<Card> c, List<Card> cardsToChange) {
        handChecker.sortHand(c);
        List<Card> discardedCard = new ArrayList<Card>();
        if (c.get(c.size() - 1).getRank() == 14) {
            handChecker.changeRankChangeForAce(c);
        }

        if (handChecker.isOnePair(c)) {
            for (int i = 0; i < c.size() - 1; i++) {
                if (c.get(i).getRank() == c.get(i + 1).getRank()) {
                    discardedCard.add(c.remove(i));
                    break;
                }
            }
        } else {

            if (handChecker.rangeOfFourOrThree((c.get(c.size() - 1).getRank()), (c.get(1).getRank()))) {
                discardedCard.add(c.remove(0));

            } else {
                discardedCard.add(c.remove(c.size() - 1));
            }
        }
        c.addAll(cardsToChange);
        handChecker.sortHand(c);
        return discardedCard;
    }

    public List<Card> changeTwoCardsForThreeSameSuit(List<Card> c, List<Card> cardsToChange) {
        handChecker.sortSuit(c);
        List<Card> discardedCard = new ArrayList<Card>();

        //DDDHH
        if ((c.get(0).getSuit() == c.get(2).getSuit())) {
            discardedCard.add(c.remove(3));
            discardedCard.add(c.remove(4));
        }
        //DCCCS
        else if ((c.get(1).getSuit() == c.get(3).getSuit())) {
            discardedCard.add(c.remove(0));
            discardedCard.add(c.remove(3));
        }
        //DCSSS
        else if ((c.get(2).getSuit() == c.get(4).getSuit())) {
            discardedCard.add(c.remove(0));
            discardedCard.add(c.remove(0));
        }
        c.addAll(cardsToChange);

        handChecker.sortHand(c);
        return discardedCard;
    }

    private int returnRankForNumberOfCards(List<Card> c, int numOfSpecificCardInList) {

        int requestedRank = 0;
        HashMap<Integer, Integer> rankBucket = new HashMap<Integer, Integer>();
        Integer count;

        for (Card currentCard : c) {
            count = rankBucket.get(currentCard.getRank());
            if (count == null) {
                count = 0;
            }
            rankBucket.put(currentCard.getRank(), ++count);
        }

        for (Map.Entry<Integer, Integer> entry : rankBucket.entrySet()) {
            if (entry.getValue() == numOfSpecificCardInList) {
                requestedRank = entry.getKey();
            }
        }
        return requestedRank;
    }
    public List<Card> changeThreeCardsForOnePair(List<Card> c, List<Card> cardsToChange) {
        List<Card> discardedCard = new ArrayList<Card>();
        int rankInPairs = returnRankForNumberOfCards(c, 2);

        handChecker.sortHand(c);
        for (int i = 0; i < c.size(); i++) {
            if (!(c.get(i).getRank() == rankInPairs)) {
                discardedCard.add(c.get(i));
            }
        }
        c.removeAll(discardedCard);
        c.addAll(cardsToChange);
        handChecker.sortHand(c);
        return discardedCard;

    }
    public List<Card> changeThreeCardsForHighCards(List<Card> c, List<Card> cardsToChange) {
        List<Card> discardedCard = new ArrayList<Card>();
        handChecker.sortHand(c);
        discardedCard.add(c.remove(0));
        discardedCard.add(c.remove(0));
        discardedCard.add(c.remove(0));
        c.addAll(cardsToChange);

        handChecker.sortHand(c);
        return discardedCard;
    }

    public List<Card> changeTwoCardsForThreeInSequence(List<Card> c, List<Card> cardsToChange) {
        List<Card> discardedCard = new ArrayList<Card>();
        handChecker.sortHand(c);

        if (c.get(0).getRank() == c.get(1).getRank() - 1 && c.get(0).getRank() == c.get(2).getRank() - 2) {
            discardedCard.add(c.remove(c.size() - 1));
            discardedCard.add(c.remove(c.size() - 1));
        } else if (c.get(1).getRank() == c.get(2).getRank() - 1 && c.get(1).getRank() == c.get(3).getRank() - 2) {
            discardedCard.add(c.remove(0));
            discardedCard.add(c.remove(c.size() - 1));
        } else if (c.get(2).getRank() == c.get(3).getRank() - 1 && c.get(2).getRank() == c.get(4).getRank() - 2) {
            discardedCard.add(c.remove(0));
            discardedCard.add(c.remove(0));
        }

        c.addAll(cardsToChange);

        handChecker.sortHand(c);
        return discardedCard;
    }


    //test for this is covered for change one card for fullHouse in the twoPairs if statement
    public List<Card> changeOneCardForTwoPairs(List<Card> c, List<Card> cardsToChange) {
        int rankWithoutPairs = returnRankForNumberOfCards(c, 1);
        List<Card> discardedCard = new ArrayList<Card>();

        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).getRank() == rankWithoutPairs) {
                discardedCard.add(c.remove(i));
                break;
            }
        }
        c.addAll(cardsToChange);
        handChecker.sortHand(c);
        return discardedCard;
    }


    public List<Card> changeCardsForFullHouse(List<Card> c, List<Card> cardsToChange) {
        List<Card> discardedCard = new ArrayList<Card>();
        if (handChecker.isTwoPair(c)) {
            discardedCard = changeOneCardForTwoPairs(c, cardsToChange);

        } else {

            discardedCard = changeTwoCardsForThreeOfAKind(c, cardsToChange);

        }
        handChecker.sortHand(c);
        return discardedCard;

    }
    //private because it is never gonna run because of the strategy from the requirement
    //cards with three same rank will enter fullhouse and change one card instead of this function
    private List<Card> changeTwoCardsForThreeOfAKind(List<Card> c, List<Card> cardsToChange) {
        List<Card> discardedCard = new ArrayList<Card>();
        handChecker.sortHand(c);

        //22234
        if ((c.get(0).getRank() == c.get(2).getRank())) {
            discardedCard.add(c.remove(4));
            discardedCard.add(c.remove(3));
        }
        //34445
        else if ((c.get(1).getRank() == c.get(3).getRank())) {
            discardedCard.add(c.remove(c.size()-1));
            discardedCard.add(c.remove(0));
        }
        //56444
        else if ((c.get(2).getRank() == c.get(4).getRank())) {
            discardedCard.add(c.remove(0));
            discardedCard.add(c.remove(0));
        }
        c.addAll(cardsToChange);
        handChecker.sortHand(c);
        return discardedCard;
    }

    public List<Card> applyStrategy(List<Card> c, List<Card> cardsToChange) {

        if (handChecker.oneCardFromRoyalFlush(c)) {
            System.out.println("AIP is one card away from Royal Flush, exchange one card");
            return changeOneCardForRoyalFlush(c, cardsToChange);

        } else if (handChecker.oneCardFromStraightFlush(c)) {
            System.out.println("AIP is one card away from Straight Flush, exchange one card");
            return changeOneCardForStraightFlush(c, cardsToChange);
        } else if (handChecker.oneCardFromFlush(c)) {
            System.out.println("AIP is one card away from Flush, exchange one card");
            return changeOneCardForFlush(c, cardsToChange);
        } else if (handChecker.oneCardFromStraight(c)) {
            System.out.println("AIP is one card away from Straight, exchange one card");
            return changeOneCardForStraight(c, cardsToChange);
        } else if (handChecker.isThreeOfSameSuit(c)) {
            System.out.println("AIP has three of the same suit, exchange two card");
            return changeTwoCardsForThreeSameSuit(c, cardsToChange);
        } else if (handChecker.isThreeOfAKind(c)) {
            System.out.println("AIP has three of a same rank, exchange two card");
            return changeTwoCardsForThreeOfAKind(c, cardsToChange);
        } else if (handChecker.isThreeInSequence(c)) {
            System.out.println("AIP has three cards in sequence, exchange two card");
            return changeTwoCardsForThreeInSequence(c, cardsToChange);
        } else if (handChecker.isTwoPair(c)) {
            System.out.println("AIP has two pairs, exchange one card");
            return changeOneCardForTwoPairs(c, cardsToChange);
        } else if (handChecker.isOnePair(c)) {
            System.out.println("AIP has one pair, exchange three card");
            return changeThreeCardsForOnePair(c, cardsToChange);
        } else if (handChecker.isHighCard(c)) {
            System.out.println("AIP has high card, exchange the 3 lowest card");
            return changeThreeCardsForHighCards(c, cardsToChange);
        }
        return null;
    }
}
