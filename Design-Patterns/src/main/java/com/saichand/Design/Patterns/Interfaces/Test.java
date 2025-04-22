package com.saichand.Design.Patterns.Interfaces;

public class Test implements Test2,Test1{
    public static void main(String[] args) {
        Test test = new Test();
        test.method();
    }

    @Override
    public String method() {
        System.out.println("Test1");
        return "1";
    }
}
