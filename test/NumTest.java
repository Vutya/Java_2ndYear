import tests.Num;

import static org.testng.Assert.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class NumTest {
    @DataProvider
    public static Object [][] listOfTests1() {
        return new Object[][]{
                {238, Arrays.asList(2, 3, 8)},
                {0, Collections.singletonList(0)},
                {-4, Collections.singletonList(4)},
                {-456, Arrays.asList(4, 5, 6)},
                {-1234567890, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)}
        };
    }

    @Test(dataProvider = "listOfTests1")
    public void test1(int num, List<Integer> listOfDigits) {
        assertEquals(Num.transformToArray(num), listOfDigits);
    }

    @DataProvider
    public static Object [][] listOfTests2() {
        return new Object[][]{
                {238, 13},
                {0, 0},
                {-4, 4},
                {-456, 15},
                {-1234567890, 45}
        };
    }

    @Test(dataProvider = "listOfTests2")
    public void test2(int num, int sum) {
        assertEquals(Num.getSum(num), sum);
    }
}
