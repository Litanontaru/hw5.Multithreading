package com.company.concurrentSync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank(500);
        int usersCount = 25;
        Executor executor = Executors.newFixedThreadPool(usersCount);
        for (int i = 0; i < usersCount; i++) {
            BankUser user = new BankUser(bank, 10);
            executor.execute(user);
        }
    }
}
