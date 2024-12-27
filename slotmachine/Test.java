package slotmachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        String[] array = new String[]{"-1", "B", "-1", "-1"}; // make it list
        List<String> topRow = new ArrayList<>();
//        topRow.add("-1");
//        topRow.add("D");
//        topRow.add("-1");
//        topRow.add("A");

        for(int i = 0; i< array.length ; i++){
            if (!array[i].contains("-1")){
                topRow.add(array[i]);
            }
        }
//        topRow.addLast("S");
        System.out.println(topRow);

    }
}
