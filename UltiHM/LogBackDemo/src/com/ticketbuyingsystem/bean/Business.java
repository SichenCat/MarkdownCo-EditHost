package com.ticketbuyingsystem.bean;

public class Business extends User{
    private String shopName;
    private String address;

    public Business(String shopName, String address) {
        this.shopName = shopName;
        this.address = address;
    }

    public Business() {
    }

    @Override
    public String toString() {
        return "Business{" +
                "shopName='" + shopName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public Business(String loginName, String userName, String passWord, char sex, String phone, double money, String shopName, String address) {
        super(loginName, userName, passWord, sex, phone, money);
        this.shopName = shopName;
        this.address = address;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
