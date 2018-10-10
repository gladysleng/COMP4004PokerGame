import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


///// REMEMBER  AFTER READING THE CARD TO EXCHANGE OR AFTER EXCHANGING
//// REMOVE IT FROM THE DECK


public class Game {

    public Player getAip() {
        return aip;
    }


    private Player aip, opponent;
    private Strategy strategy;
    private HandChecker handChecker;
    private EqualRankComparator equalRankcomparator;
    private ReadFile reader;

    private List<Card> cardsToExchange;
    private List<Card> cardsToDiscard;

    public Game() {

        aip = new Player("AIP");
        opponent = new Player("Opponent");
        strategy = new Strategy();
        handChecker = new HandChecker();
        equalRankcomparator = new EqualRankComparator();
        reader = new ReadFile();
    }


    private List<Card> createHandsFromStringArray(String[] cardStrings) {
        List<Card> cardsInput = new ArrayList<Card>();
        for (String cardString : cardStrings) {
            cardsInput.add(new Card(cardString));
        }
        return cardsInput;
    }

    public void setUpForInitialHands(String gameLine) {
        String[] lineCardStrings = gameLine.split(" ");
        String[] aipCardsStrings = Arrays.copyOfRange(lineCardStrings, 0, 5);
        String[] opponentCardsStrings = Arrays.copyOfRange(lineCardStrings, 5, 10);
        String[] cardToExchangeStrings = Arrays.copyOfRange(lineCardStrings, 10, lineCardStrings.length);

        System.out.println("INITIAL HANDS:");

        opponent.setHand(createHandsFromStringArray(opponentCardsStrings));
        opponent.printHand();
        aip.setHand(createHandsFromStringArray(aipCardsStrings));
        aip.printHand();
        cardsToExchange = createHandsFromStringArray(cardToExchangeStrings);
    }


    public void detectRankOfHand(Player p) {
        p.setHandRank(handChecker.getPokerRank(p.getHand()));
    }

    public void run() {
        int gameCounter = 0;
        while (true) {

            String gameLine = reader.readNext();
            if (gameLine == null) {
                break;
            }
            System.out.println();
            System.out.println("-----------------------------------------------------------------");
            System.out.println("                            Game " + ++gameCounter + "!!");
            System.out.println("-----------------------------------------------------------------");

            setUpForInitialHands(gameLine);
            playRound(gameLine);

        }
    }


    public void playRound(String gameLine) {
        cardsToDiscard = new ArrayList<Card>();

        System.out.print("Rank of AIP : ");
        detectRankOfHand(aip);
        System.out.println();
        if (aip.getHandRank() < 5) {
            System.out.println("AIP will exchange card to improve its rank!");

            cardsToDiscard = strategy.applyStrategy(aip.getHand(), cardsToExchange);
            if (cardsToDiscard != null) {
                System.out.println();
                System.out.print("AIP will discard cards : ");
                printCardsList(cardsToDiscard);
            }
            printCardsToExchange();
            System.out.println();
            System.out.println();
            System.out.println("AIP'S HAND AFTER CARD EXCHANGE :");
            aip.printHand();
        } else {
            System.out.println("AIP did not exchange any of its card");
            aip.getHandRank();
            aip.printHand();
        }
        determineWinner();
    }

    public void determineWinner() {
        System.out.println();
        System.out.print("AIP's card : " );
        printCardsList(aip.getHand());
        System.out.print(", Rank : ");
        detectRankOfHand(aip);
        System.out.print("Opponent's card : " );
        printCardsList(opponent.getHand());
        System.out.print(", Rank : ");
        detectRankOfHand(opponent);
        System.out.println();

        if (aip.getHandRank() == opponent.getHandRank()) {
            int winner = compareTwoEqualRank(aip.getHand(), opponent.getHand());
            if (winner == 1) {
                System.out.println("AIP Wins!");
            } else {
                System.out.println("Opponent Wins!");
            }
        } else if (aip.getHandRank() > opponent.getHandRank()) {
            System.out.println("AIP Wins!");
        } else {
            System.out.println("Opponent Wins!");
        }
    }

    public int compareTwoEqualRank(List<Card> c1, List<Card> c2) {
        if (aip.getHandRank() == 10) {
            if (c1.equals(equalRankcomparator.compareRoyalFlush(c1, c2))) {
                return 1;
            }
        } else if (aip.getHandRank() == 9) {
            if (c1.equals(equalRankcomparator.compareStraightFlush(c1, c2))) {
                return 1;
            }
        } else if (aip.getHandRank() == 8) {
            if (c1.equals(equalRankcomparator.compareFourOfAKind(c1, c2))) {
                return 1;
            }
        } else if (aip.getHandRank() == 7) {
            if (c1.equals(equalRankcomparator.compareFullHouse(c1, c2))) {
                return 1;
            }
        } else if (aip.getHandRank() == 6) {
            if (c1.equals(equalRankcomparator.compareFlush(c1, c2))) {
                return 1;
            }
        } else if (aip.getHandRank() == 5) {
            if (c1.equals(equalRankcomparator.compareStraight(c1, c2))) {
                return 1;
            }
        } else if (aip.getHandRank() == 4) {
            if (c1.equals(equalRankcomparator.compareThreeOfAKind(c1, c2))) {
                return 1;
            }
        } else if (aip.getHandRank() == 3) {
            if (c1.equals(equalRankcomparator.compareTwoPairs(c1, c2))) {
                return 1;
            }
        } else if (aip.getHandRank() == 2) {
            if (c1.equals(equalRankcomparator.compareOnePair(c1, c2))) {
                return 1;
            }
        } else if (aip.getHandRank() == 1) {
            if (c1.equals(equalRankcomparator.compareHighCard(c1, c2))) {
                return 1;
            }
        }

        return 0;

    }

    public void printCardsList(List<Card> c) {
        handChecker.sortHand(c);
        String cardString = "";
        for (int i = 0; i < c.size(); i++) {
            cardString += c.get(i).toString() + " ";
        }
        System.out.print(cardString.trim());
    }

    public void printCardsToExchange() {
        System.out.println();

        System.out.print("Card received for exchange: ");
        String cardString = "";
        for (int i = 0; i < cardsToExchange.size(); i++) {
            cardString += cardsToExchange.get(i).toString() + " ";
        }
        System.out.print(cardString.trim());

    }

    private List<String> readFile(String path) {
        List<String> stringCardLines = new ArrayList<String>();
        String output = "";
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            java.io.File file = new java.io.File(classLoader.getResource(path).getFile());
            java.io.FileReader fileReader = new java.io.FileReader(file);
            java.io.BufferedReader bufferedReader = new java.io.BufferedReader(fileReader);

            String currentLine;

            int i = 0 ;
            while ((currentLine = bufferedReader.readLine()) != null) {
                stringCardLines.add(currentLine);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return stringCardLines;
    }

    public static void main(String[] args) {
        Game poker = new Game();
        poker.run();

    }


}
