package com.saichand.Design.Patterns.Oops;

public class AccessModifiers {
    public int orderId;
    private String name;
    protected String description;
    public AccessModifiers(int orderId, String name, String description) {
        this.orderId = orderId;
        this.name = name;
        this.description = description;
        System.out.println(orderId + " " + name + " " + description);
    }

    public static void main(String[] args) {
        AccessModifiers accessModifiers = new AccessModifiers(100, "A", "B");
    }
}

