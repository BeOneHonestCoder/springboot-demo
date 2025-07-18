package com.net.interview;


public class Question415 {

    public static void main(String[] args) {
        String result = addStrings("456", "77");
        System.err.println(result);

        String subtractString = subtractStrings("456", "51");
        System.err.println(subtractString);
    }

    private static String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        StringBuilder ans = new StringBuilder();
        while (i >= 0 || j >= 0 || carry != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + carry;
            ans.append(result % 10);
            carry = result / 10;
            i--;
            j--;
        }
        System.err.println(ans);
        ans.reverse();
        return ans.toString();
    }

    private static String subtractStrings(String num1, String num2) {
        boolean isNegative = false;
        if (compare(num1, num2) < 0) {
            isNegative = true;
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        int maxLength = Math.max(num1.length(), num2.length());
        num1 = padZeros(num1, maxLength);
        num2 = padZeros(num2, maxLength);

        StringBuilder result = new StringBuilder();
        int borrow = 0;
        for (int i = maxLength - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';
            int digit2 = num2.charAt(i) - '0';

            digit1 -= borrow;
            borrow = 0;

            if (digit1 < digit2) {
                digit1 += 10;
                borrow = 1;
            }

            result.append(digit1 - digit2);
        }
        System.err.println(result);
        String res = result.reverse().toString().replaceAll("^0+", "");
        if (res.isEmpty()) return "0";
        return isNegative ? "-" + res : res;
    }

    private static int compare(String num1, String num2) {
        if (num1.length() != num2.length()) {
            return num1.length() - num2.length();
        }
        return num1.compareTo(num2);
    }

    private static String padZeros(String num, int targetLength) {
        while (num.length() < targetLength) {
            num = "0" + num;
        }
        return num;
    }
}
