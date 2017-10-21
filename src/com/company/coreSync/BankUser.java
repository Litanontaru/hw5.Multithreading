package com.company.coreSync;

public class BankUser implements Runnable {
    private final Bank bank;
    private int moneyToWithdraw;

    public BankUser(Bank bank, int moneyToWithdraw) {
        this.bank = bank;
        this.moneyToWithdraw = moneyToWithdraw;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (bank) {
                if (bank.hasMoney(moneyToWithdraw)) {
                    bank.getMoney(moneyToWithdraw);
                } else return;
            }
        }
    }
}
