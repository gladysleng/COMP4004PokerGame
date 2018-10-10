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

}