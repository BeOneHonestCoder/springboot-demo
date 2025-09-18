package com.net.interview;


import java.util.Scanner;

public class Main3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (true) {
            if (isPrime(n) && isPalindrome(n)) {
                System.out.println(n);
                break;
            }
            n++;
        }

    }

    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPalindrome(int n) {
        String str = String.valueOf(n);
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }
}
