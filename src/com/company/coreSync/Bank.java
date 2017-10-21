package com.company.coreSync;

public class Bank {

    private int moneyAmount;

    public Bank(int initialMoney) {
        moneyAmount = initialMoney;
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
