public class CardTestHelper {

    public static java.util.List<Card> createHand(int[][] cards) {
        java.util.List<Card> hand = new java.util.ArrayList<Card>();
        for (int[] card : cards) {
            hand.add(new Card(card[0], card[1]));
        }
        return hand;
    }
}
