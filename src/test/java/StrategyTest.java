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


}