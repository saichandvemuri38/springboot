package com.saichand.Design.Patterns.factoryMethods;

public class Demo {
    public static void main(String[] args) {
        Course lld = CourseFactory.getCourse("Java");
        Course mkd = CourseFactory.getCourse("Python");
        System.out.println(lld.modules);
        System.out.println(mkd.modules);
    }
}
