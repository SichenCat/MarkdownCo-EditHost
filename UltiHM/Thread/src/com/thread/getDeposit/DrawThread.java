package com.thread.getDeposit;

public class DrawThread implements Runnable{
    private AccountSyn acc;

    public DrawThread(AccountSyn acc) {
        this.acc = acc;
    }

    public DrawThread() {
    }

    @Override
    public void run() {
        //draw money
        acc.drawMoney(100000);
    }
}
