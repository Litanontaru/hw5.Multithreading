package com.company.coreSync;

public class Bank {

    //В будущем стоит изучить необходимость использования volatile для подобных переменных
    //В реальном продакшене без volatile это не будет работать
    private int moneyAmount;

    public Bank(int initialMoney) {
        moneyAmount = initialMoney;
    }

    public void getMoney(int money) {
        //Нарушение code style: несколько операторов на одной строке + нужно добавить {}
        if (!hasMoney(money)) throw new NoMoneyException("Bank has no money: " + money);
        //Нарушение code style: несколько операторов на одной строке + нужно добавить {}
        else moneyAmount -= money;
        //Нарушение JCC - строка более 120 символов
        System.out.format("thread '%s' got %d money. (money left in bank: %d)%n", Thread.currentThread().getName(), money, moneyAmount);
    }

    public boolean hasMoney(int amount) {
        return moneyAmount >= amount;
    }

    //Этот класс либо должен быть объявлен как static либо вынесен ввверх
    public class NoMoneyException extends RuntimeException {
        public NoMoneyException() {
            super();
        }

        public NoMoneyException(String s) {
            super(s);
        }
    }
}
