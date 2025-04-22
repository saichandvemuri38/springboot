package com.saichand.Design.Patterns.factoryMethods;

public class HLD extends Course{
    @Override
    public void createCourse() {
        modules.add(new IntroModule());
    }
}
