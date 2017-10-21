package com.company.concurrentSync;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    private int moneyAmount;
    private Lock lock = new ReentrantLock();

    public Bank(int initialMoney) {
        moneyAmount = initialMoney;
    }

    public synchronized Lock getLock() {
        return lock;
    }

    public void getMoney(int money) {
        if (!hasMoney(money)) throw new NoMoneyException("Bank has no money: " + money);
        else moneyAmount -= money;
        System.out.format("thread '%s' got %d money. (money left in bank: %d)%n", Thread.currentThread().getName(), money, moneyAmount);
    }

    public boolean hasMoney(int amount) {
        return moneyAmount >= amount;
    }

    public class NoMoneyException extends RuntimeException {
        public NoMoneyException() {
            super();
        }

        public NoMoneyException(String s) {
            super(s);
        }
    }
}
