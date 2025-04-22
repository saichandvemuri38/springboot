package com.saichand.Design.Patterns.factoryMethods;

public class LLD extends Course{
    @Override
    public void createCourse() {
        modules.add(new IntroModule());
    }
}
