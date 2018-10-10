import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


import org.junit.contrib.java.lang.system.SystemOutRule;

public class GameTest {
    Game g;
    HandChecker h = new HandChecker();

    @org.junit.Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @org.junit.Before
    public void setUp() throws Exception {
        g = new Game();
    }


    public String[] splitOutputIntoArray(String actualOutput) {
        return actualOutput.split("\\r?\\n");

    }

    private List<String> readFile(String path) {
        List<String> stringCardLines = new ArrayList<String>();

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(path).getFile());
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String currentLine;

            int i = 0;
            while ((currentLine = bufferedReader.readLine()) != null) {
                stringCardLines.add(currentLine);

            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return stringCardLines;
    }

    private String getInputLinesFromStringArray(int line) {
        List<String> arrayInputLines = readFile("Poker.txt");
        String s = arrayInputLines.get(line);

        return s;
    }

    private List storeAIPInitialHand(String gameLine) {
        g.setUpForInitialHands(gameLine);
        List<Card> beginningAIPHand = new ArrayList<Card>(g.getAip().getHand());
        h.sortHand(beginningAIPHand);
        return beginningAIPHand;
    }

    private void runHoldTest(int lineIndex, String expRankOutput, String expectedAct) {

        String gameLine = getInputLinesFromStringArray(lineIndex);

        storeAIPInitialHand(gameLine);
        g.playRound(gameLine);

        String actualOutput = systemOutRule.getLog();
        String[] actualOutputArray = splitOutputIntoArray(actualOutput);

        String expectedRankOutput = expRankOutput;
        String expectedAction = expectedAct;

        assertEquals(expectedRankOutput, actualOutputArray[3]);
        assertEquals(expectedAction, actualOutputArray[5]);
    }

    private void runExchangeOneCardTest(int lineIndex, String expAct) {

        String gameLine = getInputLinesFromStringArray(lineIndex);

        storeAIPInitialHand(gameLine);
        g.playRound(gameLine);

        String actualOutput = systemOutRule.getLog();
        String[] actualOutputArray = splitOutputIntoArray(actualOutput);

        String expectedAction = expAct;

        assertEquals(expectedAction, actualOutputArray[6]);
    }

    private void winningTest(int lineIndex, String aip, String opponent, String winner) {

        String gameLine = getInputLinesFromStringArray(lineIndex);

        storeAIPInitialHand(gameLine);
        g.playRound(gameLine);

        String actualOutput = systemOutRule.getLog();
        String[] actualOutputArray = splitOutputIntoArray(actualOutput);

        String aip1 = aip;
        String opo = opponent;
        String win = winner;
        assertEquals(aip, actualOutputArray[8]);
        assertEquals(opo, actualOutputArray[9]);
        assertEquals(win, actualOutputArray[11]);
    }

    private void winningThreeOfAKindTest(int lineIndex, String aip, String opponent, String winner) {

        String gameLine = getInputLinesFromStringArray(lineIndex);

        storeAIPInitialHand(gameLine);
        g.playRound(gameLine);

        String actualOutput = systemOutRule.getLog();
        String[] actualOutputArray = splitOutputIntoArray(actualOutput);

        String aip1 = aip;
        String opo = opponent;
        String win = winner;
        assertEquals(aip1, actualOutputArray[14]);
        assertEquals(opo, actualOutputArray[15]);
        assertEquals(win, actualOutputArray[17]);
    }

    private void winningCompareTest(int lineIndex, String ex, String aip, String opponent, String winner) {

        String gameLine = getInputLinesFromStringArray(lineIndex);

        storeAIPInitialHand(gameLine);
        g.playRound(gameLine);

        String actualOutput = systemOutRule.getLog();
        String[] actualOutputArray = splitOutputIntoArray(actualOutput);

        String exchange = ex;
        String aip1 = aip;
        String opo = opponent;
        String win = winner;
        assertEquals(exchange, actualOutputArray[6]);
        assertEquals(aip1, actualOutputArray[14]);
        assertEquals(opo, actualOutputArray[15]);
        assertEquals(win, actualOutputArray[17]);
    }

