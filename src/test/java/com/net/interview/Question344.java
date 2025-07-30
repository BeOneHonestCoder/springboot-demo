package com.net.interview;


import java.util.Arrays;

public class Question344 {

    public static void main(String[] args) {
        char[] charArray = new char[]{'h', 'e', 'l', 'l', 'o'};
        System.err.println(Arrays.toString(charArray));
        reverseString(charArray);
        System.err.println(Arrays.toString(charArray));
    }

    private static void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[right];
            s[right] = s[left];
            s[left] = temp;
            left++;
            right--;
        }
    }
}
