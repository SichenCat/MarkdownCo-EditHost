package com.Digui;

public class Recursion {
    public static int runTimes = 5;
    public static void main(String[] args) {
        test();
    }
    public static void test(){
        System.out.println("RUN for" + runTimes + "times");
        runTimes--;
        if (runTimes > 0 ) test();

    }
}