    private void winningCompareNoExchangeCardTest(int lineIndex, String ex, String aip, String opponent, String winner) {

        String gameLine = getInputLinesFromStringArray(lineIndex);

        storeAIPInitialHand(gameLine);
        g.playRound(gameLine);

        String actualOutput = systemOutRule.getLog();
        String[] actualOutputArray = splitOutputIntoArray(actualOutput);

        String exchange = ex;
        String aip1 = aip;
        String opo = opponent;
        String win = winner;
        assertEquals(exchange, actualOutputArray[5]);
        assertEquals(aip1, actualOutputArray[8]);
        assertEquals(opo, actualOutputArray[9]);
        assertEquals(win, actualOutputArray[11]);
    }

    @org.junit.Test
    public void runRoyalFlushAndHoldTest() {

        String expectedRankOutput = "Rank of AIP : Royal Flush!";
        String expectedAction = "AIP did not exchange any of its card";
        runHoldTest(0, expectedRankOutput, expectedAction);
    }

    @org.junit.Test
    public void runStraightFlushAndHoldTest() {

        String expectedRankOutput = "Rank of AIP : Straight FLush!";
        String expectedAction = "AIP did not exchange any of its card";
        runHoldTest(1, expectedRankOutput, expectedAction);

    }

    @org.junit.Test
    public void runFourOfAKindAndHoldTest() {
        String expectedRankOutput = "Rank of AIP : Four of A Kind!";
        String expectedAction = "AIP did not exchange any of its card";
        runHoldTest(2, expectedRankOutput, expectedAction);

    }

    @org.junit.Test
    public void runFullHouseAndHoldTest() {
        String expectedRankOutput = "Rank of AIP : Full House!";
        String expectedAction = "AIP did not exchange any of its card";
        runHoldTest(3, expectedRankOutput, expectedAction);
    }

    @org.junit.Test
    public void runFlushAndHoldTest() {
        String expectedRankOutput = "Rank of AIP : Flush!";
        String expectedAction = "AIP did not exchange any of its card";
        runHoldTest(4, expectedRankOutput, expectedAction);
    }

    @org.junit.Test
    public void runStraightAndHoldTest() {
        String expectedRankOutput = "Rank of AIP : Straight!";
        String expectedAction = "AIP did not exchange any of its card";
        runHoldTest(5, expectedRankOutput, expectedAction);
    }


    @org.junit.Test
    public void runOneCardAwayFromRoyalFlush_OnePairTest() {
        String expectedAction = "AIP is one card away from Royal Flush, exchange one card";
        runExchangeOneCardTest(6, expectedAction);
    }

    @org.junit.Test
    public void runOneCardAwayFromRoyalFlush_AceTest() {
        String expectedAction = "AIP is one card away from Royal Flush, exchange one card";
        runExchangeOneCardTest(7, expectedAction);
    }

    @org.junit.Test
    public void runOneCardAwayFromStraightFlush_OnePairWithRangeTest() {
        String expectedAction = "AIP is one card away from Straight Flush, exchange one card";
        runExchangeOneCardTest(8, expectedAction);
    }

    @org.junit.Test
    public void runOneCardAwayFromStraightFlushTest() {
        String expectedAction = "AIP is one card away from Straight Flush, exchange one card";
        runExchangeOneCardTest(9, expectedAction);
    }

    @org.junit.Test
    public void runOneCardAwayFromFlush() {
        String expectedAction = "AIP is one card away from Flush, exchange one card";
        runExchangeOneCardTest(10, expectedAction);
    }

    @org.junit.Test
    public void runOneCardAwayFromStraight() {
        String expectedAction = "AIP is one card away from Straight, exchange one card";
        runExchangeOneCardTest(11, expectedAction);
    }

    @org.junit.Test
    public void runOneCardAwayFromStraight_onePair() {
        String expectedAction = "AIP is one card away from Straight, exchange one card";
        runExchangeOneCardTest(12, expectedAction);
    }

    @org.junit.Test
    public void runThreeOfSameSuit() {
        String expectedAction = "AIP has three of the same suit, exchange two card";
        runExchangeOneCardTest(13, expectedAction);
    }

    @org.junit.Test
    public void runThreeOfAKind() {
        String expectedAction = "AIP has three of a same rank, exchange two card";
        runExchangeOneCardTest(14, expectedAction);
    }

    @org.junit.Test
    public void runThreeCardsInSequence() {
        String expectedAction = "AIP has three cards in sequence, exchange two card";
        runExchangeOneCardTest(15, expectedAction);
    }

    @org.junit.Test
    public void runTwoDistinctPair() {
        String expectedAction = "AIP has two pairs, exchange one card";
        runExchangeOneCardTest(16, expectedAction);
    }

