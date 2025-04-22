package com.saichand.Design.Patterns.Interview;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        String[] list = new String[] { "ravi" , "java", "syed"};
        String[] t = main.reverseStringsInArray(list);
        for (int i = 0; i < t.length; i++) {
          t[i] =   main.reverseCharInString(t[i]);
        }
        Arrays.stream(t).forEach(System.out::println);
    }
    public String reverseCharInString(String str) {
        char[] chars = str.toCharArray();
        String[] reversed = str.split(" ");
        for (int i = 0; i < chars.length / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[chars.length - 1 - i];
            chars[chars.length - 1 - i] = temp;
        }
        return Arrays.toString(reversed);
    }
    public String[] reverseStringsInArray(String[] list){
        for (int i = 0; i < list.length/2; i++) {
            String temp = list[i];
            list[i] = list[list.length - i - 1];
            list[list.length - i - 1] = temp;
        }
        return list;
    }
}
