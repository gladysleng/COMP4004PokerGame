import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class HandCheckerTest {
    HandChecker handChecker;

    @org.junit.Before
    public void setUp() throws Exception {
        handChecker = new HandChecker();
    }

    @org.junit.Test
    public void validSizeTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {1, 6},
                {1, 2},
                {1, 3},
                {1, 4},
                {1, 5}
        });
        assertEquals(5, c.size());
    }

    @org.junit.Test
    public void sortHandTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {1, 10},
                {1, 2},
                {2, 12},
                {1, 12},
                {3, 3}
        });

        ArrayList<Card> expectedOutput = new ArrayList<Card>();
        expectedOutput.add(c.get(1));
        expectedOutput.add(c.get(4));
        expectedOutput.add(c.get(0));
        expectedOutput.add(c.get(3));
        expectedOutput.add(c.get(2));

        handChecker.sortHand(c);
        assertEquals(expectedOutput, c);
    }

    @org.junit.Test
    public void sortSuitTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {4, 10},
                {3, 2},
                {2, 12},
                {1, 12}
        });

        ArrayList<Card> expectedOutput = new ArrayList<Card>();
        expectedOutput.add(c.get(3));
        expectedOutput.add(c.get(2));
        expectedOutput.add(c.get(1));
        expectedOutput.add(c.get(0));

        handChecker.sortSuit(c);
        assertEquals(expectedOutput, c);

    }

    @org.junit.Test
    public void isFullHouseAABBBTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {4, 10},
                {2, 12},
                {1, 12},
                {1, 10},
                {3, 12}
        });
        assertEquals(true, handChecker.isFullHouse(c));
    }

    @org.junit.Test
    public void isFullHouseAAABBTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {4, 10},
                {2, 10},
                {1, 12},
                {3, 12},
                {1, 10}
        });
        assertEquals(true, handChecker.isFullHouse(c));
    }


    @org.junit.Test
    public void isFlushTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {4, 10},
                {4, 7},
                {4, 8},
                {4, 12},
                {4, 11}
        });
        assertEquals(true, handChecker.isFlush(c));
    }

    @org.junit.Test
    public void isFlushTest_invalid() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {4, 10},
                {4, 7},
                {4, 8},
                {3, 12},
                {4, 11}
        });
        assertEquals(false, handChecker.isFlush(c));

    }

    @org.junit.Test
    public void isStraightFlushDifferentSuitsTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {4, 10},
                {4, 14},
                {3, 13},
                {4, 12},
                {2, 11}
        });
        assertEquals(false, handChecker.isStraightFlush(c));
    }

    @org.junit.Test
    public void isStraightFlushTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 7},
                {2, 4},
                {2, 5},
                {2, 6},
                {2, 3}
        });
        assertEquals(true, handChecker.isStraightFlush(c));
    }

    @org.junit.Test
    public void isStraightFlushAceToFiveWithFlushTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {2, 3},
                {2, 5},
                {2, 4},
                {2, 2}
        });
        assertEquals(true, handChecker.isStraightFlush(c));
    }

    @org.junit.Test
    public void isStraightFlush_RoyalFlushTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {2, 11},
                {2, 10},
                {2, 13},
                {2, 12}
        });
        assertEquals(false, handChecker.isStraightFlush(c));
    }
    @org.junit.Test
    public void isFourOfAKind_37777Test() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {1, 14},
                {2, 2},
                {4, 14},
                {3, 14}
        });
        assertEquals(true, handChecker.isFourOfAKind(c));
    }

    @org.junit.Test
    public void isFourOfAKind_777712Test() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {1, 11},
                {2, 12},
                {3, 11},
                {4, 11},
                {2, 11}
        });
        assertEquals(true, handChecker.isFourOfAKind(c));
    }

    @org.junit.Test
    public void isStraightKingAce234Test() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {1, 13},
                {3, 2},
                {4, 3},
                {2, 4}
        });
        assertEquals(false, handChecker.isStraight(c));
    }

    @org.junit.Test
    public void isStraight_Ace2345Test() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {1, 5},
                {3, 2},
                {4, 3},
                {2, 4}
        });
        assertEquals(true, handChecker.isStraight(c));
    }

    @org.junit.Test
    public void isStraightTenToAceTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {1, 13},
                {3, 10},
                {4, 11},
                {2, 12}
        });
        assertEquals(true, handChecker.isStraight(c));
    }

    @org.junit.Test
    public void isStraightAceWithTwoButNotStraightTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {1, 13},
                {3, 3},
                {4, 11},
                {2, 2}
        });
        assertEquals(false, handChecker.isStraight(c));
    }

    @org.junit.Test
    public void isThreeOfAKind_XYAAATest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {1, 14},
                {3, 3},
                {4, 2},
                {4, 14}
        });
        assertEquals(true, handChecker.isThreeOfAKind(c));
    }

    @org.junit.Test
    public void isThreeOfAKind_AAA78Test() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 7},
                {3, 3},
                {1, 8},
                {4, 3},
                {2, 3}
        });
        assertEquals(true, handChecker.isThreeOfAKind(c));
    }

    @org.junit.Test
    public void isThreeOfAKind_XAAAYTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {1, 7},
                {2, 3},
                {3, 7},
                {4, 7}

        });
        assertEquals(true, handChecker.isThreeOfAKind(c));
    }

    @org.junit.Test
    public void isThreeOfAKind_NotFourOfAKindTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {1, 7},
                {3, 7},
                {4, 7},
                {2, 7}
        });
        assertEquals(false, handChecker.isThreeOfAKind(c));
    }

    @org.junit.Test
    public void isThreeOfAKind_NotFullHouseTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {1, 14},
                {3, 7},
                {4, 7},
                {2, 7}
        });
        assertEquals(false, handChecker.isThreeOfAKind(c));
    }

    @org.junit.Test
    public void isTwoPair_NotFullHouseTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 3},
                {1, 4},
                {3, 3},
                {4, 4},
                {2, 4}
        });
        assertEquals(false, handChecker.isTwoPair(c));
    }

    @org.junit.Test
    public void isTwoPair_NotFourOfAKindTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 3},
                {1, 4},
                {3, 4},
                {4, 4},
                {2, 4}
        });
        assertEquals(false, handChecker.isTwoPair(c));
    }

    @org.junit.Test
    public void isTwoPair_NotThreeOfAKindTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {1, 7},
                {3, 1},
                {4, 7},
                {2, 7}
        });
        assertEquals(false, handChecker.isTwoPair(c));
    }

    @org.junit.Test
    public void isTwoPair_44799Test() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 7},
                {1, 9},
                {3, 4},
                {4, 9},
                {2, 4}
        });
        assertEquals(true, handChecker.isTwoPair(c));
    }

    @org.junit.Test
    public void isTwoPair_22JJATest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 11},
                {1, 2},
                {3, 2},
                {4, 14},
                {2, 11}
        });
        assertEquals(true, handChecker.isTwoPair(c));
    }

    @org.junit.Test
    public void isTwoPair_46699Test() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 9},
                {3, 6},
                {4, 4},
                {1, 9},
                {2, 6}
        });
        assertEquals(true, handChecker.isTwoPair(c));
    }

    @org.junit.Test
    public void isOnePair_NotTwoPairTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 9},
                {1, 9},
                {3, 6},
                {4, 4},
                {2, 6}
        });
        assertEquals(false, handChecker.isOnePair(c));
    }

    @org.junit.Test
    public void isOnePair_NotThreeOfAKindTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 9},
                {1, 9},
                {3, 9},
                {4, 4},
                {2, 6}
        });
        assertEquals(false, handChecker.isOnePair(c));
    }

    @org.junit.Test
    public void isOnePair_NotFourOfAKindTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 9},
                {1, 9},
                {3, 9},
                {4, 9},
                {2, 6}
        });
        assertEquals(false, handChecker.isOnePair(c));
    }

    @org.junit.Test
    public void isOnePairTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 9},
                {3, 14},
                {1, 9},
                {4, 5},
                {2, 6}
        });
        assertEquals(true, handChecker.isOnePair(c));
    }

    @org.junit.Test
    public void isHighCard_notOnePairsTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 9},
                {1, 9},
                {3, 14},
                {4, 5},
                {2, 6}
        });
        assertEquals(false, handChecker.isHighCard(c));
    }

    @org.junit.Test
    public void isHighCard_notThreeOfAKindTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 9},
                {1, 9},
                {3, 9},
                {4, 5},
                {2, 6}
        });
        assertEquals(false, handChecker.isHighCard(c));
    }

    @org.junit.Test
    public void isHighCard_notFourOfAKindTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 9},
                {1, 9},
                {3, 9},
                {4, 9},
                {2, 6}
        });
        assertEquals(false, handChecker.isHighCard(c));
    }

    @org.junit.Test
    public void isHighCard_notFullHouseTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 9},
                {1, 9},
                {3, 9},
                {4, 6},
                {2, 6}
        });
        assertEquals(false, handChecker.isHighCard(c));
    }

    @org.junit.Test
    public void isHighCard_notStraightTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 10},
                {1, 14},
                {3, 11},
                {4, 13},
                {2, 12}
        });
        assertEquals(false, handChecker.isHighCard(c));
    }

    @org.junit.Test
    public void isHighCard_notStraightFlushTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {2, 2},
                {2, 5},
                {2, 4},
                {2, 3}
        });
        assertEquals(false, handChecker.isHighCard(c));
    }

    @org.junit.Test
    public void isHighCard_notFlushTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {2, 5},
                {2, 8},
                {2, 3},
                {2, 9}
        });
        assertEquals(false, handChecker.isHighCard(c));
    }

    @org.junit.Test
    public void isHighCardTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {2, 5},
                {3, 7},
                {2, 3},
                {2, 9}
        });
        assertEquals(true, handChecker.isHighCard(c));
    }

    @org.junit.Test
    public void isRoyalFlush_noFlushTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {2, 11},
                {3, 10},
                {2, 12},
                {2, 13}
        });
        assertEquals(false, handChecker.isRoyalFlush(c));
    }

    @org.junit.Test
    public void isRoyalFlush_notTenToAce_notStraightTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {2, 7},
                {2, 10},
                {2, 12},
                {2, 13}
        });
        assertEquals(false, handChecker.isRoyalFlush(c));
    }


    @org.junit.Test
    public void isRoyalFlush_notTenToAce_StraightTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 8},
                {2, 5},
                {2, 6},
                {2, 7},
                {2, 4}
        });
        assertEquals(false, handChecker.isRoyalFlush(c));
    }

    @org.junit.Test
    public void isRoyalFlushTest() {
        List<Card> c = CardTestHelper.createHand(new int[][]{
                {2, 14},
                {2, 11},
                {2, 10},
                {2, 12},
                {2, 13}
        });
        assertEquals(true, handChecker.isRoyalFlush(c));
    }


}