package com.thread.getDeposit;

public class quqianDemo {
    public static void main(String[] args) {
        Account acc1 = new Account(100000.0,"ABAKA");
        AccountSyn acc2 = new AccountSyn(100000.0,"GORGOA");
        new Thread(new DrawThread(acc2),"lee").start();
        new Thread(new DrawThread(acc2),"wang").start();
    }
}
