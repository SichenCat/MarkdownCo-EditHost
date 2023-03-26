package com.thread.create_1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadDemo2 {
    public static void main(String[] args) {
        Callable<String> call = new MyCallable(100);
        FutureTask<String> ft1 = new FutureTask<>(call);
        Thread t1 = new Thread(ft1);
        t1.start();
        try {
            String st = ft1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class  MyCallable implements Callable<String>{
    private  int n;

    public MyCallable(int n) {
        this.n = n;
    }

    @Override
    public String call() throws Exception{
        int sum=0;
        for (int i  = 1 ; i <= n ;i ++){
            sum  += i;
        }
        return "result is " + sum;
    }
}