    @org.junit.Test
    public void runOnePair() {
        String expectedAction = "AIP has one pair, exchange three card";
        runExchangeOneCardTest(17, expectedAction);
    }

    @org.junit.Test
    public void runHighCard() {
        String expectedAction = "AIP has high card, exchange the 3 lowest card";
        runExchangeOneCardTest(18, expectedAction);
    }

    @org.junit.Test
    public void royalFlushWinsTest() {
        String aip = "AIP's card : S10 SJ SQ SK SA, Rank : Royal Flush!";
        String opponent = "Opponent's card : H9 H10 HJ HQ HK, Rank : Straight FLush!";
        String winner = "AIP Wins!";
        winningTest(19, aip, opponent, winner);
    }

    @org.junit.Test
    public void StraightFlushLostTest() {
        String aip = "AIP's card : S9 S10 SJ SQ SK, Rank : Straight FLush!";
        String opponent = "Opponent's card : H10 HJ HQ HK HA, Rank : Royal Flush!";
        String winner = "Opponent Wins!";
        winningTest(20, aip, opponent, winner);
    }

    @org.junit.Test
    public void StraightFlushWinTest() {
        String aip = "AIP's card : S9 S10 SJ SQ SK, Rank : Straight FLush!";
        String opponent = "Opponent's card : D4 C4 H4 S4 HK, Rank : Four of A Kind!";
        String winner = "AIP Wins!";
        winningTest(21, aip, opponent, winner);
    }

    @org.junit.Test
    public void FourOfAKindWinTest() {
        String aip = "AIP's card : D4 C4 H4 S4 HK, Rank : Four of A Kind!";
        String opponent = "Opponent's card : D3 D5 D8 D9 D10, Rank : Flush!";
        String winner = "AIP Wins!";
        winningTest(22, aip, opponent, winner);
    }

    @org.junit.Test
    public void FourOfAKindLoseRFTest() {
        String aip = "AIP's card : D4 C4 H4 S4 HK, Rank : Four of A Kind!";
        String opponent = "Opponent's card : S10 SJ SQ SK SA, Rank : Royal Flush!";
        String winner = "Opponent Wins!";
        winningTest(23, aip, opponent, winner);
    }

    @org.junit.Test
    public void FourOfAKindLostSFTest() {
        String aip = "AIP's card : D4 C4 H4 S4 HK, Rank : Four of A Kind!";
        String opponent = "Opponent's card : S9 S10 SJ SQ SK, Rank : Straight FLush!";
        String winner = "Opponent Wins!";
        winningTest(24, aip, opponent, winner);
    }

    @org.junit.Test
    public void FullHouseBeatFlushTest() {
        String aip = "AIP's card : CJ HJ SJ HK SK, Rank : Full House!";
        String opponent = "Opponent's card : D5 D8 D10 DJ DA, Rank : Flush!";
        String winner = "AIP Wins!";
        winningTest(25, aip, opponent, winner);
    }

    @org.junit.Test
    public void FullHouseLoseFourOfAKindTest() {
        String aip = "AIP's card : CJ HJ SJ HK SK, Rank : Full House!";
        String opponent = "Opponent's card : D10 DQ CQ HQ SQ, Rank : Four of A Kind!";
        String winner = "Opponent Wins!";
        winningTest(26, aip, opponent, winner);
    }

    @org.junit.Test
    public void FullHouseLoseStraightFlushTest() {
        String aip = "AIP's card : CJ HJ SJ HK SK, Rank : Full House!";
        String opponent = "Opponent's card : D9 D10 DJ DQ DK, Rank : Straight FLush!";
        String winner = "Opponent Wins!";
        winningTest(27, aip, opponent, winner);
    }

    @org.junit.Test
    public void FullHouseLoseRoyalFlushTest() {
        String aip = "AIP's card : CJ HJ SJ HK SK, Rank : Full House!";
        String opponent = "Opponent's card : D10 DJ DQ DK DA, Rank : Royal Flush!";
        String winner = "Opponent Wins!";
        winningTest(28, aip, opponent, winner);
    }

    @org.junit.Test
    public void FlushbeatStraightTest() {
        String aip = "AIP's card : D5 D8 D10 DJ DA, Rank : Flush!";
        String opponent = "Opponent's card : C5 H6 S7 H8 C9, Rank : Straight!";
        String winner = "AIP Wins!";
        winningTest(29, aip, opponent, winner);
    }

