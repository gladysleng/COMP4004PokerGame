import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

public class StrategyTest {

    Strategy strategy;
    HandChecker h = new HandChecker();

    @org.junit.Before
    public void setUp() throws Exception {
        strategy = new Strategy();
    }

    private List<Card> createHand(int[][] cards) {
        List<Card> hand = new ArrayList<Card>();
        for (int[] card : cards) {
            hand.add(new Card(card[0], card[1]));
        }
        return hand;
    }

    private List<Card> createExpectedHand(Card[] cards) {
        List<Card> hand = new ArrayList<Card>();
        for (Card card : cards) {
            hand.add(card);
        }
        h.sortHand(hand);
        return hand;
    }

    private void assertDiscardedCards(List<Card> expectedDiscardedCards, List<Card> discardedCards) {
        h.sortHand(expectedDiscardedCards);
        h.sortHand(discardedCards);
        assertEquals(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeOneCardForRoyalFlush_OnePairTest() {
        List<Card> cardsToExchange = createHand(new int[][]{
                {4, 5}
        });

        List<Card> c = createHand(new int[][]{
                {2, 10},
                {1, 11},
                {1, 10},
                {1, 12},
                {1, 14}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(0)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(2),
                c.get(1),
                c.get(3),
                c.get(4),
                cardsToExchange.get(0),
        });

        List<Card> discardedCards = strategy.changeOneCardForRoyalFlush(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeOneCardForRoyalFlush_firstIndexIsNotRangeOf11to14Test() {
        List<Card> cardsToExchange = createHand(new int[][]{
                {4, 13}
        });

        List<Card> c = createHand(new int[][]{
                {2, 9},
                {1, 11},
                {1, 12},
                {1, 13},
                {1, 14}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(0)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(1),
                c.get(2),
                c.get(3),
                c.get(4),
                cardsToExchange.get(0),
        });

        List<Card> discardedCards = strategy.changeOneCardForRoyalFlush(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeOneCardForStraightFlush_HHHHSTest() {
        List<Card> cardsToExchange = createHand(new int[][]{
                {4, 7}
        });

        List<Card> c = createHand(new int[][]{
                {4, 14},
                {3, 3},
                {3, 2},
                {3, 4},
                {3, 6}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(0)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(2),
                c.get(1),
                c.get(3),
                c.get(4),
                cardsToExchange.get(0),
        });

        List<Card> discardedCards = strategy.changeOneCardForRoyalFlush(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeOneCardForStraightFlush_CHHHHTest() {
        List<Card> cardsToExchange = createHand(new int[][]{
                {4, 7}
        });

        List<Card> c = createHand(new int[][]{
                {2, 14},
                {3, 3},
                {3, 2},
                {3, 4},
                {3, 6}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(0)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(2),
                c.get(1),
                c.get(3),
                c.get(4),
                cardsToExchange.get(0),
        });

        List<Card> discardedCards = strategy.changeOneCardForRoyalFlush(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeCardsForFullHouse_TwoPairsTest() {
        List<Card> cardsToExchange = createHand(new int[][]{
                {4, 7}
        });

        List<Card> c = createHand(new int[][]{
                {2, 14},
                {3, 14},
                {3, 2},
                {1, 2},
                {3, 6}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(4)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(2),
                c.get(1),
                c.get(3),
                c.get(0),
                cardsToExchange.get(0),
        });

        List<Card> discardedCards = strategy.changeCardsForFullHouse(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeCardsForFullHouse_ThreeOfAKind_56AAATest() {
        List<Card> cardsToExchange = createHand(new int[][]{
                {4, 7},
                {2, 9}
        });

        List<Card> c = createHand(new int[][]{
                {2, 14},
                {3, 14},
                {3, 2},
                {1, 14},
                {3, 6}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(2),
                c.get(4)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(0),
                c.get(1),
                c.get(3),
                cardsToExchange.get(1),
                cardsToExchange.get(0)
        });

        List<Card> discardedCards = strategy.changeCardsForFullHouse(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeCardsForFullHouse_ThreeOfAKind_34445Test() {
        List<Card> cardsToExchange = createHand(new int[][]{
                {4, 7},
                {2, 9}
        });

        List<Card> c = createHand(new int[][]{
                {2, 4},
                {3, 4},
                {3, 3},
                {1, 4},
                {3, 5}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(2),
                c.get(4)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(0),
                c.get(1),
                c.get(3),
                cardsToExchange.get(1),
                cardsToExchange.get(0),
        });

        List<Card> discardedCards = strategy.changeCardsForFullHouse(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeCardsForFullHouse_ThreeOfAKind_23444Test() {
        List<Card> cardsToExchange = createHand(new int[][]{
                {4, 7},
                {2, 8}

        });

        List<Card> c = createHand(new int[][]{
                {2, 4},
                {3, 4},
                {3, 2},
                {1, 4},
                {3, 3}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(2),
                c.get(4)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(0),
                c.get(1),
                c.get(3),
                cardsToExchange.get(1),
                cardsToExchange.get(0)
        });

        List<Card> discardedCards = strategy.changeCardsForFullHouse(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeOneCardForFlush_DCCCCTest() {
        List<Card> cardsToExchange = createHand(new int[][]{
                {2, 9}
        });

        List<Card> c = createHand(new int[][]{
                {3, 5},
                {3, 4},
                {3, 2},
                {1, 4},
                {3, 3}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(3)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(0),
                c.get(1),
                c.get(2),
                c.get(4),
                cardsToExchange.get(0),
        });

        List<Card> discardedCards = strategy.changeOneCardForFlush(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeOneCardForFlush_CCCCSTest() {
        List<Card> cardsToExchange = createHand(new int[][]{
                {2, 9}
        });

        List<Card> c = createHand(new int[][]{
                {3, 5},
                {3, 4},
                {3, 2},
                {4, 4},
                {3, 3}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(3)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(0),
                c.get(1),
                c.get(2),
                c.get(4),
                cardsToExchange.get(0),
        });

        List<Card> discardedCards = strategy.changeOneCardForFlush(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeOneCardForStraight_OnePairTest() {
        List<Card> cardsToExchange = createHand(new int[][]{
                {2, 9}
        });

        List<Card> c = createHand(new int[][]{
                {3, 5},
                {3, 4},
                {3, 2},
                {4, 4},
                {3, 3}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(1)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(0),
                c.get(3),
                c.get(2),
                c.get(4),
                cardsToExchange.get(0),
        });

        List<Card> discardedCards = strategy.changeOneCardForStraight(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeOneCardForStraight_removeIndex0Test() {
        List<Card> cardsToExchange = createHand(new int[][]{
                {2, 11}
        });

        List<Card> c = createHand(new int[][]{
                {3, 5},
                {3, 4},
                {3, 2},
                {4, 7},
                {3, 3}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(2)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(0),
                c.get(3),
                c.get(1),
                c.get(4),
                cardsToExchange.get(0),
        });

        List<Card> discardedCards = strategy.changeOneCardForStraight(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeOneCardForStraight_keepHigherRankTest() {
        List<Card> cardsToExchange = createHand(new int[][]{
                {2, 12}
        });

        List<Card> c = createHand(new int[][]{
                {3, 5},
                {3, 4},
                {3, 6},
                {4, 7},
                {3, 9}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(1)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(0),
                c.get(3),
                c.get(2),
                c.get(4),
                cardsToExchange.get(0),
        });

        List<Card> discardedCards = strategy.changeOneCardForStraight(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeOneCardForStraight_removeLastIndexTest() {
        List<Card> cardsToExchange = createHand(new int[][]{
                {2, 12}
        });

        List<Card> c = createHand(new int[][]{
                {3, 5},
                {3, 8},
                {3, 12},
                {4, 7},
                {3, 9}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(2)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(0),
                c.get(3),
                c.get(1),
                c.get(4),
                cardsToExchange.get(0),
        });

        List<Card> discardedCards = strategy.changeOneCardForStraight(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }


    @org.junit.Test
    public void changeOneCardForStraight_AceNoPairsTest() {
        List<Card> cardsToExchange = createHand(new int[][]{
                {2, 7}
        });

        List<Card> c = createHand(new int[][]{
                {3, 2},
                {3, 4},
                {3, 3},
                {4, 12},
                {3, 14}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(3)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(0),
                c.get(2),
                c.get(1),
                c.get(4),
                cardsToExchange.get(0),
        });

        expectedOutput.get(expectedOutput.size()-1).setRank(1);
        h.sortHand(expectedOutput);
        List<Card> discardedCards = strategy.changeOneCardForStraight(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeOneCardForStraight_AceWithAcePairsTest() {
        List<Card> cardsToExchange = createHand(new int[][]{
                {2, 7}
        });

        List<Card> c = createHand(new int[][]{
                {3, 2},
                {3, 4},
                {3, 3},
                {4, 14},
                {3, 14}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(4)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(0),
                c.get(2),
                c.get(1),
                c.get(3),
                cardsToExchange.get(0),
        });

        expectedOutput.get(expectedOutput.size()-1).setRank(1);
        h.sortHand(expectedOutput);
        List<Card> discardedCards = strategy.changeOneCardForStraight(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeOneCardForStraight_AceWithPairsTest() {
        List<Card> cardsToExchange = createHand(new int[][]{
                {2, 7}
        });

        List<Card> c = createHand(new int[][]{
                {3, 2},
                {3, 4},
                {3, 3},
                {4, 2},
                {3, 14}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(0)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(1),
                c.get(2),
                c.get(3),
                c.get(4),
                cardsToExchange.get(0),
        });

        expectedOutput.get(expectedOutput.size()-1).setRank(1);
        h.sortHand(expectedOutput);
        List<Card> discardedCards = strategy.changeOneCardForStraight(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeTwoCardsForThreeSameSuitTest(){
        List<Card> cardsToExchange = createHand(new int[][]{
                {2, 7},
                {2, 3}
        });

        List<Card> c = createHand(new int[][]{
                {3, 2},
                {3, 4},
                {3, 3},
                {4, 2},
                {1, 14}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(3),
                c.get(4)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(1),
                c.get(2),
                c.get(0),
                cardsToExchange.get(0),
                cardsToExchange.get(1)
        });

        List<Card> discardedCards = strategy.changeTwoCardsForThreeSameSuit(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

    @org.junit.Test
    public void changeThreeCardsForOnePairTest(){
        List<Card> cardsToExchange = createHand(new int[][]{
                {2, 7},
                {2, 12},
                {4, 5}
        });

        List<Card> c = createHand(new int[][]{
                {3, 2},
                {3, 4},
                {3, 3},
                {4, 2},
                {1, 14}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(1),
                c.get(2),
                c.get(4)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(0),
                c.get(3),
                cardsToExchange.get(2),
                cardsToExchange.get(0),
                cardsToExchange.get(1)
        });

        List<Card> discardedCards = strategy.changeThreeCardsForOnePair(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }


    @org.junit.Test
    public void changeThreeCardsForHighestCardTest(){
        List<Card> cardsToExchange = createHand(new int[][]{
                {2, 7},
                {2, 12},
                {4, 5}
        });

        List<Card> c = createHand(new int[][]{
                {3, 6},
                {2, 10},
                {3, 3},
                {4, 2},
                {1, 14}
        });

        List<Card> expectedDiscardedCards = createExpectedHand(new Card[]{
                c.get(0),
                c.get(2),
                c.get(3)
        });

        List<Card> expectedOutput = createExpectedHand(new Card[]{
                c.get(4),
                c.get(1),
                cardsToExchange.get(2),
                cardsToExchange.get(0),
                cardsToExchange.get(1)
        });

        List<Card> discardedCards = strategy.changeThreeCardsForHighCards(c, cardsToExchange);
        assertEquals(expectedOutput, c);
        assertDiscardedCards(expectedDiscardedCards, discardedCards);
    }

}