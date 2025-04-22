package com.saichand.Design.Patterns.factoryMethods;

import java.util.ArrayList;
import java.util.List;

public abstract class Course {
    protected List<IntroModule> modules = new ArrayList<>();
    public Course() {
        this.createCourse();
    }
    public List<IntroModule> getModules() {
        return modules;
    }
    public abstract void createCourse();
}
