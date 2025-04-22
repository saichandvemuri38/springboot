package com.saichand.Design.Patterns.factoryMethods;

public class CourseFactory {
    public static Course getCourse(String courseName) {
        switch (courseName) {
            case "Java":
                return new LLD();
            case "Python":
                return new HLD();
            default:{
                return null;
            }
        }
    }
}
