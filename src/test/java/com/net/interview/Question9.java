package com.net.interview;


public class Question9 {

    public static void main(String[] args) {
        boolean result = isPalindrome(121);
        System.err.println(result);
    }

    private static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x = x / 10;
        }
        return x == reversed || x == reversed / 10;
    }
}
