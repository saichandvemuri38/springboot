package com.saichand.Design.Patterns.builderPattern;

public class Main {
    public static void main(String[] args) {
        UrlBuilder.Builder b = new UrlBuilder.Builder();
        b.protocol("http").host("www.google.com").port("6+969+6596");
        UrlBuilder urlBuilder = b.build();
        System.out.print(urlBuilder.protocol);
        System.out.print(urlBuilder.host);
        System.out.println(urlBuilder.port);
    }
}
