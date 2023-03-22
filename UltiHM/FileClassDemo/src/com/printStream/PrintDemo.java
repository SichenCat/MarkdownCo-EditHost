package com.printStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintDemo {
    public static void main(String[] args) throws Exception {
        //1.create a printStream Instance
        //1.1. directly wrap a stream
        //PrintStream ps0 = new PrintStream(new FileOutputStream("FileClassDemo/src/printStream.txt",true));
        //1.2 wrap the file path
        //PrintStream ps1 = new PrintStream("FileClassDemo/src/printStream.txt");
        //1.3 wrap the File Class instance
        PrintStream ps = new PrintStream(new File("FileClassDemo/src/printStream.txt"));
        ps.println(213);
        ps.println("everything");
        ps.flush();
        ps.close();
    }
}
