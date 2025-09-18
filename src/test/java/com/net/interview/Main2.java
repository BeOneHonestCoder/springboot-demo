package com.net.interview;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input  = scanner.nextLine();
        List<String> inputList = Arrays.asList(input.split(" "));
        Collections.reverse(inputList);
        StringBuilder output = new StringBuilder();
        for (String str : inputList) {
            output.append(new StringBuilder(str).reverse()).append(" ");
        }
        System.out.println(output.toString().trim());
    }
}
