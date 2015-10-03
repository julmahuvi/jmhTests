package com.julmahuvi;

/**
 * Created by tuomaala_petri on 03/10/15.
 */
public class Entry {
    private String a;
    private String b;

    public Entry(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public void doSomething() {

        if ( a.contains( "ii")) {
            System.out.println();
        } else if( a.contains( "iii")) {
            System.out.println();
        }

        String tmp = a + b;

        if ( tmp.contains("iiii")) {
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Entry{" +
            "a='" + a + '\'' +
            ", b='" + b + '\'' +
            '}';
    }
}
