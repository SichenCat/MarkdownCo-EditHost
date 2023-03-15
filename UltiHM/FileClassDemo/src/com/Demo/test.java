package com.Demo;

import java.io.File;

public class test {
    public static void main(String[] args) {
        File f = new File("C:\\Users\\osaxe\\Desktop\\image.png");
        long size = f.length(); //字节大小
        System.out.println(size);
    }
}
