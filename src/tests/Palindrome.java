package tests;

public class Palindrome {
    public static boolean isPalindrome(String word) {
        int l = word.length();
        for (int i= 0; i < l /2; i++) {
            if (word.charAt(i) != word.charAt(l - i - 1))
                return false;
        }
        return true;
    }
}
