package com.iostream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class test {
    public static void main(String[] args) throws IOException {
        OutputStream os =new FileOutputStream("FileClassDemo/src/outputstream.txt");
            os.write('a');
            os.write('2');
            // os.flush()  after output,we must flush in case the data lost
            os.flush();


        }
    }
