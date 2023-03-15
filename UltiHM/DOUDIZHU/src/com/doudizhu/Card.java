package com.doudizhu;

public class Card {
    private String number;
    private String decor;
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Card (){}

    public Card(String decor, String number,int index) {
        this.number = number;
        this.decor = decor;
        this.index = index;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDecor() {
        return decor;
    }

    public void setDecor(String decor) {
        this.decor = decor;
    }

    @Override
    public String toString() {
        return decor + number ;
    }
}
