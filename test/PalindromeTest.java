import tests.Palindrome;

import static org.testng.Assert.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PalindromeTest  {
    @Test
    public void test1() {
        // assert Palindrome.isPalindrome('abcccba');
        assertTrue(Palindrome.isPalindrome("abccba"));
        assertFalse(Palindrome.isPalindrome("abccbA"));
    }

    @Test
    public void test2() {
        assertTrue(Palindrome.isPalindrome("pqp"));
        assertTrue(Palindrome.isPalindrome(""));
        assertTrue(Palindrome.isPalindrome("a"));
    }

    @DataProvider
    public static Object[][] listOfPalindromes() {
        return new Object[][] {
                {"abcba", true},
                {"abcbA", false},
                {"AbcbA", true}
        };
    }

    @Test(dataProvider = "listOfPalindromes")
    public void test3(String word, Boolean isPalindrome) {
        assertEquals(Palindrome.isPalindrome(word), (boolean)isPalindrome);
    }
}
