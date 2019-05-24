package tests;

import java.util.ArrayList;
import java.util.List;

public class Num {
    public static List<Integer> transformToArray(int num) {
        List<Integer> digits = new ArrayList<>();
        String n = String.valueOf(Math.abs(num));
        for(int i = 0; i <= n.length() - 1; i++)
            digits.add(Character.getNumericValue(n.charAt(i)));
        return digits;
    }

    public static int getSum(int num) {
        return transformToArray(num).stream().reduce(0, Integer::sum);
        //return transformToArray(num).stream().mapToInt(Integer::intValue).sum();
    }
}
