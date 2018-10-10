import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

public class EqualRankComparatorTest {
    EqualRankComparator comparator;

    @org.junit.Before
    public void setUp() throws Exception {
        comparator = new EqualRankComparator();
    }

    @org.junit.Test
    public void compareRoyalFlush_AIPWins() {
        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {3, 12},
                {3, 11},
                {3, 13},
                {3, 14},
                {3, 10}
        });

        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {1, 12},
                {1, 11},
                {1, 13},
                {1, 14},
                {1, 10}
        });

        List<Card> winner = c1;
        assertEquals(winner, comparator.compareRoyalFlush(c1, c2));
    }

    @org.junit.Test
    public void compareRoyalFlush_OpponentWins() {
        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {3, 12},
                {3, 11},
                {3, 13},
                {3, 14},
                {3, 10}
        });

        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {1, 12},
                {1, 11},
                {1, 13},
                {1, 14},
                {1, 10}
        });

        List<Card> winner = c2;
        assertEquals(winner, comparator.compareRoyalFlush(c1, c2));
    }

    @org.junit.Test
    public void compareStraightFlush_AIPWinsCompareRank() {

        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {3, 6},
                {3, 7},
                {3, 8},
                {3, 9},
                {3, 10}
        });

        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {1, 5},
                {1, 6},
                {1, 7},
                {1, 8},
                {1, 9}
        });

        List<Card> winner = c1;
        assertEquals(winner, comparator.compareStraightFlush(c1, c2));

    }

    @org.junit.Test
    public void compareStraightFlush_OpponentWinsCompareRank() {

        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {3, 6},
                {3, 7},
                {3, 8},
                {3, 9},
                {3, 10}
        });

        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {1, 5},
                {1, 6},
                {1, 7},
                {1, 8},
                {1, 9}
        });

        List<Card> winner = c2;
        assertEquals(winner, comparator.compareStraightFlush(c1, c2));
    }


    @org.junit.Test
    public void compareStraightFlush_AIPWinsCompareSuit() {

        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {3, 6},
                {3, 7},
                {3, 8},
                {3, 9},
                {3, 10}
        });

        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {2, 6},
                {2, 7},
                {2, 8},
                {2, 9},
                {2, 10}
        });

        List<Card> winner = c1;
        assertEquals(winner, comparator.compareStraightFlush(c1, c2));

    }

    @org.junit.Test
    public void compareStraightFlush_OpponentWinsCompareSuit() {

        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {3, 6},
                {3, 7},
                {3, 8},
                {3, 9},
                {3, 10}
        });

        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {2, 6},
                {2, 7},
                {2, 8},
                {2, 9},
                {2, 10}
        });

        List<Card> winner = c2;
        assertEquals(winner, comparator.compareStraightFlush(c1, c2));
    }

    @org.junit.Test
    public void compareFourOfAKind_AIPWins() {
        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {3, 7},
                {2, 7},
                {1, 7},
                {4, 7},
                {3, 10}
        });

        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {3, 12},
                {2, 12},
                {1, 12},
                {4, 12},
                {4, 10}
        });

        List<Card> winner = c1;
        assertEquals(winner, comparator.compareFourOfAKind(c1, c2));
    }

    @org.junit.Test
    public void compareFourOfAKind_OpponentWins() {
        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {3, 7},
                {2, 7},
                {1, 7},
                {4, 7},
                {3, 10}
        });

        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {3, 12},
                {2, 12},
                {1, 12},
                {4, 12},
                {4, 10}
        });

        List<Card> winner = c2;
        assertEquals(winner, comparator.compareFourOfAKind(c1, c2));
    }

    @org.junit.Test
    public void compareFullHouse_OpponentWins() {
        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {3, 7},
                {2, 7},
                {1, 7},
                {2, 10},
                {3, 10}
        });

        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {3, 12},
                {2, 12},
                {1, 12},
                {4, 10},
                {1, 10}
        });

        List<Card> winner = c2;
        assertEquals(winner, comparator.compareFullHouse(c1, c2));
    }

    @org.junit.Test
    public void compareFullHouse_AIPWins() {
        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {3, 7},
                {2, 7},
                {1, 7},
                {4, 7},
                {3, 10}
        });

        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {3, 12},
                {2, 12},
                {1, 12},
                {4, 12},
                {4, 10}
        });

        List<Card> winner = c1;
        assertEquals(winner, comparator.compareFullHouse(c1, c2));
    }

    @org.junit.Test
    public void compareThreeOfAKind_OpponentWins() {
        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {3, 7},
                {2, 7},
                {1, 7},
                {4, 10},
                {3, 11}
        });

        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {3, 12},
                {2, 12},
                {1, 12},
                {3, 10},
                {1, 2}
        });

        List<Card> winner = c2;
        assertEquals(winner, comparator.compareThreeOfAKind(c1, c2));
    }

    @org.junit.Test
    public void compareThreeOfAKind_AIPWins() {
        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {3, 7},
                {2, 7},
                {1, 7},
                {4, 10},
                {3, 11}
        });

        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {3, 12},
                {2, 12},
                {1, 12},
                {3, 10},
                {1, 2}
        });

        List<Card> winner = c1;
        assertEquals(winner, comparator.compareThreeOfAKind(c1, c2));
    }

    @org.junit.Test
    public void compareFlush_CompareWithRecursiveRank_3thRank() {

        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {3, 2},
                {2, 3},
                {3, 7},
                {4, 10},
                {3, 11}
        });

        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {4, 2},
                {1, 3},
                {1, 6},
                {3, 10},
                {1, 11}
        });

        List<Card> winner = c1;
        assertEquals(winner, comparator.compareFlush(c1, c2));
    }


    @org.junit.Test
    public void compareFlush_CompareWithRecursiveRank_2thRank() {

        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {3, 2},
                {2, 4},
                {3, 6},
                {4, 10},
                {3, 11}
        });

        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {4, 2},
                {1, 3},
                {1, 6},
                {3, 10},
                {1, 11}
        });

        List<Card> winner = c1;
        assertEquals(winner, comparator.compareFlush(c1, c2));
    }

    @org.junit.Test
    public void compareFlush_CompareWithRecursiveRank_1thRank() {

        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {3, 3},
                {2, 6},
                {3, 8},
                {4, 10},
                {3, 11}
        });

        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {4, 2},
                {1, 6},
                {1, 8},
                {3, 10},
                {1, 11}
        });

        List<Card> winner = c1;
        assertEquals(winner, comparator.compareFlush(c1, c2));
    }

    @org.junit.Test
    public void compareFlush_CompareWithRecursiveRank_4thRank() {

        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {3, 2},
                {2, 4},
                {3, 7},
                {4, 10},
                {3, 11}
        });

        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {4, 2},
                {1, 3},
                {1, 6},
                {3, 8},
                {1, 11}
        });

        List<Card> winner = c1;
        assertEquals(winner, comparator.compareFlush(c1, c2));
    }

    @org.junit.Test
    public void compareFlush_CompareWithRecursiveRank_5thRank() {

        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {4, 2},
                {3, 3},
                {4, 7},
                {3, 10},
                {3, 12}
        });

        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {1, 2},
                {2, 3},
                {1, 7},
                {2, 10},
                {1, 11}
        });

        List<Card> winner = c1;
        assertEquals(winner, comparator.compareFlush(c1, c2));
    }


    @org.junit.Test
    public void compareFlush_AIPWinsCompareWithSuit() {

        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {2, 2},
                {1, 3},
                {4, 7},
                {3, 10},
                {3, 11}
        });

        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {1, 2},
                {2, 3},
                {1, 7},
                {4, 10},
                {1, 11}
        });

        List<Card> winner = c1;
        assertEquals(winner, comparator.compareFlush(c1, c2));
    }

    @org.junit.Test
    public void compareFlush_OpponentWinsCompareWithSuit() {

        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {3, 2},
                {2, 3},
                {1, 7},
                {3, 10},
                {3, 11}
        });

        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {1, 2},
                {1, 3},
                {4, 7},
                {1, 10},
                {1, 11}
        });

        List<Card> winner = c2;
        assertEquals(winner, comparator.compareFlush(c1, c2));
    }
    @org.junit.Test
    public void compareStraight_AIPWithRank() {
        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {3, 2},
                {2, 3},
                {1, 4},
                {4, 5},
                {4, 6}
        });

        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {4, 10},
                {3, 11},
                {1, 12},
                {2, 13},
                {1, 14}
        });

        List<Card> winner = c1;
        assertEquals(winner, comparator.compareStraight(c1, c2));

    }

    @org.junit.Test
    public void compareStraight_OpponentWithRank() {
        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {3, 2},
                {2, 3},
                {1, 4},
                {4, 5},
                {4, 6}
        });

        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {4, 10},
                {3, 11},
                {1, 12},
                {2, 13},
                {1, 14}
        });

        List<Card> winner = c2;
        assertEquals(winner, comparator.compareStraight(c1, c2));

    }

    @org.junit.Test
    public void compareStraight_AIPWithSuit() {
        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {3, 2},
                {2, 3},
                {1, 4},
                {4, 5},
                {4, 6}
        });

        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {4, 2},
                {3, 3},
                {1, 4},
                {2, 5},
                {1, 6}
        });

        List<Card> winner = c1;
        assertEquals(winner, comparator.compareStraight(c1, c2));

    }

    @org.junit.Test
    public void compareStraight_OpponentWithSuit() {
        List<Card> c2 = CardTestHelper.createHand(new int[][]{
                {3, 2},
                {2, 3},
                {1, 4},
                {4, 5},
                {4, 6}
        });

        List<Card> c1 = CardTestHelper.createHand(new int[][]{
                {4, 2},
                {3, 3},
                {1, 4},
                {2, 5},
                {1, 6}
        });

        List<Card> winner = c2;
        assertEquals(winner, comparator.compareStraight(c1, c2));

    }
}