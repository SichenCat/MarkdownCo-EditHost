package com.Digui;


/*
    beer:  2rmb -> 1beer,1lid,1bottle
    lid:   4lids -> 1beer;
    bottle: 2bottles -> 1beer;
 */
public class BeerRecursion {

    public static int MONEY=20,LID=0,BOTTLE=0,BEER=0,BEER_IN_TOTAL=0;

    public static void main(String[] args) {
        buyBeer();
    }
    public static void buyBeer(){
        BEER += MONEY / 2;MONEY = MONEY % 2;
        BEER += LID/4; LID = LID % 4;
        BEER += BOTTLE/2; BOTTLE = BOTTLE % 2;
        if (BEER == 0) {
            System.out.println("Drinks in total:" + BEER_IN_TOTAL);
            System.out.println("Left lids:" + LID);
            System.out.println("Left bottles:" + BOTTLE);
            return;
        }
        drinkBeer();
    }

    private static void drinkBeer() {
        BEER_IN_TOTAL += BEER;
        LID += BEER;BOTTLE += BEER;
        BEER = 0;
        buyBeer();
    }

}
