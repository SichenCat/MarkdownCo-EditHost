package com.Digui;

public class Recursion {
    public static int runTimes = 5;
    public static void main(String[] args) {
        System.out.println(houzi(3,0));
    }
    public static void test(){
        System.out.println("RUN for" + runTimes + "times");
        runTimes--;
        if (runTimes > 0 ) test();
    }

    public static int digui(int i){
        if (i==1){
            return 1;
        }
        else return i * digui(i-1);
    }


    public static int houzi(int day,int left){
        if ( day == 1) {
            return left;
        } else  return 2*(1+houzi(day - 1,left));
    }
}
