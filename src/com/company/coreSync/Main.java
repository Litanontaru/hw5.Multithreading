package com.company.coreSync;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank(500);
        int usersCount = 25;
        //Этот массив является излишним для решения задачи
        BankUser[] users = new BankUser[usersCount];
        for (int i = 0; i < usersCount; i++) {
            users[i] = new BankUser(bank, 10);
            Thread t = new Thread(users[i]);
            t.start();
        }
    }
}
