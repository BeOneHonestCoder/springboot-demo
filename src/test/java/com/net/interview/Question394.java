package com.net.interview;


import java.util.LinkedList;

public class Question394 {

    public static void main(String[] args) {
        String result = decodeString("3[a2[c]]");
        System.err.println(result);
    }

    private static String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> numStack = new LinkedList<>();
        LinkedList<StringBuilder> strStack = new LinkedList<>();
        for (Character c : s.toCharArray()) {
            if (c == '[') {
                numStack.push(multi);
                strStack.push(res);
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder temp = res;
                res = strStack.pop();
                res.append(temp.toString().repeat(numStack.pop()));
            } else if (c >= '0' && c <= '9') {
                multi = multi * 10 + Integer.parseInt(c + "");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
