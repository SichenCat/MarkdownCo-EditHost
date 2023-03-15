package com.ATM;

public class Account {
    private String cardID;
    private String userName;
    private String password;
    private double money;
    private double qouta;

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getQouta() {
        return qouta;
    }

    public void setQouta(double qouta) {
        this.qouta = qouta;
    }
}
