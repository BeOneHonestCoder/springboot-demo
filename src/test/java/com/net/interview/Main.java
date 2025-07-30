package com.net.interview;


import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(str);
        str = str.replaceAll("[^0-9]", "");
        System.out.println(str);
        String[] strArray = str.split("");
        System.out.println(Arrays.toString(strArray));
    }
}
