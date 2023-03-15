package com.ArrList;

import java.util.ArrayList;

public class Arrlt {
    public static void main(String[] args){
        //create new arraylist
        ArrayList list = new ArrayList();

        //add content   Public boolean add
        list.add("num1");
        list.add("num2");
        list.add(3);
        System.out.println(list.add("num4 BUT Later 5"));

        //add content at specific index    ,return void
        list.add(3,"New num 4");

        //notice:list is still an address,but <toString> makes it print the whole content
        System.out.println(list);

        //arraylist can store all kinds of obejects
        // 泛型支持:example: ArrayList<String>,ArrayList<Integer>
        //Caution! : 只能存储引用类型，不支持基本数据类型(故要写Integer而不能写int)

        ArrayList<String> strList= new ArrayList<String>();
        // now,it's limited to String Object

        // usually used API
        System.out.println( list.get(3) );
        System.out.println("size is" + list.size() );
        list.add("deleted six");
        System.out.println( "Deleted:" + list.remove(5) );
        System.out.println( list.remove("not exist") +"---Expected:False");
        System.out.println( list.remove("num2") + "---Expected:True");
        list.set(0,"num1 to num0");
        for (int i = 0 ; i < list.size();i++){
            System.out.println(list.get(i));
        }
    }

}
