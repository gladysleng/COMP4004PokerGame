import java.util.Map;
import java.util.HashMap;

public class Card {

    private int suit;
    private int rank;

    public Card(int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Card(String input){
        this.suit = mapStringtoIntSuit(input.charAt(0));
        this.rank = mapStringtoIntRank(input.substring(1));
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int r){  // for straight flush or flush, ace became the lowest rank
        this.rank = r;
    }

    private static int mapStringtoIntSuit(char input){
        int suitRank;

        switch(input){
            case 'D':
                suitRank = 1;
                break;

            case 'C':
                suitRank = 2;
                break;

            case 'H':
                suitRank = 3;
                break;

            case 'S':
                suitRank = 4;
                break;

            default:
                suitRank = 0;
        }
        return suitRank;
    }

    private static int mapStringtoIntRank(String input){
        Map<String, Integer> map = new HashMap<String, Integer>();

        map.put("2",2);
        map.put("3",3);
        map.put("4",4);
        map.put("5",5);
        map.put("6",6);
        map.put("7",7);
        map.put("8",8);
        map.put("9",9);
        map.put("10",10);
        map.put("J",11);
        map.put("Q",12);
        map.put("K",13);
        map.put("A",14);

        return map.get(input);
    }


    @Override
    public String toString() {

        String cardString;

        switch (this.suit) {
            case 1:
                cardString = "D";
                break;

            case 2:
                cardString = "C";
                break;

            case 3:
                cardString = "H";
                break;

            case 4:
                cardString = "S";
                break;

            default:
                cardString = "Invalid suit";
                break;
        }

        switch (this.rank) {
            case 1:  // in case for straight flush/flush
                cardString += "A";
                break;

            case 2:
                cardString += "2";
                break;

            case 3:
                cardString += "3";
                break;

            case 4:
                cardString += "4";
                break;

            case 5:
                cardString += "5";
                break;

            case 6:
                cardString += "6";
                break;

            case 7:
                cardString += "7";
                break;

            case 8:
                cardString += "8";
                break;

            case 9:
                cardString += "9";
                break;

            case 10:
                cardString += "10";
                break;

            case 11:
                cardString += "J";
                break;

            case 12:
                cardString += "Q";
                break;

            case 13:
                cardString += "K";
                break;

            case 14:
                cardString += "A";
                break;
            default:
                cardString = "Invalid suit";
                break;
        }

        return cardString;
    }

}
