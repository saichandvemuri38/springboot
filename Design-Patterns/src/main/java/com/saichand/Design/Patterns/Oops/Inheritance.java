package com.saichand.Design.Patterns.Oops;

public class Inheritance {
    public int width;
    public int height;
    public Inheritance() {}
    public  Inheritance(int a, int b) {
        System.out.println("Inheritance");
        this.width = a;
        this.height = b;
    }
    public void rectangle() {
        System.out.println("Reca"+width*height);
    }

    public static void main(String[] args) {
        Inheritance obj = new Inheritance(5,10);
        obj.rectangle();
        Child child = new Child(8,10);
        child.square();
    }
}
