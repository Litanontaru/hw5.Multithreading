package com.company.coreSync;

public class BankUser implements Runnable {
    private final Bank bank;
    //Поле должно быть final
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
                    //нарушение JCC - несколько операторов в одной строке + нужно добавить {}
                } else return;
            }
        }
    }
}
