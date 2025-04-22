package com.saichand.Design.Patterns.Oops;

public class Child extends Inheritance implements User{

    public Child(int x, int y) {
        super(x,y);
    }

    public void square(){
        System.out.println(width);
        System.out.println(height);
        System.out.println("Inside Child square"+width*height);
    }

    @Override
    public void sayHello() {
        
    }
}