    @org.junit.Test
    public void FlushbeatThreeOfAKindTest() {
        String aip = "AIP's card : D5 D8 D10 DJ DA, Rank : Flush!";
        String opponent = "Opponent's card : C2 D6 D10 C10 H10, Rank : Three of A Kind!";
        String winner = "AIP Wins!";
        winningTest(30, aip, opponent, winner);
    }

    @org.junit.Test
    public void FlushbeatTwoPairsTest() {
        String aip = "AIP's card : D5 D8 D10 DJ DA, Rank : Flush!";
        String opponent = "Opponent's card : S4 H7 S7 CJ SJ, Rank : Two Pairs!";
        String winner = "AIP Wins!";
        winningTest(31, aip, opponent, winner);
    }

    @org.junit.Test
    public void FlushbeatOnePairsTest() {
        String aip = "AIP's card : D5 D8 D10 DJ DA, Rank : Flush!";
        String opponent = "Opponent's card : C5 S8 S9 DK CK, Rank : One Pair!";
        String winner = "AIP Wins!";
        winningTest(32, aip, opponent, winner);
    }

    @org.junit.Test
    public void FlushbeatHighCardTest() {
        String aip = "AIP's card : D5 D8 D10 DJ DA, Rank : Flush!";
        String opponent = "Opponent's card : D2 C3 S8 DQ SA, Rank : High Card!";
        String winner = "AIP Wins!";
        winningTest(33, aip, opponent, winner);
    }

    @org.junit.Test
    public void StraightBeatThreeOfAKindTest() {
        String aip = "AIP's card : C5 H6 D7 S8 C9, Rank : Straight!";
        String opponent = "Opponent's card : C2 D6 D10 C10 H10, Rank : Three of A Kind!";
        String winner = "AIP Wins!";
        winningTest(34, aip, opponent, winner);
    }

    @org.junit.Test
    public void StraightBeatTwoPairsTest() {
        String aip = "AIP's card : C5 H6 D7 S8 C9, Rank : Straight!";
        String opponent = "Opponent's card : H7 S7 S9 DJ CJ, Rank : Two Pairs!";
        String winner = "AIP Wins!";
        winningTest(35, aip, opponent, winner);
    }

    @org.junit.Test
    public void StraightBeatOnePairTest() {
        String aip = "AIP's card : C5 H6 D7 S8 C9, Rank : Straight!";
        String opponent = "Opponent's card : H3 D8 S9 DK CK, Rank : One Pair!";
        String winner = "AIP Wins!";
        winningTest(36, aip, opponent, winner);
    }

    @org.junit.Test
    public void StraightBeatHighCardTest() {
        String aip = "AIP's card : C5 H6 D7 S8 C9, Rank : Straight!";
        String opponent = "Opponent's card : D2 C4 C8 DQ SA, Rank : High Card!";
        String winner = "AIP Wins!";
        winningTest(37, aip, opponent, winner);
    }

    @org.junit.Test
    public void ThreeOfAKindBeatTwoPairTest() {
        String aip = "AIP's card : H2 D6 D10 C10 H10, Rank : Three of A Kind!";
        String opponent = "Opponent's card : H7 S7 S9 DJ CJ, Rank : Two Pairs!";
        String winner = "AIP Wins!";
        winningThreeOfAKindTest(38, aip, opponent, winner);
    }

    @org.junit.Test
    public void ThreeOfAKindBeatOnePairTest() {
        String aip = "AIP's card : H2 D6 D10 C10 H10, Rank : Three of A Kind!";
        String opponent = "Opponent's card : C4 D8 S9 DK CK, Rank : One Pair!";
        String winner = "AIP Wins!";
        winningThreeOfAKindTest(39, aip, opponent, winner);
    }

    @org.junit.Test
    public void ThreeOfAKindBeatHighCardTest() {
        String aip = "AIP's card : H2 D6 D10 C10 H10, Rank : Three of A Kind!";
        String opponent = "Opponent's card : D2 C4 S8 DQ SK, Rank : High Card!";
        String winner = "AIP Wins!";
        winningThreeOfAKindTest(40, aip, opponent, winner);
    }

    @org.junit.Test
    public void TwoPairBeatOnePairTest() {
        String aip = "AIP's card : S3 H4 S4 DJ CJ, Rank : Two Pairs!";
        String opponent = "Opponent's card : D2 C2 D7 D8 C9, Rank : One Pair!";
        String winner = "AIP Wins!";
        winningThreeOfAKindTest(41, aip, opponent, winner);
    }

