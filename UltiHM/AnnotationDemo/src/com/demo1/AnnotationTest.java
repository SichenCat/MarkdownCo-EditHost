package com.demo1;

import org.junit.Test;

import java.util.Arrays;

public class AnnotationTest {

    @Test
    public void parseClass() {
        Class c = BookStore.class; // 获得类对象
        if (c.isAnnotationPresent(Book.class)) { // 判断该类是否存在这个注解
            Book book = (Book) c.getDeclaredAnnotation(Book.class);  //获取该注解对象,注意！不要使用多态，不便于获取内容
            System.out.println(book.value());
            System.out.println(book.price());
            System.out.println(Arrays.toString(book.authors()));
        }
    }
}
