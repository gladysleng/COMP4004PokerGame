import static org.junit.Assert.*;

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
}