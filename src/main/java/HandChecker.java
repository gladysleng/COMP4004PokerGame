import java.util.List;
import java.util.ArrayList;
public class HandChecker {

    private int TWO_OF_A_KIND = 2;
    private int FOUR_OF_A_KIND = 4;
    private int THREE_OF_A_KIND = 3;

    public boolean validSize(List<Card> c) {

        return c.size() == 5;
    }



}