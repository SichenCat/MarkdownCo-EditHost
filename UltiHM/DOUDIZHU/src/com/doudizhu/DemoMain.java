package com.doudizhu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DemoMain {
    /*
    1:one static arraylist to store 54 card instance
     */
    public static List<Card> allCards = new ArrayList<>() ;
    /*
    2:use static code block to initialize the cards
     */
    static{
        int index=0;
        String[] numbers = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        String[] decors = {"♠","♥","♣","♦"};
        for (String decor :decors)
        {
            index=0;
            for (String number:numbers
                 ) {
                index++;
                Card c = new Card(decor,number,index);
                allCards.add(c);
            }
        }
        allCards.add(new Card("🃏","Joker",23));
        allCards.add(new Card("🃏","joker",22));
        System.out.println("New Cards:"+'\n'+allCards);

        }


    public static void main(String[] args) {
        Collections.shuffle(allCards); // shuffle
        System.out.println("after shuffle:" + '\n' +allCards);

        //distribute the cards to 3 new players
        List<Card> user1 = new ArrayList<>();
        List<Card> user2 = new ArrayList<>();
        List<Card> user3 = new ArrayList<>();

        for (int i = 0; i < allCards.size()-3; i++) {
            Card c = allCards.get(i);
            switch (i % 3) {
                case 0:user1.add(c);
                break;
                case 1:user2.add(c);
                break;
                case 2:user3.add(c);
                break;
            }
        }
        //get last three cards
        List<Card> dipai = allCards.subList(allCards.size()-3,allCards.size());

        //show the 3 players and their cards
        System.out.println("user1:" + '\n' + user1);
        System.out.println("user2:" + '\n' + user2);
        System.out.println("user3:" + '\n' + user3);
        System.out.println("dipai:" + '\n' + dipai);

        //sort the cards
        sortCards(user1);
        sortCards(user2);
        sortCards(user3);
        System.out.println("user1:" + '\n' + user1);
        System.out.println("user2:" + '\n' + user2);
        System.out.println("user3:" + '\n' + user3);
    }

    private static void sortCards(List<Card> cards) {
        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getIndex() - o2.getIndex();
            }
        });
    }


}
