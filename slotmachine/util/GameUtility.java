package slotmachine.util;

import java.util.ArrayList;
import java.util.List;

public class GameUtility {
    public static List<Integer> getReelLength(List<String[]> bgReels){
        List<Integer> reelLengthList = new ArrayList<>();
        for (String[] reel : bgReels) {
            reelLengthList.add(reel.length);
        }
        return reelLengthList;
    }
}
