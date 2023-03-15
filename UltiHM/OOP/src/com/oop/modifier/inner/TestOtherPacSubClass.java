package com.oop.modifier.inner;

import com.oop.modifier.doc.Fu;

public class TestOtherPacSubClass extends Fu {
    public static void main(String[] args) {
        TestOtherPacSubClass subClass = new TestOtherPacSubClass();
        Fu fuClass = new Fu();
        // fu only use publicMethod
        fuClass.publicMethod();
        //subClass can use the protectedMethod
        subClass.protectedMethod();
        subClass.publicMethod();
    }
}
