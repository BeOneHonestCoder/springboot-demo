package com.net.interview;


public class Question29 {

    public static void main(String[] args) {
        int result = divide(10, 3);
        System.err.println(result);
    }

    private static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int sign = (dividend > 0) ^ (divisor > 0) ? -1 : 1;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int res = 0;
        while (dividend >= divisor) {
            dividend -= divisor;
            res++;
        }
        return sign == 1 ? res : -res;
    }

    private static int divide1(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        int res = 0;
        while (dividend >= divisor) {
            long temp = divisor, multiple = 1;

            while (dividend >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }

            dividend -= temp;
            res += multiple;
        }

        return (dividend > 0) == (divisor > 0) ? res : -res;
    }
}
