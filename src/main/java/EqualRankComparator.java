import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class EqualRankComparator {
    private HandChecker handChecker = new HandChecker();

    public List<Card> compareRoyalFlush(List<Card> c1,List<Card>c2){
        if(c1.get(0).getSuit() > c2.get(0).getSuit()){
            return c1;
        }
        else{
            return c2;
        }
    }
}
