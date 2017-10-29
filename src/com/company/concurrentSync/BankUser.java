package com.company.concurrentSync;

public class BankUser implements Runnable {
    //Поле должно быть final
    private Bank bank;
    //Поле должно быть final
    private int moneyToWithdraw;

    public BankUser(Bank bank, int moneyToWithdraw) {
        this.bank = bank;
        this.moneyToWithdraw = moneyToWithdraw;
    }

    @Override
    public void run() {
        while (true) {
            try {
                bank.getLock().lock();
                if (bank.hasMoney(moneyToWithdraw)) {
                    bank.getMoney(moneyToWithdraw);
                } else return;
            } finally {
                bank.getLock().unlock();
            }
        }
    }
}
