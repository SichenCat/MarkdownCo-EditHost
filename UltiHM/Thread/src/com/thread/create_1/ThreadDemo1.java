package com.thread.create_1;

public class ThreadDemo1 {
    public static void main(String[] args) {
        Thread t = new MyThread();  // 3.多态，创建线程实例
        t.start();  //4. 用start方法启动子线程
    }
}

class MyThread extends Thread{      //1.自定义线程，继承Thread类
     //2.重写Run方法，Run里定义线程需要做的事
    @Override
    public void run(){
        //something
        System.out.println("子线程执行————");
    }
}
