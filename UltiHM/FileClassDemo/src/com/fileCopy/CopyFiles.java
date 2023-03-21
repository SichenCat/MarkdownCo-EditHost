package com.fileCopy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFiles {
    public static void main(String[] args) {
        try {
            InputStream is = new FileInputStream("D:\\考研网课\\21_B区大数据.png");
            OutputStream os = new FileOutputStream("FileClassDemo/src/new.png");
            // define a byte buffer to transfer data
            byte[] buffer = new byte[1024];
            int len;  // record the data volume every time;
            while ((len = is.read(buffer)) !=-1){
                os.write(buffer,0,len);
            }
            System.out.println("Copy finished!");
            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
