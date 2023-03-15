package com.ATM;

import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Atm {
    public static void main(String[] args) {
        ArrayList<Account> accountArrayList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("===Welcome Menu===");
            System.out.println("Please Enter:");
            System.out.println("1-Log in");
            System.out.println("2-Sign up");
            System.out.println("3-Exit System");
            int command = sc.nextInt();
            switch (command) {
                case 1:
                    //login
                    break;
                case 2:
                    signUp(accountArrayList,sc);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Illegal!");
            }
        }
    }

    private static void login(ArrayList<Account> accountArrayList,Scanner sc){
        while (true) {
            System.out.println("=== Log in Menu ===");
            System.out.println("Please enter ID");
            String id = sc.next();

        }

    }

    /**
     *
     * @param accountArrayList
     */
    private static void signUp(ArrayList<Account> accountArrayList,Scanner sc){
        System.out.println("=== Sign Up Menu ===");
        Account  account = new Account();
        System.out.println("Please enter name");
        account.setUserName(sc.next());
        String pwd="",pwdCheck="";
        while (true) {
            System.out.println("Please enter password");
            pwd = sc.next();
            System.out.println("Please enter again");
            pwdCheck = sc.next();
            if (pwd.equals(pwdCheck)) {
                System.out.println("Set pwd Successfully");
                break;
            } else System.out.println("password Not compromised");
        }
        account.setPassword(pwd);
        System.out.println("please enter Quota");
        account.setQouta(sc.nextDouble());
        account.setMoney(0);
        account.setCardID(getRandomCardID());
        System.out.println("Ur cardID is" + account.getCardID());
        accountArrayList.add(account);
    }

    private static String getRandomCardID() {
        String cardID ="";
        Random random =new Random();
        for (int i =0;i<8;i++){
            cardID += random.nextInt(10);
        }
        return cardID;
    }
}
