import static org.junit.Assert.*;

public class CardTest {

    @org.junit.Test
    public void createAceCardFromStringInputTest() {
        String input = "DA";
        Card c = new Card(input);
        assertEquals(14, c.getRank());
        assertEquals(1, c.getSuit());
    }

    @org.junit.Test
    public void compareCardRank() {
        String input = "DA";
        String input2 = "S10";
        Card c = new Card(input);
        Card c2 = new Card(input2);
        assertEquals(true, c.getRank() > c2.getRank());
    }

    @org.junit.Test
    public void compareCardSuit() {
        String input = "DA";
        String input2 = "SA";
        Card c = new Card(input);
        Card c2 = new Card(input2);
        assertEquals(true, c2.getSuit() > c.getSuit());
    }

    @org.junit.Test
    public void createCardFromStringInputTest() {
        String input = "S10";
        Card c = new Card(input);
        assertEquals(10, c.getRank());
        assertEquals(4, c.getSuit());
    }

    @org.junit.Test
    public void toStringTest() {
        Card c = new Card(2, 8);
        assertEquals("C8", c.toString());
    }

}