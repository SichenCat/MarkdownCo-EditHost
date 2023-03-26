package com.thread.getDeposit;

public class AccountSyn {
    private double money;
    private String  id;

    public double getMoney() {
        return money;
    }

    public AccountSyn(double money,String id) {
        this.money = money;
        this.id = id;
    }

    public AccountSyn() {
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void drawMoney(double drawed) {
        System.out.println(Thread.currentThread().getName() + " is drawing now");
        // 同步代码块
        //
        synchronized ("constString") {
            if (this.money >= drawed){
                System.out.println("successfully DRAW :" + drawed);  //  first draw
                this.money -= drawed;    // after update,this is more danger for problems to occur
                System.out.println("Money left: " + this.money);
            }else System.out.println("Money NOT enough");
        }
    }
}
