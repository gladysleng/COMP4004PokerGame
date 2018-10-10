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
}