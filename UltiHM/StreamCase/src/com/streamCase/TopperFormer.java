package com.streamCase;

public class TopperFormer extends Employee{
    private int money;

    public int getMoney() {
        return money;
    }

    public TopperFormer(String name, char sex, int salary, int bonus, String punish, int money) {
        super(name, sex, salary, bonus, punish);
        this.money = money;
    }

    public TopperFormer() {
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
