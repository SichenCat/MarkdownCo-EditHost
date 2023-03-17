package com.Demo;

import java.io.File;

public class test {
    public static void main(String[] args) {
        File f = new File("D:\\考研网课\\408\\C");
        long size = f.length(); //字节大小
        System.out.println(size);
    }
}
