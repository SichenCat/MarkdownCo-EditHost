package com.ObjectOutputDemo;

import java.io.*;

public class serialedObjectOutput {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //Student s = new Student("GUANGXI","guangxi","123456");
        //ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("FileClassDemo/src/obj.txt"));
        //oos.writeObject(s);
        //oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("FileClassDemo/src/obj.txt"));
        Student s = (Student) ois.readObject();
        System.out.println(s);
    }
}
