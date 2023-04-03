package com.demo1;

import org.junit.Test;

import java.lang.annotation.Annotation;

@Book(value = "DND:FIRST",price = 99.9,authors = {"haha"})
public class BookStore {

    @Book(value = "DND:SECOND",price =  199.9,authors = {"heihei"})
    public void test(){

    }
}

