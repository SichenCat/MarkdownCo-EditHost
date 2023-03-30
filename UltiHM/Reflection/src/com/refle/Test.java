package com.refle;

import java.lang.reflect.Constructor;

public class Test {
    public static void main(String[] args) throws Exception {
        //1. Class类的forName方法
        Class c = Student.class;
        Constructor cons = c.getConstructor();
        Student s = (Student)  cons.newInstance();
    }
}