    @org.junit.Test
    public void TwoPairBeatHighCardTest() {
        String aip = "AIP's card : S3 H4 S4 DJ CJ, Rank : Two Pairs!";
        String opponent = "Opponent's card : D2 S4 S8 C9 DQ, Rank : High Card!";
        String winner = "AIP Wins!";
        winningThreeOfAKindTest(42, aip, opponent, winner);
    }

    @org.junit.Test
    public void OnePairBeatHighCardTest() {
        String aip = "AIP's card : S2 C10 DK CK CA, Rank : One Pair!";
        String opponent = "Opponent's card : D2 C6 S8 S10 DQ, Rank : High Card!";
        String winner = "AIP Wins!";
        winningThreeOfAKindTest(43, aip, opponent, winner);
    }

    @org.junit.Test
    public void RoyalFlushSuitCompareTest() {
        String exchange = "AIP is one card away from Royal Flush, exchange one card";
        String aip = "AIP's card : S10 SJ SQ SK SA, Rank : Royal Flush!";
        String opponent = "Opponent's card : H10 HJ HQ HK HA, Rank : Royal Flush!";
        String winner = "AIP Wins!";
        winningCompareTest(44, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void StraightFlushHighestRankCompareTest() {
        String exchange = "AIP is one card away from Straight Flush, exchange one card";
        String aip = "AIP's card : S6 S7 S8 S9 S10, Rank : Straight FLush!";
        String opponent = "Opponent's card : H5 H6 H7 H8 H9, Rank : Straight FLush!";
        String winner = "AIP Wins!";
        winningCompareTest(45, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void StraightFlushHighestSuiCompareTest() {
        String exchange = "AIP is one card away from Straight Flush, exchange one card";
        String aip = "AIP's card : S5 S6 S7 S8 S9, Rank : Straight FLush!";
        String opponent = "Opponent's card : H5 H6 H7 H8 H9, Rank : Straight FLush!";
        String winner = "AIP Wins!";
        winningCompareTest(46, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void FourOfAKindCompareTest() {
        String exchange = "AIP has three of a same rank, exchange two card";
        String aip = "AIP's card : D5 DQ CQ HQ SQ, Rank : Four of A Kind!";
        String opponent = "Opponent's card : D3 DJ CJ HJ SJ, Rank : Four of A Kind!";
        String winner = "AIP Wins!";
        winningCompareTest(47, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void FullHouseompareTest() {
        String exchange = "AIP has three of a same rank, exchange two card";
        String aip = "AIP's card : CJ HJ SJ CK HK, Rank : Full House!";
        String opponent = "Opponent's card : C10 H10 S10 DK SK, Rank : Full House!";
        String winner = "AIP Wins!";
        winningCompareTest(48, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void ThreeOfAKindCompareTest() {
        String exchange = "AIP has one pair, exchange three card";
        String aip = "AIP's card : C5 C6 D10 C10 H10, Rank : Three of A Kind!";
        String opponent = "Opponent's card : S2 S5 D9 C9 H9, Rank : Three of A Kind!";
        String winner = "AIP Wins!";
        winningCompareTest(49, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void StraightSuitCompareTest() {
        String exchange = "AIP did not exchange any of its card";
        String aip = "AIP's card : S5 H6 C7 D8 S9, Rank : Straight!";
        String opponent = "Opponent's card : H5 S6 S7 S8 D9, Rank : Straight!";
        String winner = "AIP Wins!";
        winningCompareNoExchangeCardTest(50, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void StraightRankCompareTest() {
        String exchange = "AIP did not exchange any of its card";
        String aip = "AIP's card : S5 H6 C7 D8 S9, Rank : Straight!";
        String opponent = "Opponent's card : D2 D3 H4 H5 S6, Rank : Straight!";
        String winner = "AIP Wins!";
        winningCompareNoExchangeCardTest(51, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void TwoPairsSuitCompareTest() {
        String exchange = "AIP has two pairs, exchange one card";
        String aip = "AIP's card : D6 S6 D8 S8 CQ, Rank : Two Pairs!";
        String opponent = "Opponent's card : D2 C6 S6 C8 H8, Rank : Two Pairs!";
        String winner = "AIP Wins!";
        winningCompareTest(52, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void TwoPairsRankCompareTest() {
        String exchange = "AIP has two pairs, exchange one card";
        String aip = "AIP's card : D6 S6 D8 S8 CQ, Rank : Two Pairs!";
        String opponent = "Opponent's card : D2 C6 S6 C7 H7, Rank : Two Pairs!";
        String winner = "AIP Wins!";
        winningCompareTest(53, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void OnePairSuitCompareTest() {
        String exchange = "AIP has one pair, exchange three card";
        String aip = "AIP's card : D6 S7 C8 CK SK, Rank : One Pair!";
        String opponent = "Opponent's card : S2 C3 D9 DK HK, Rank : One Pair!";
        String winner = "AIP Wins!";
        winningCompareTest(54, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void OnePairRankCompareTest() {
        String exchange = "AIP has one pair, exchange three card";
        String aip = "AIP's card : D6 S7 C8 CK SK, Rank : One Pair!";
        String opponent = "Opponent's card : S2 C3 D9 DQ HQ, Rank : One Pair!";
        String winner = "AIP Wins!";
        winningCompareTest(55, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void HighCardSuitCompareTest() {
        String exchange = "AIP has three of the same suit, exchange two card";
        String aip = "AIP's card : H2 S3 D9 SJ SA, Rank : High Card!";
        String opponent = "Opponent's card : C2 D3 H6 S8 HQ, Rank : High Card!";
        String winner = "AIP Wins!";
        winningCompareTest(56, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void HighCardRankCompareTest() {
        String exchange = "AIP has three of the same suit, exchange two card";
        String aip = "AIP's card : H2 S3 D9 S10 SA, Rank : High Card!";
        String opponent = "Opponent's card : C2 D3 H6 S8 HA, Rank : High Card!";
        String winner = "AIP Wins!";
        winningCompareTest(57, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void FlushSuitCompareTest() {
        String exchange = "AIP is one card away from Flush, exchange one card";
        String aip = "AIP's card : S5 S8 S10 SJ SA, Rank : Flush!";
        String opponent = "Opponent's card : H5 H8 H10 HJ HA, Rank : Flush!";
        String winner = "AIP Wins!";
        winningCompareTest(58, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void HighCardFourSameCompareTest() {
        String exchange = "AIP is one card away from Flush, exchange one card";
        String aip = "AIP's card : S6 S8 S10 SJ SA, Rank : Flush!";
        String opponent = "Opponent's card : H5 H8 H10 HJ HA, Rank : Flush!";
        String winner = "AIP Wins!";
        winningCompareTest(59, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void HighCardThreeSameCompareTest() {
        String exchange = "AIP is one card away from Flush, exchange one card";
        String aip = "AIP's card : S4 S9 S10 SJ SA, Rank : Flush!";
        String opponent = "Opponent's card : H5 H8 H10 HJ HA, Rank : Flush!";
        String winner = "AIP Wins!";
        winningCompareTest(60, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void HighCardTwoSameCompareTest() {
        String exchange = "AIP is one card away from Flush, exchange one card";
        String aip = "AIP's card : S3 S6 S10 SJ SA, Rank : Flush!";
        String opponent = "Opponent's card : H4 H7 H9 HJ HA, Rank : Flush!";
        String winner = "AIP Wins!";
        winningCompareTest(61, exchange, aip, opponent, winner);
    }


    @org.junit.Test
    public void HighCardOneSameCompareTest() {
        String exchange = "AIP is one card away from Flush, exchange one card";
        String aip = "AIP's card : S5 S8 S10 SJ SA, Rank : Flush!";
        String opponent = "Opponent's card : H3 H5 H8 H10 HA, Rank : Flush!";
        String winner = "AIP Wins!";
        winningCompareTest(62, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void ComplexOneAwayAceTest() {
        String exchange = "AIP is one card away from Straight, exchange one card";
        String aip = "AIP's card : SA H2 H3 H4 C5, Rank : Straight!";
        String opponent = "Opponent's card : H6 D8 C8 H8 S9, Rank : Three of A Kind!";
        String winner = "AIP Wins!";
        winningCompareTest(63, exchange, aip, opponent, winner);
    }

    @org.junit.Test
    public void ComplexOneAwayCompareTest() {
        String exchange = "AIP is one card away from Straight, exchange one card";
        String aip = "AIP's card : H9 H10 CJ HQ SK, Rank : Straight!";
        String opponent = "Opponent's card : H6 D8 C8 H8 S9, Rank : Three of A Kind!";
        String winner = "AIP Wins!";
        winningCompareTest(64, exchange, aip, opponent, winner);
    }


}